use cape_codd;
SELECT Department, Count(SKU) AS Dept_SKU_Count
FROM SKU_DATA
WHERE SKU <> 302000 
Group by  Department
Having  Dept_SKU_Count > 1 
Order by Dept_SKU_Count 