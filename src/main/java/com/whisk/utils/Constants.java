package com.whisk.utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    /*User data*/
    public static final String EMAIL = "emailtest080784@gmail.com";
    public static final String PASSWORD = "123Qwerty123!";

    /*Timeouts*/
    public static final int ELEMENT_TIMEOUT_SIXTY_SECONDS = 60;

    /*Test data*/
    public static final String SHOPPING_LIST_NAME = DataGenerator.generateNameForElement("Shopping_List");

    /*API data*/
    public static final String USERAGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36";
    public static final String BEARER_TOKEN = "DutdpmvlZnUPjR9ctZwSi6tUfBxiKoWI1QB8H9oCaBEujKeQv7uJvfrHLMmi4u3v";
    public static final String SHOPPING_LIST_ENDPOINT = "/list/v2";

    /*Pathes*/
    protected static Map<String, String> destination = getDestination();
    public static String MAIN_PAGE_URL = destination.get("client");
    public static final String HOST = destination.get("client_api");
    public static String DOMAIN;
    public static final String PATH_TO_DIRECTORY_CONFIG = "./src/main/resources/";


    public static Map<String, String> getDestination() {
        DOMAIN = System.getProperty("DOMAIN");

        JSONObject domainsJson;

        try {
            domainsJson = Formatter.parseJSONFile(PATH_TO_DIRECTORY_CONFIG,"domains.json").getJSONObject(DOMAIN);
        } catch (Exception e) {
            throw new AssertionError("We don't have '" + DOMAIN + "' domain in our domains.json file");
        }

        Map<String, String> result = new HashMap<>();

        result.put("client", domainsJson.getString("client"));
        result.put("client_api",  domainsJson.getString("client_api"));
        result.put("admin", domainsJson.getString("admin"));
        result.put("admin_api", domainsJson.getString("admin_api"));

        return result;
    }
}
