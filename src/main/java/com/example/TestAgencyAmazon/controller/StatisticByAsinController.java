package com.example.TestAgencyAmazon.controller;

import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByAsinDto;
import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByDateDto;
import com.example.TestAgencyAmazon.models.SalesAndTrafficByAsin;
import com.example.TestAgencyAmazon.models.SalesAndTrafficByDate;
import com.example.TestAgencyAmazon.service.SalesAndTrafficByAsinService;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/statics/asin")
public class StatisticByAsinController {
    private final SalesAndTrafficByAsinService service;

    @GetMapping()
    public List<SalesAndTrafficByAsin> getStaticsByDate(@RequestParam List<String> asins) {
        return service.getAsinStatistics(asins);
    }

    @GetMapping("/all")
    public TotalStatisticByAsinDto getAllStaticsByDate() {
        return service.getTotalStatisticByAsin();
    }
}
