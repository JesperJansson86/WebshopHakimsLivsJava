package com.example.hakimlivs.controllers;


import com.example.hakimlivs.models.Image;
import com.example.hakimlivs.models.AreaCode;
import com.example.hakimlivs.repositories.ImageRepository;
import com.example.hakimlivs.repositories.AreaCodeRepository;
import com.example.hakimlivs.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ("/api/Image"))
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
        if (productRepository.existsById(productId)&&productRepository.existsByimageList(image)) {
            return "Denna bild finns redan registrerad på denna produkt.";
        } else {
            if (productRepository.existsById(productId)) {
                Image i = new Image();
//                i.setProduct(productRepository.findById(productId).get());
                i.setImage(image);
                imageRepository.save(i);
                return String.format("%s has been added to product %s ", image, productId);
            } else return "Product id not found.";
        }

    }

    @GetMapping(path = "/byId")
    public Image getImageById(@RequestParam Long id) {
        return imageRepository.findById(id).get();
    }

    @GetMapping(path = "/all")
    public Iterable<Image> getAllImages() {
        return imageRepository.findAll();
    }

}


