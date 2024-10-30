package com.example.TestAgencyAmazon.dto.statistic;

import com.example.TestAgencyAmazon.models.salesdata.Money;
import java.util.List;
import lombok.Data;

@Data
public class TotalSalesByDateDto {
    private List<Money> orderedProductSales;
    private List<Money> orderedProductSalesB2B;
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
    private List<Money> averageSalesPerOrderItem;
    private List<Money> averageSalesPerOrderItemB2B;
    private double averageUnitsPerOrderItem;
    private double averageUnitsPerOrderItemB2B;
    private List<Money> averageSellingPrice;
    private List<Money> averageSellingPriceB2B;
    private int unitsRefunded;
    private double refundRate;
    private int claimsGranted;
    private List<Money> claimsAmount;
    private List<Money> shippedProductSales;
    private int unitsShipped;
    private int ordersShipped;
}
