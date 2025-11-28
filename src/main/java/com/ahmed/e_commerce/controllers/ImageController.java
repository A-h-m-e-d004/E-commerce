package com.ahmed.e_commerce.controllers;

import com.ahmed.e_commerce.Entity.Image;
import com.ahmed.e_commerce.service.ImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;

    }

    @PostMapping("/image/upload")
    public ResponseEntity<?> saveImageFiles(@RequestParam Long productId, @RequestParam List<MultipartFile> files){
        return ResponseEntity.ok(imageService.saveImageFiles(productId, files));
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(imageService.downloadImage(imageId));
    }

    @GetMapping("/image/downloadAll/{productId}")
    public ResponseEntity<List<ByteArrayResource>> downloadAllImages(@PathVariable long productId) throws SQLException {
        return ResponseEntity.ok(imageService.downloadAllImages(productId));
    }

    @DeleteMapping("/image/delete/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable long imageId){
        imageService.deleteImageFile(imageId);
        return ResponseEntity.ok("Image deleted successfully");
    }
}
