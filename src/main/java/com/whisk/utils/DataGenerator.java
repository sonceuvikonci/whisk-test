package com.whisk.utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DataGenerator {

    public static String getDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy / HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static String generateNameForElement(String elementName) {
        String generatedElementName = String.format("%s created by autotest at %s", elementName, getDateTime());
        return generatedElementName;
    }

    public static List<String> generateFoodList(int foodCount) {
        Reporter.log("Food list generating ...");
        Faker faker = new Faker();
        List<String> foodList = new ArrayList<>();
        for (int i = 0; i < foodCount; i++) {
            foodList.add(faker.food().ingredient());
        }
        Collections.sort(foodList);
        return foodList;
    }

}