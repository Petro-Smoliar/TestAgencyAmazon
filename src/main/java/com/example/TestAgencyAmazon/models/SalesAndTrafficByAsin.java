package com.example.TestAgencyAmazon.models;

import com.example.TestAgencyAmazon.models.salesdata.SalesByAsin;
import com.example.TestAgencyAmazon.models.trafficdata.TrafficByAsin;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "salesAndTrafficByAsin")
public class SalesAndTrafficByAsin {
    @Id
    private String id;
    private String parentAsin;
    private SalesByAsin salesByAsin;
    private TrafficByAsin trafficByAsin;
}
