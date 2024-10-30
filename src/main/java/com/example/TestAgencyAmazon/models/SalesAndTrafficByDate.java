package com.example.TestAgencyAmazon.models;

import com.example.TestAgencyAmazon.models.salesdata.SalesByDate;
import com.example.TestAgencyAmazon.models.trafficdata.TrafficByDate;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "salesAndTrafficByDate")
public class SalesAndTrafficByDate {
    @Id
    private String id;
    private LocalDate date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;
}
