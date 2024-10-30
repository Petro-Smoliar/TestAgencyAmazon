package com.example.TestAgencyAmazon.dto.statistic;

import com.example.TestAgencyAmazon.models.trafficdata.TrafficByAsin;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalStatisticByAsinDto {
    private TrafficByAsin totalTrafficByAsin;
    private TotalSalesByAsinDto totalSalesByAsin;
}
