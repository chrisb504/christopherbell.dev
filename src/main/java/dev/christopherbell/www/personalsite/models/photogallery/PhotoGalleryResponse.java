package dev.christopherbell.www.personalsite.models.photogallery;

import java.util.List;

import dev.christopherbell.www.personalsite.models.global.Message;
import dev.christopherbell.www.personalsite.models.global.Response;
import lombok.Getter;

@Getter
public class PhotoGalleryResponse extends Response {
    private List<Image> images;

    public PhotoGalleryResponse(List<Image> images, List<Message> messages, String status) {
        super(messages, status);
        this.images = images;
    }
}
