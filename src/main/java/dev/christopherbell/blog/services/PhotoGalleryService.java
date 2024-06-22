package dev.christopherbell.blog.services;

import dev.christopherbell.blog.configs.Constants;
import dev.christopherbell.blog.configs.properties.ImageProperties;
import java.util.Arrays;
import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.christopherbell.blog.models.global.Message;
import dev.christopherbell.blog.models.photogallery.PhotoGalleryResponse;

@Service
public class PhotoGalleryService {
    private final Logger LOG = LoggerFactory.getLogger(PhotoGalleryService.class);
    private final ImageProperties imageProperties;

    @Autowired
    public PhotoGalleryService(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }

    public PhotoGalleryResponse getAllImages(HttpServletRequest request) {
        final var images = imageProperties.getImages();
        LOG.info("Request's address: {}", request.getRemoteAddr());
        if (Objects.isNull(images)) {
            LOG.error("No references to images found in config file");
            var messages = Arrays.asList(new Message("PhotoGalleryService.Response.NoImages", "No Images Found"));
            return new PhotoGalleryResponse(null, messages, Constants.STATUS_FAILURE);
        }
        return new PhotoGalleryResponse(images, null, Constants.STATUS_SUCCESS);
    }
}