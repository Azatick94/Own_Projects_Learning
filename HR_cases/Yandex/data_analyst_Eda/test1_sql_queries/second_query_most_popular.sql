

-- Какой самый популярный промокод (название) и какая сумма общих затрат по нему?
-- Самый популярный промокод – это промокод, по которому получились наибольшие затраты в деньгах.

SELECT promocode_id FROM (

       SELECT promocode_id, SUM(promocode_discount) as sum FROM (
            SELECT * FROM (
                              SELECT orders.id as orders, promocode_id, name as promocode_name, discount as promocode_discount
                              FROM orders LEFT JOIN promocodes p on p.id = orders.promocode_id
                          ) AS result
            WHERE (promocode_id is NOT NULL AND promocode_discount is NOT NULL AND promocode_discount>0)
            ) AS result_with_promo
       GROUP BY promocode_id ORDER BY sum DESC LIMIT 1

           ) as result_sorted_sum;

