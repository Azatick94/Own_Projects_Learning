## Trying to improve speed

import pandas as pd

from shapely.geometry import Point
from shapely.geometry.polygon import Polygon
from datetime import datetime

# %%
time1 = datetime.now()

df_users = pd.read_csv('user_coordinates.csv')
df_places = pd.read_csv('place_zone_coordinates.csv')

# группировка ресторанчиков
df_places_grouped = df_places.groupby(by='place_id').agg({'loc_lat': list, 'loc_lon': list}, axis=0)
df_places_grouped['place_id'] = df_places_grouped.index
df_places_grouped = df_places_grouped[['place_id', 'loc_lat', 'loc_lon']]
df_places_grouped.reset_index(inplace=True, drop=True)


def run_check_poly(places_grouped, user_loc_lat, user_loc_lon):
    # df_places - датафрейм для фильтрации
    # sample_place_id - id для фильтрования
    lst_loc_lat = places_grouped['loc_lat'].tolist()
    lst_loc_lon = places_grouped['loc_lon'].tolist()

    counter = 0;
    for k in range(len(places_grouped)):

        # preparing polygon for
        lats = lst_loc_lat[k]
        longs = lst_loc_lon[k]

        sample_poly = Polygon(list(zip(longs, lats)))
        sample_point = Point(user_loc_lon, user_loc_lat)
        condition_check = sample_poly.contains(sample_point)
        if condition_check:
            counter += 1

    return counter


# # %% trying lambda, didn't help me, no improvement
# df_users = df_users[:10]
# df_users['count'] = df_users.apply(
#     lambda row: run_check_poly(places_grouped=df_places_grouped, user_loc_lat=row['loc_lat'],
#                                user_loc_lon=row['loc_lon']), axis=1)

# %%

lst_result_user_id = []
lst_result_restaurant_count = []
# итерируем по юзерам


lst_users = df_users['user_id'].tolist()

lst_lat = df_users['loc_lat'].tolist()
lst_lon = df_users['loc_lon'].tolist()

for i in range(len(df_users[:1000])):
    # sample user with id, latitude, longiture
    sample_user_id = int(lst_users[i])
    print(
        'user: ' + str(sample_user_id) + ", percents_completed: " + str(round(sample_user_id / len(df_users) * 100, 5)))

    sample_user_loc_lat = lst_lat[i]
    sample_user_loc_lon = lst_lon[i]

    count = run_check_poly(places_grouped=df_places_grouped,
                           user_loc_lat=sample_user_loc_lat,
                           user_loc_lon=sample_user_loc_lon)

    lst_result_user_id.append(sample_user_id)
    lst_result_restaurant_count.append(count)

# %%

final_result = pd.DataFrame({'user_id': lst_result_user_id, 'restaurant_count': lst_result_restaurant_count})
final_result.to_csv('processed_file.csv', encoding='utf-8', index=False)

time2 = datetime.now()
delta_time = str((time2 - time1).total_seconds())
print("Time required: " + delta_time)
