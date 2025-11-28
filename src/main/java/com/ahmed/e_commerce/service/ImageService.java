package com.ahmed.e_commerce.service;

import com.ahmed.e_commerce.Dto.ImageResponseDto;
import com.ahmed.e_commerce.Entity.Image;
import com.ahmed.e_commerce.Entity.Product;
import com.ahmed.e_commerce.repository.ImageRepository;
import com.ahmed.e_commerce.repository.ProductRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    private final ProductRepository productRepository;

    public ImageService(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    public List<ImageResponseDto> saveImageFiles(Long productId, List<MultipartFile> files){
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        List<ImageResponseDto> savedImagesDto = new ArrayList<>();
        for (MultipartFile file : files){
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                Image savedImage = imageRepository.save(image);

                String buildImageUrl = "api/images/image/download/";
                String imageUrl = buildImageUrl + image.getFileName();
                image.setImageUrl(imageUrl);

                savedImage.setImageUrl(imageUrl);
                imageRepository.save(savedImage);
                savedImagesDto.add(new ImageResponseDto(savedImage.getId(), savedImage.getImageUrl(), savedImage.getFileName()));
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return savedImagesDto;
    }

    public void deleteImageFile(Long imageId){
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new RuntimeException("Image not found"));
        imageRepository.delete(image);
    }

    @Transactional(readOnly = true)
    public Resource downloadImage(Long imageId) throws SQLException{
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new RuntimeException("Image not found"));
        byte[] imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
        return new ByteArrayResource(imageBytes);
    }

    @Transactional(readOnly = true)
    public List<ByteArrayResource> downloadAllImages(Long productId) throws SQLException{
        List<ByteArrayResource> resources = new ArrayList<>();
        List<Image> images = imageRepository.findAllByProductId(productId);
        for (Image image : images){
            byte[] imageBytes = image.getImage().getBytes(1, (int) image.getImage().length());
            resources.add(new ByteArrayResource(imageBytes));
        }
        return resources;
    }

    public List<Image> getImagesByProductId(Long productId){
        return imageRepository.findAllByProductId(productId);
    }

    public Image getImageById(Long imageId){
        return imageRepository.findById(imageId).orElseThrow(() -> new RuntimeException("Image not found"));
    }
}
