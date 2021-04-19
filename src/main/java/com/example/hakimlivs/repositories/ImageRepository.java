package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Image;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 09:57
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/
public interface ImageRepository extends CrudRepository<Image, Long> {
}
