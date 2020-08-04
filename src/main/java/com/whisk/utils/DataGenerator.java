package com.whisk.utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.*;

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
        String [] foodList = new String[foodCount];
        Arrays.setAll(foodList, p -> faker.food().ingredient());
        return Arrays.asList(foodList);
    }
}