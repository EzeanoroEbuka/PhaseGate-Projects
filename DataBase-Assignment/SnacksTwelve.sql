use cape_codd;
SELECT orderNumber, SKU 
FROM ORDER_ITEM
WHERE (Quantity * Price) <> ExtendedPrice
ORDER BY OrderNumber, SKU;