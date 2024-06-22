package dev.christopherbell.blog.models.photogallery;

import dev.christopherbell.blog.models.global.Message;
import dev.christopherbell.blog.models.global.Response;
import java.util.List;

import lombok.Getter;

@Getter
public class PhotoGalleryResponse extends Response {
    private List<Image> images;

    public PhotoGalleryResponse(List<Image> images, List<Message> messages, String status) {
        super(messages, status);
        this.images = images;
    }
}
