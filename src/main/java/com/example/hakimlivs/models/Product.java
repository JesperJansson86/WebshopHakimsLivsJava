package com.example.hakimlivs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title = "";

    private String description = "";

    private double price = 0;

    private int inventory = 0;

    private int quantity = 0;

    private double size = 0.0;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "unit_id")
    private Unit unit;

//    @OneToMany(cascade = {CascadeType.PERSIST})
//    @JoinColumn(name = "product_id")
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy ="product")
    private List<Image> imageList;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "product_id")
    private List<Order_Contains> productList;

    private boolean visibility = false;

    public Product(String title, String description, double price, int inventory, int quantity, int size, Brand brand, Category category, Unit unit, boolean visibility) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.quantity = quantity;
        this.size = size;
        this.brand = brand;
        this.category = category;
        this.unit = unit;
        this.visibility = visibility;
    }

    public Product(String title, String description, double price, int inventory, int quantity, int size, Brand brand, Category category, Unit unit, boolean visibility, List image) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.quantity = quantity;
        this.size = size;
        this.brand = brand;
        this.category = category;
        this.unit = unit;
        this.visibility = visibility;
        this.imageList = image;
    }
}
