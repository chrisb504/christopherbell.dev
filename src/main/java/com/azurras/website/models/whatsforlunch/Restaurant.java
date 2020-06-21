package com.azurras.website.models.whatsforlunch;

public class Restaurant {
    private String address;
    private int id;
    private String name;
    private String phoneNumber;
    private String postalCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}