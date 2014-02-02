package edu.illinois.coursera.android.json.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {

    private String name;
    private String price;
    private int quantity;
    private String weight;

    public Item (JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString("name");
        price = jsonObject.getString("price");
        quantity = jsonObject.getInt("quantity");
        weight = jsonObject.getString("weight");
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
