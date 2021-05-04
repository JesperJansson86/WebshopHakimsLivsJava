package com.example.hakimlivs.controllers;

import com.example.hakimlivs.models.*;
import com.example.hakimlivs.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = ("/api/product"))
public class ProductController {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private BrandRepository brandRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private UnitRepository unitRepo;
    @Autowired
    private ImageRepository imageRepo;

    @GetMapping(path = "/add")
    public String addProduct(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam int inventory,
            @RequestParam int quantity,
            @RequestParam double size,
            @RequestParam String brand,
            @RequestParam String category,
            @RequestParam String unit,
            @RequestParam boolean visibility,
            @RequestParam (defaultValue = "noimage.jpg", required = false) String image
    ) {
        Product p = new Product();
        p.setTitle(title);
        p.setDescription(description);
        p.setPrice(price);
        p.setInventory(inventory);
        p.setQuantity(quantity);
        p.setSize(size);

        // Om id till Brand inte finns kommer ett nytt Brand att skapas upp.
        if (brandRepo.existsByBrand(brand)) {
            p.setBrand(brandRepo.findByBrand(brand).get());
        } else {
            Brand b = new Brand();
            b.setBrand(brand);
            p.setBrand(b);
        }

        // Om id till Category inte finns kommer ett nytt Category att skapas upp.
        if (categoryRepo.existsByCategory(category)) {
            p.setCategory(categoryRepo.findCategoryBycategory(category).get());
        } else {
            Category c = new Category();
            c.setCategory(category);
            p.setCategory(c);
        }
        // Om id till Unit inte finns kommer ett nytt Unit att skapas upp.
        if (unitRepo.existsByUnit(unit)) {
            p.setUnit(unitRepo.findByUnit(unit).get());
        } else {
            Unit u = new Unit();
            u.setUnit(unit);
            p.setUnit(u);
        }
        imageRepo.save(new Image(image,p));
        p.setVisibility(visibility);


        productRepo.save(p);

        return String.format("%s has been added", title);
    }

    @GetMapping(path = "/findById")
    public Product getProductById(@RequestParam Long id) {
        return productRepo.findById(id).get();
    }

    @GetMapping(path = "/deleteById")
    public String deleteProductById(@RequestParam Long id) {
        productRepo.deleteById(id);
        return String.format("Product with id:%s has been deleted", id);
    }

    @GetMapping(path = "/all")
    public Iterable<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @PostMapping("/update")
    public String updateProduct(@RequestBody Product p) {

        // Om id till produkten inte finns kommer produkten att skapas upp, annars uppdateras produkten.


        if (!productRepo.existsById(p.getId())) {
            addProduct(p.getTitle(), p.getDescription(), p.getPrice(), p.getInventory(), p.getQuantity(), p.getSize(), p.getBrand().getBrand(), p.getCategory().getCategory(), p.getUnit().getUnit(), p.isVisibility(), p.getImageList().get(0).getImage());
            return "Product created";
        } else {
            Product updateP = getProductById(p.getId());
            updateP.setTitle(p.getTitle());
            updateP.setDescription(p.getDescription());
            updateP.setPrice(p.getPrice());
            updateP.setInventory(p.getInventory());
            updateP.setQuantity(p.getQuantity());
            updateP.setSize(p.getSize());
            updateP.setBrand(brandRepo.findById(p.getBrand().getId()).get());
            updateP.setCategory(categoryRepo.findById(p.getCategory().getId()).get());
            updateP.setUnit(unitRepo.findById(p.getUnit().getId()).get());
            updateP.setVisibility(p.isVisibility());
            updateP.setImageList(p.getImageList());
            System.out.println(p.getImageList());
            productRepo.save(updateP);


            return "Product updated";
        }
    }
}
