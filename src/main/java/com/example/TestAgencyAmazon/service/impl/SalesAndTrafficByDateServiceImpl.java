package com.example.TestAgencyAmazon.service.impl;

import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByDateDto;
import com.example.TestAgencyAmazon.models.SalesAndTrafficByDate;
import com.example.TestAgencyAmazon.repository.SalesAndTrafficByDateRepository;
import com.example.TestAgencyAmazon.service.SalesAndTrafficByDateService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesAndTrafficByDateServiceImpl implements SalesAndTrafficByDateService {
    private final SalesAndTrafficByDateRepository dateRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    @Cacheable(value = "productStatsCache", key = "#dates")
    public List<SalesAndTrafficByDate> getDateStatistic(List<LocalDate> dates) {
        if (dates.isEmpty()) {
            return List.of();
        }
        if (dates.size() == 1) {
            return dateRepository.findByDate(dates.get(0));
        } else {
            return dateRepository.findByDateBetween(dates.get(0), dates.get(1));
        }
    }

    @Override
    @Cacheable
    public TotalStatisticByDateDto getTotalStatisticByDate() {
        return dateRepository.aggregateSalesAndTrafficData();
    }
}
