use cape_codd; 
SELECT *
FROM ORDER_ITEM
where ExtendedPrice >= 100 
And ExtendedPrice <= 200
order by ExtendedPrice ASC