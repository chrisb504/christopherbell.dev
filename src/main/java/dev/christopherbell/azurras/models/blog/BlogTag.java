package dev.christopherbell.azurras.models.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
public class BlogTag implements Serializable {
    private Date creationDate;
    private Integer id;
    private String name;
}
