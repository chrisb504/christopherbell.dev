package com.azurras.website.models.blog;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class BlogPost {
    private String author;
    private String contentText;
    private Date creationDate;
    private String description;
    private int id;
    private List<BlogTag> tags;
    private String title;

    public BlogPost() {
        this.author = "";
        this.contentText = "";
        this.creationDate = new Date();
        this.description = "";
        this.id = generateId();
        this.tags = new ArrayList<>();
        this.title = "";
    }

    private int generateId() {
        return new Random().nextInt();
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContentText() {
        return this.contentText;
    }

    public void setContentText(String contextText) {
        this.contentText = contextText;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BlogTag> getTags() {
        return new ArrayList<>(this.tags);
    }

    public void setTags(List<BlogTag> tag) {
        this.tags = new ArrayList<>(tags);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}