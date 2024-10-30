package com.example.TestAgencyAmazon.service.impl;

import com.example.TestAgencyAmazon.models.SalesAndTrafficReport;
import com.example.TestAgencyAmazon.repository.SalesAndTrafficByAsinRepository;
import com.example.TestAgencyAmazon.repository.SalesAndTrafficByDateRepository;
import com.example.TestAgencyAmazon.service.UpdateDataBaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateDataBaseServiceImpl implements UpdateDataBaseService {
    private final SalesAndTrafficByAsinRepository asinRepository;
    private final SalesAndTrafficByDateRepository dateRepository;
    private final ObjectMapper objectMapper;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        loadSalesData();
    }

    @Override
    @SneakyThrows
    @Scheduled(fixedRate = 300000)
    public void loadSalesData() {
        var resource = new ClassPathResource("test_report.json");
        SalesAndTrafficReport report =
                objectMapper.readValue(resource.getInputStream(), SalesAndTrafficReport.class);

        asinRepository.deleteAll();
        dateRepository.deleteAll();

        asinRepository.saveAll(report.getSalesAndTrafficByAsin());
        dateRepository.saveAll(report.getSalesAndTrafficByDate());
    }
}
