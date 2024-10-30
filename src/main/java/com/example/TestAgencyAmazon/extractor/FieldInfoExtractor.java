package com.example.TestAgencyAmazon.extractor;

import com.example.TestAgencyAmazon.models.salesdata.Money;
import com.example.TestAgencyAmazon.models.salesdata.SalesByAsin;
import com.example.TestAgencyAmazon.models.salesdata.SalesByDate;
import com.example.TestAgencyAmazon.models.trafficdata.TrafficByAsin;
import com.example.TestAgencyAmazon.models.trafficdata.TrafficByDate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FieldInfoExtractor {
    private final HashMap<Class<?>, List<String>> nonObjectFieldsMap;
    private final HashMap<Class<?>, List<String>> moneyFieldsMap;

    private FieldInfoExtractor() {
        nonObjectFieldsMap = new HashMap<>();
        moneyFieldsMap = new HashMap<>();
        extractFields();
    }

    public List<String> getNonObjectFields(Class<?> clazz) {
        return nonObjectFieldsMap.get(clazz);
    }

    public List<String> getMoneyFields(Class<?> clazz) {
        return moneyFieldsMap.get(clazz);
    }

    private void extractFields() {
        addFields(SalesByDate.class);
        addFields(SalesByAsin.class);
        addFields(TrafficByDate.class);
        addFields(TrafficByAsin.class);
    }

    private void addFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> nonObjectFields = new ArrayList<>();
        List<String> moneyFields = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == Money.class) {
                moneyFields.add(field.getName());
            } else {
                nonObjectFields.add(field.getName());
            }
        }

        nonObjectFieldsMap.put(clazz, nonObjectFields);
        moneyFieldsMap.put(clazz, moneyFields);
    }
}
