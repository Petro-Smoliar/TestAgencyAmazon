package com.example.TestAgencyAmazon.service.impl;

import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByAsinDto;
import com.example.TestAgencyAmazon.models.SalesAndTrafficByAsin;
import com.example.TestAgencyAmazon.repository.SalesAndTrafficByAsinRepository;
import com.example.TestAgencyAmazon.service.SalesAndTrafficByAsinService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalesAndTrafficByAsinServiceImpl implements SalesAndTrafficByAsinService {
    private final SalesAndTrafficByAsinRepository asinRepository;

    @Override
    @Cacheable(value = "productStatsCache", key = "#asins")
    public List<SalesAndTrafficByAsin> getAsinStatistics(List<String> asins) {
        return asinRepository.findByParentAsinIn(asins);
    }

    @Override
    @Cacheable
    public TotalStatisticByAsinDto getTotalStatisticByAsin() {
        return asinRepository.aggregateSalesAndTrafficAsin();
    }
}
