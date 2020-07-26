package dev.christopherbell.azurras.models.whatsforlunch;

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