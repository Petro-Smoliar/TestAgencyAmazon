package com.example.TestAgencyAmazon.models.salesdata;

import lombok.Data;

@Data
public class SalesByAsin {
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private Money orderedProductSales;
    private Money orderedProductSalesB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
}
