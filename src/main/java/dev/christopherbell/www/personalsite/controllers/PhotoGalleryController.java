package dev.christopherbell.www.personalsite.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.christopherbell.www.personalsite.models.global.Response;
import dev.christopherbell.www.personalsite.services.PhotoGalleryService;

@RestController
public class PhotoGalleryController {
    private final Logger LOG = LoggerFactory.getLogger(PhotoGalleryController.class);
    private final PhotoGalleryService photoGalleryService;

    @Autowired
    public PhotoGalleryController(PhotoGalleryService photoGalleryService) {
        this.photoGalleryService = photoGalleryService;
    }

    @GetMapping(value = "/api/photogallery/images", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getImages(HttpServletRequest request) {
        if (LOG.isDebugEnabled()) {
            LOG.info("Request received to return all photo gallery images");
        }
        var response = this.photoGalleryService.getAllImages(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}