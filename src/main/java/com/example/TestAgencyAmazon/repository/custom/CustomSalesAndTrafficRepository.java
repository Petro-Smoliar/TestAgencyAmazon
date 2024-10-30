package com.example.TestAgencyAmazon.repository.custom;

import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByAsinDto;
import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByDateDto;

public interface CustomSalesAndTrafficRepository {

    TotalStatisticByDateDto aggregateSalesAndTrafficData();

    TotalStatisticByAsinDto aggregateSalesAndTrafficAsin();
}
