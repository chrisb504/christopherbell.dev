package dev.christopherbell.azurras.models.blog;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "cbBlog_table")
public class BlogPost {
    private String author;
    private String contentText;
    private Date creationDate;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String imagePath;
    @OneToMany(targetEntity = BlogTag.class, fetch = FetchType.EAGER)
    private List<BlogTag> tags;
    private String title;

    public BlogPost() {
        this.author = "";
        this.contentText = "";
        this.creationDate = new Date();
        this.description = "";
        this.imagePath = "";
        this.tags = new ArrayList<>();
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePage(String imagePath) {
        this.imagePath = imagePath;
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
