package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Address;
import com.example.hakimlivs.models.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
    public Image findImageByimage(String a);
    public boolean existsByimage(String s);
}
