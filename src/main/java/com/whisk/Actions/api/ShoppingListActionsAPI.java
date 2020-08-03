package com.whisk.Actions.api;

import com.whisk.base.api.APIRequest;
import com.whisk.base.api.BaseActionsAPI;
import com.whisk.models.ShoppingList;
import com.whisk.utils.Reporter;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;

import static com.whisk.utils.Constants.*;

public class ShoppingListActionsAPI extends BaseActionsAPI {

    private static ShoppingListActionsAPI shoppingListActionsAPI;
    private final String shoppingListURL = HOST + SHOPPING_LIST_ENDPOINT;
    private ResponseEntity response;

    public ShoppingListActionsAPI createShoppingListAPI(String name) {
        Reporter.log("Create and send API request to " + shoppingListURL + " to create shopping list...");

        ShoppingList listToCreate = ShoppingList.createListWithName(name);

        response = APIRequest.createPostRequest(shoppingListURL)
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .setBody(createBodyJSONFromObject(listToCreate))
                .send();

        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());

        return this;
    }

    public ShoppingListActionsAPI getShoppingListAPI(String id) {
        Reporter.log(String.format("Create and send API request to %s/%s to get shopping list ...", shoppingListURL, id));

        String url = shoppingListURL + "/" + id;

        response = APIRequest.createGetRequest(url)
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .send();

        return this;
    }

    public ShoppingList getShoppingListFromResponse() {

        ShoppingList list = decodeJson(toJSONObject(response).getJSONObject("list"), ShoppingList.class);
        JSONObject content = toJSONObject(response).getJSONObject("content");
        list.setContent(content);
        return list;
    }

    public ShoppingListActionsAPI deleteShoppingListAPI(String id) {
        Reporter.log(String.format("Create and send API request to %s/%s to delete shopping list ...", shoppingListURL, id));

        String url = shoppingListURL + "/" + id;
        response = APIRequest.createDeleteRequest(url)
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
                .send();
        Assert.assertTrue(response.getStatusCode().is2xxSuccessful(), String.format("Response is not 200 OK for %s", url));
        return this;
    }

    public ShoppingListActionsAPI check200OKResponse() {
        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
        return this;
    }

    public ShoppingListActionsAPI check400OKResponse() {
        Assert.assertTrue(response.getStatusCode().is4xxClientError());
        return this;
    }

    public String getCodeFromErrorResponse() {
        return toJSONObject(response).getString("code");
    }

    public static ShoppingListActionsAPI getShoppingListActionsAPI() {
        if (shoppingListActionsAPI == null)
            shoppingListActionsAPI = new ShoppingListActionsAPI();
        return shoppingListActionsAPI;
    }
}
