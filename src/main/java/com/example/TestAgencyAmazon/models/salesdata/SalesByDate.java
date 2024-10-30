package com.example.TestAgencyAmazon.models.salesdata;

import lombok.Data;

@Data
public class SalesByDate {
    private Money orderedProductSales;
    private Money orderedProductSalesB2B;
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
    private Money averageSalesPerOrderItem;
    private Money averageSalesPerOrderItemB2B;
    private double averageUnitsPerOrderItem;
    private double averageUnitsPerOrderItemB2B;
    private Money averageSellingPrice;
    private Money averageSellingPriceB2B;
    private int unitsRefunded;
    private double refundRate;
    private int claimsGranted;
    private Money claimsAmount;
    private Money shippedProductSales;
    private int unitsShipped;
    private int ordersShipped;
}
