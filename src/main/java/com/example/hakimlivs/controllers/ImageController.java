package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.Category;
import com.example.hakimlivs.models.Image;
import com.example.hakimlivs.models.Message;
import com.example.hakimlivs.repositories.ImageRepository;
import com.example.hakimlivs.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ("/api/image"))
public class ImageController {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping(path = "/add")
    public String addImage(
            @RequestParam String image,
            @RequestParam Long productId
    ) {
        if (imageRepository.existsByimage(image)) {
            return "Denna bild finns redan registrerad.";
        } else {
            if (productRepository.existsById(productId)) {
                Image i = new Image();
                i.setProduct(productRepository.findById(productId).get());
                i.setImage(image);
                imageRepository.save(i);
                return String.format("%s has been added to product %s ", image, productId);
            } else return "Product id not found.";
        }
    }

    @GetMapping(path = "/findById")
    public Image getImageById(@RequestParam Long id) {
        return imageRepository.findById(id).get();
    }

    @GetMapping(path = "/all")
    public Iterable<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @GetMapping(path = "/deleteById")
    public String deleteImageById(@RequestParam Long id) {
        imageRepository.deleteById(id);
        return String.format("Image with id: %s has been deleted", id);
    }
    @PostMapping("/update")
    public Message updateImage(@RequestBody Image c) {
        try {
            if (c.getImage() == null || c.getId() == null)
                throw new IllegalArgumentException("Missing parameter in object");
            if (imageRepository.existsById(c.getId())) {
                Image iExisting = imageRepository.findById(c.getId()).get();
                iExisting.setImage(c.getImage());
                imageRepository.save(iExisting);
                return new Message(true, String.format("%s updated", c.getImage()));
            } else {
                imageRepository.save(c);
                return new Message(true, String.format("%s created", c.getImage()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(false, "Error when processing.");
        }
    }
}


