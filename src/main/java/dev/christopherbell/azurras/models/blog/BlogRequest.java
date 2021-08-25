package dev.christopherbell.azurras.models.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {
    private String author;
    private String contentText;
    private String description;
    private String imagePath;
    private String tags;
    private String title;
}
