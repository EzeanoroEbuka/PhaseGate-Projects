use cape_codd;
select sum(ExtendedPrice) AS OrderItemSum, 
		avg(ExtendedPrice) AS OrderItemAvg,
        min(ExtendedPrice) AS OrderItemMin,
        max(ExtendedPrice) AS orderItemMax
 FROM Order_item       
        