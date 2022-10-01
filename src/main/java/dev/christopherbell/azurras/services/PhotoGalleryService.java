package dev.christopherbell.azurras.services;

import dev.christopherbell.azurras.configs.Constants;
import dev.christopherbell.azurras.configs.properties.ImageProperties;
import dev.christopherbell.azurras.models.global.Message;
import dev.christopherbell.azurras.models.photogallery.PhotoGalleryResponse;

import java.util.Arrays;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoGalleryService {
    private final Logger LOG = LoggerFactory.getLogger(PhotoGalleryService.class);
    private final ImageProperties imageProperties;

    @Autowired
    public PhotoGalleryService(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }

    public PhotoGalleryResponse getAllImages(HttpServletRequest request) {
        var images = imageProperties.getImages();
        LOG.info("Request's address: {}", request.getRemoteAddr());
        if (Objects.isNull(images)) {
            LOG.error("No references to images found in config file");
            var messages = Arrays.asList(new Message("PhotoGalleryService.Response.NoImages", "No Images Found"));
            return new PhotoGalleryResponse(null, messages, Constants.STATUS_FAILURE);
        }
        return new PhotoGalleryResponse(images, null, Constants.STATUS_SUCCESS);
    }
}