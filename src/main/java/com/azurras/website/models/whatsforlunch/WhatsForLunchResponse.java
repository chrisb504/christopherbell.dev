package com.azurras.website.models.whatsforlunch;

import java.util.ArrayList;
import java.util.List;

public class WhatsForLunchResponse {
    private String message;
    private String status;
    private List<Restaurant> payload;

    public WhatsForLunchResponse() {
        this.message = "";
        this.status = "";
        this.payload = new ArrayList<>();
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public List<Restaurant> getBlogPostPayload() {
        return new ArrayList<>(this.payload);
    }

    public void setPayLoad(final List<Restaurant> payload) {
        this.payload.addAll(payload);
    }

    public List<Restaurant> getBlogTagPayload() {
        return new ArrayList<>(this.payload);
    }

    public void setBlogTagPayLoad(final List<Restaurant> payload) {
        this.payload.addAll(payload);
    }
}