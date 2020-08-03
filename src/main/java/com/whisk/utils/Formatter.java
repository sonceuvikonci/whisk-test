package com.whisk.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Formatter {

    public static String escapeCharacters(String string) {
        return string.replace("&", "&#38;");
    }

    public static JSONObject parseJSONFile(String path, String filename) {
        JSONObject result = null;
        try {
            String content = new String(Files.readAllBytes(Paths.get(path + filename)));
            result = new JSONObject(content);
        } catch (IOException | JSONException e) {
            Reporter.log("Some issues with reading from json file");
        }
        return result;
    }
}
