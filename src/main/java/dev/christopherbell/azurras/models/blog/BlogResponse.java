package dev.christopherbell.azurras.models.blog;

import java.util.ArrayList;
import java.util.List;

public class BlogResponse {
    private String message;
    private String status;
    private List<BlogPost> blogPostPayLoad;
    private List<BlogTag> blogTagPayload;

    public BlogResponse() {
        this.message = "";
        this.status = "";
        this.blogPostPayLoad = new ArrayList<>();
        this.blogTagPayload = new ArrayList<>();
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

    public List<BlogPost> getBlogPostPayload() {
        return new ArrayList<>(this.blogPostPayLoad);
    }

    public void setBlogPostPayLoad(final List<BlogPost> blogPostPayLoad) {
        this.blogPostPayLoad.addAll(blogPostPayLoad);
    }

    public List<BlogTag> getBlogTagPayload() {
        return new ArrayList<>(this.blogTagPayload);
    }

    public void setBlogTagPayLoad(final List<BlogTag> blogTagPayload) {
        this.blogTagPayload.addAll(blogTagPayload);
    }
}
