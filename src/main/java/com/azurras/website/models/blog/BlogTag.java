package com.azurras.website.models.blog;

import java.util.Date;

public class BlogTag {
    private Date creationDate;
    private String name;

    public BlogTag() {
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