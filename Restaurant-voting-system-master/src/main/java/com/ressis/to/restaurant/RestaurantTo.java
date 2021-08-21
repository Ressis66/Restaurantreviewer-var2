package com.ressis.to.restaurant;

import com.ressis.to.BaseTo;

public class RestaurantTo extends BaseTo {
    private String name;

    public RestaurantTo() {
    }

    public RestaurantTo(int id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
