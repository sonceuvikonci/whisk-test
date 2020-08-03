package com.whisk.base.api;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.bytebuddy.build.ToStringPlugin;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

public class BaseActionsAPI {

    protected Gson gson;

    protected Gson getGson(){
        if (gson == null) {
            gson = new GsonBuilder()
                    .setExclusionStrategies(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            return f.getAnnotation(ToStringPlugin.Exclude.class) != null;
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> c) {
                            return false;
                        }
                    })
                    .create();
        }
        return gson;
    }

    protected  JSONObject toJSONObject(ResponseEntity response){
        return new JSONObject(response.getBody().toString());
    }

    protected <T> T decodeJson(String json, Class<T> cls) {
        return  getGson().fromJson(json, cls);
    }

    protected <T> T decodeJson(JSONObject json, Class<T> cls) {
        return  getGson().fromJson(json.toString(), cls);
    }

    protected String createBodyJSONFromObject(Object o){
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getAnnotation(ToStringPlugin.Exclude.class) != null;
                    }
                    @Override
                    public boolean shouldSkipClass(Class<?> c) {
                        return false;
                    }
                })
                .create();
        return gson.toJson(o);
    }
}
