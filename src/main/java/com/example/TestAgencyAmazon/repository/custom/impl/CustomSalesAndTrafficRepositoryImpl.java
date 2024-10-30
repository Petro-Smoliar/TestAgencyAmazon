package com.example.TestAgencyAmazon.repository.custom.impl;

import com.example.TestAgencyAmazon.dto.statistic.TotalSalesByAsinDto;
import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByAsinDto;
import com.example.TestAgencyAmazon.extractor.FieldInfoExtractor;
import com.example.TestAgencyAmazon.models.salesdata.Money;
import com.example.TestAgencyAmazon.models.salesdata.SalesByAsin;
import com.example.TestAgencyAmazon.models.salesdata.SalesByDate;
import com.example.TestAgencyAmazon.dto.statistic.TotalSalesByDateDto;
import com.example.TestAgencyAmazon.dto.statistic.TotalStatisticByDateDto;
import com.example.TestAgencyAmazon.models.trafficdata.TrafficByAsin;
import com.example.TestAgencyAmazon.models.trafficdata.TrafficByDate;
import com.example.TestAgencyAmazon.repository.custom.CustomSalesAndTrafficRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomSalesAndTrafficRepositoryImpl implements CustomSalesAndTrafficRepository {
    private final MongoTemplate mongoTemplate;
    private final FieldInfoExtractor extractor;

    @Override
    public TotalStatisticByDateDto aggregateSalesAndTrafficData() {
        String trafficPath = "trafficByDate";
        String salesPath = "salesByDate";
        String collection = "salesAndTrafficByDate";

        Aggregation trafficByDate = getAggregation(TrafficByDate.class, trafficPath);
        AggregationResults<TrafficByDate> traffic = mongoTemplate.aggregate(
                trafficByDate,
                collection,
                TrafficByDate.class
        );

        Aggregation salesByDate = getAggregation(SalesByDate.class, salesPath);
        AggregationResults<TotalSalesByDateDto> sales = mongoTemplate.aggregate(
                salesByDate,
                collection,
                TotalSalesByDateDto.class
        );

        Document salesReportDocument =
                (Document) sales.getRawResults().get("results", List.class).get(0);

        addListsMoneyByReport(salesReportDocument, SalesByDate.class, salesPath, collection);

        TotalSalesByDateDto salesByDateDto = mongoTemplate
                .getConverter()
                .read(TotalSalesByDateDto.class, salesReportDocument);

        return new TotalStatisticByDateDto(traffic.getUniqueMappedResult(), salesByDateDto);
    }

    @Override
    public TotalStatisticByAsinDto aggregateSalesAndTrafficAsin() {
        String trafficPath = "trafficByAsin";
        String salesPath = "salesByAsin";
        String collection = "salesAndTrafficByAsin";

        Aggregation trafficByAsin = getAggregation(TrafficByAsin.class, trafficPath);
        AggregationResults<TrafficByAsin> traffic = mongoTemplate.aggregate(
                trafficByAsin,
                collection,
                TrafficByAsin.class
        );

        Aggregation salesByAsin = getAggregation(SalesByAsin.class, salesPath);
        AggregationResults<TotalSalesByAsinDto> sales = mongoTemplate.aggregate(
                salesByAsin,
                collection,
                TotalSalesByAsinDto.class
        );

        Document salesReportDocument =
                (Document) sales.getRawResults().get("results", List.class).get(0);

        addListsMoneyByReport(salesReportDocument, SalesByAsin.class, salesPath, collection);

        TotalSalesByAsinDto salesByAsinDto = mongoTemplate
                .getConverter()
                .read(TotalSalesByAsinDto.class, salesReportDocument);

        return new TotalStatisticByAsinDto(traffic.getUniqueMappedResult(), salesByAsinDto);
    }

    private Aggregation getAggregation(Class<?> filedClass, String path) {
        GroupOperation group = Aggregation.group();
        ProjectionOperation project = Aggregation.project();
        for (String field : extractor.getNonObjectFields(filedClass)) {
            group = group.sum(path + "." + field).as("total" + field);
            project = project.and("total" + field).as(field);
        }
        return  Aggregation.newAggregation(group, project);
    }

    private void addListsMoneyByReport(
            Document reportDocument, Class<?> fieldClass, String path, String collection) {
        for (String field : extractor.getMoneyFields(fieldClass)) {
            AggregationOperation groupByCurrency =
                    Aggregation.group(path + "." + field + ".currencyCode")
                            .sum(path + "." + field + ".amount")
                            .as(field + "_amount");

            ProjectionOperation projectionMoney = Aggregation.project()
                    .and("_id").as("currencyCode")
                    .and(field + "_amount").as("amount");

            Aggregation aggregation =
                    Aggregation.newAggregation(groupByCurrency, projectionMoney);
            AggregationResults<Money> aggregate = mongoTemplate.aggregate(
                    aggregation,
                    collection,
                    Money.class
            );
            reportDocument.append(field, aggregate.getMappedResults());
        }
    }
}
