use cape_codd;
SELECT Department, Buyer, count(SKU) AS Dept_Buyer_SKU_Count
FROM  SKU_DATA
Group by Department,  Buyer