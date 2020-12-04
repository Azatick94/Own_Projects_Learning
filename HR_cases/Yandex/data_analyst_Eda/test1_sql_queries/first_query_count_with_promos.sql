-- testing query

-- count ALL rows;
-- we are suggesting that id is unique;
-- (SELECT COUNT(*) FROM orders);



-- фильтрация строк которые были с промокодами.

--   SELECT * FROM (
--                     SELECT orders.id as orders, promocode_id, name as promocode_name, discount as promocode_discount
--                     FROM orders LEFT JOIN promocodes p on p.id = orders.promocode_id
--                 )
--                     AS result
--   WHERE (promocode_id is NOT NULL AND promocode_discount is NOT NULL AND
--          promocode_discount>0)
-- ;



-- Final Scripts
-- 1.Какова доля заказов с промокодами?

SELECT
(SELECT COUNT(*) FROM (
                          SELECT * FROM (
                                            SELECT orders.id as orders, promocode_id, name as promocode_name, discount as promocode_discount
                                            FROM orders LEFT JOIN promocodes p on p.id = orders.promocode_id
                                        )
                                            AS result
                          WHERE (promocode_id is NOT NULL AND promocode_discount is NOT NULL AND
                                 promocode_discount>0)
                         ) AS result_count)
    /
(SELECT CAST(COUNT(*) AS FLOAT) FROM orders) AS result_ratio;
