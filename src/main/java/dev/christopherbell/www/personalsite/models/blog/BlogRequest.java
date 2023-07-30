package dev.christopherbell.www.personalsite.models.blog;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogRequest implements Serializable {
    private String author;
    private String contentText;
    private String description;
    private String imagePath;
    private String tags;
    private String title;
}
