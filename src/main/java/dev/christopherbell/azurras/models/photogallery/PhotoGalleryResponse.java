package dev.christopherbell.azurras.models.photogallery;

import java.util.List;

import dev.christopherbell.azurras.models.global.Message;
import dev.christopherbell.azurras.models.global.Response;
import lombok.Getter;

@Getter
public class PhotoGalleryResponse extends Response {
    private List<Image> images;

    public PhotoGalleryResponse(List<Image> images, List<Message> messages, String status) {
        super(messages, status);
        this.images = images;
    }
}
