package dev.christopherbell.www.personalsite.models.photogallery;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image implements Serializable {
    private String dateAdded;
    private String desc;
    private int id;
    private String name;
    private String path;
}
