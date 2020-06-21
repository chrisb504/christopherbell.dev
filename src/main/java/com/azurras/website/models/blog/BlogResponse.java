package com.azurras.website.models.blog;

import java.util.ArrayList;
import java.util.List;

public class BlogResponse {
    private String message;
    private String status;
    private List<BlogPost> payload;

    public BlogResponse() {
        this.message = "";
        this.status = "";
        this.payload = new ArrayList<BlogPost>();
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

    public List<BlogPost> getPayload() {
        return new ArrayList<>(payload);
    }

    public void setPayLoad(final List<BlogPost> payload) {
        this.payload.addAll(payload);
    }
}