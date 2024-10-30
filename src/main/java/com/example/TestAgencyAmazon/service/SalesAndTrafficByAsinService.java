package com.example.TestAgencyAmazon.service;

import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByAsinDto;
import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByDateDto;
import com.example.TestAgencyAmazon.models.SalesAndTrafficByAsin;
import java.util.List;

public interface SalesAndTrafficByAsinService {

    List<SalesAndTrafficByAsin> getAsinStatistics(List<String> asins);

    TotalStatisticByAsinDto getTotalStatisticByAsin();
}
