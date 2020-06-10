package com.azurras.website.models.blog;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Post {
    private String author;
    private String contentText;
    private Date creationDate;
    private String description;
    private int id;
    private List<Tag> tags;
    private String title;

    public Post() {
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

    public List<Tag> getTags() {
        return new ArrayList<>(this.tags);
    }

    public void setTags(List<Tag> tag) {
        this.tags = new ArrayList<>(tags);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}