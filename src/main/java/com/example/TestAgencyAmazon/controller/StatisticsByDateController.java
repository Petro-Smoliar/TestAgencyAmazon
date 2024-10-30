package com.example.TestAgencyAmazon.controller;

import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByDateDto;
import com.example.TestAgencyAmazon.models.SalesAndTrafficByDate;
import com.example.TestAgencyAmazon.service.SalesAndTrafficByDateService;
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
@RequestMapping("/statics/date")
public class StatisticsByDateController {
    private final SalesAndTrafficByDateService service;

    @GetMapping()
    public List<SalesAndTrafficByDate> getStaticsByDate(
            @RequestParam @Size(max = 2) List<LocalDate> dates) {
        return service.getDateStatistic(dates);
    }

    @GetMapping("/all")
    public TotalStatisticByDateDto getAllStaticsByDate() {
        return service.getTotalStatisticByDate();
    }
}
