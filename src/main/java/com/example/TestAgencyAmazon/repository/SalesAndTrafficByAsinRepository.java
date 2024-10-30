package com.example.TestAgencyAmazon.repository;

import com.example.TestAgencyAmazon.models.SalesAndTrafficByAsin;
import com.example.TestAgencyAmazon.repository.custom.CustomSalesAndTrafficRepository;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesAndTrafficByAsinRepository
        extends MongoRepository<SalesAndTrafficByAsin, String>, CustomSalesAndTrafficRepository {

    List<SalesAndTrafficByAsin> findByParentAsinIn(List<String> asins);
}
