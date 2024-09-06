use cape_codd;
SELECT orderNumber , SKU, (Quantity * Price) AS EP
FROM ORDER_ITEM
ORDER BY OrderNumber 