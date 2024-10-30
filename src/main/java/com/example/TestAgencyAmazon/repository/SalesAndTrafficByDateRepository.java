package com.example.TestAgencyAmazon.repository;

import com.example.TestAgencyAmazon.models.SalesAndTrafficByDate;
import com.example.TestAgencyAmazon.repository.custom.CustomSalesAndTrafficRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesAndTrafficByDateRepository
        extends MongoRepository<SalesAndTrafficByDate, String>, CustomSalesAndTrafficRepository {

    List<SalesAndTrafficByDate> findByDate(LocalDate date);

    List<SalesAndTrafficByDate> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
