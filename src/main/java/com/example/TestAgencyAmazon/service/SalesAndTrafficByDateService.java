package com.example.TestAgencyAmazon.service;

import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByDateDto;
import com.example.TestAgencyAmazon.models.SalesAndTrafficByDate;
import java.time.LocalDate;
import java.util.List;

public interface SalesAndTrafficByDateService {

    List<SalesAndTrafficByDate> getDateStatistic(List<LocalDate> dates);

    TotalStatisticByDateDto getTotalStatisticByDate();
}
