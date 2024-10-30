package com.example.TestAgencyAmazon.dto.statistic;

import com.example.TestAgencyAmazon.models.salesdata.Money;
import java.util.List;
import lombok.Data;

@Data
public class TotalSalesByAsinDto {
    private int unitsOrdered;
    private int unitsOrderedB2B;
    private List<Money> orderedProductSales;
    private List<Money> orderedProductSalesB2B;
    private int totalOrderItems;
    private int totalOrderItemsB2B;
}
