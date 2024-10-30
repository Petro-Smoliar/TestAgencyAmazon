package com.example.TestAgencyAmazon.dto.statistic;

import com.example.TestAgencyAmazon.models.trafficdata.TrafficByDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalStatisticByDateDto {
    private TrafficByDate totalTrafficByDate;
    private TotalSalesByDateDto totalSalesByDate;
}
