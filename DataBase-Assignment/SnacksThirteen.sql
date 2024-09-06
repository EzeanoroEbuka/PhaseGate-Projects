use cape_codd;
SELECT Department, count(SKU) AS NumberOfCatalogItems
FROM CATALOG_SKU_2017
WHERE CatalogPage is not NULL
Group by Department