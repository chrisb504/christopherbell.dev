package dev.christopherbell.blog.controllers;

import dev.christopherbell.blog.models.global.Response;
import dev.christopherbell.blog.services.PhotoGalleryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class PhotoGalleryController {

  private final PhotoGalleryService photoGalleryService;

  @GetMapping(value = "/api/photogallery/images", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Response> getImages(HttpServletRequest request) {
    var response = this.photoGalleryService.getAllImages(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}