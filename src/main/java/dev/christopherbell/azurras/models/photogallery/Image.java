package dev.christopherbell.azurras.models.photogallery;

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
    private String name;
    private String path;
    private String desc;
    private String dateAdded;
}
