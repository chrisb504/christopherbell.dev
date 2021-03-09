package dev.christopherbell.azurras.models.blog;

import java.util.ArrayList;
import java.util.List;

public class BlogRequest {
    private String author;
    private String contentText;
    private String description;
    private String imagePath;
    private List<String> tags;
    private String title;

    public BlogRequest() {
        this.author = "";
        this.contentText = "";
        this.description = "";
        this.imagePath = "";
        this.tags = new ArrayList<String>();
        this.title = "";
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

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ArrayList<String> getTags() {
        return new ArrayList<String>(this.tags);
    }

    public void setTags(ArrayList<String> tags) {
        this.tags.addAll(tags);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
