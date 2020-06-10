package com.azurras.website.models.blog;

import java.util.Date;

public class Tag {
    private Date creationDate;
    private String name;

    public Tag() {
        this.creationDate = new Date();
        this.name = "";
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}