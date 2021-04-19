package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.Content;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lukas Aronsson
 * Date: 19/04/2021
 * Time: 09:55
 * Project: WebshopHakimsLivsJava
 * Copyright: MIT
 **/
public interface ContentRepository extends CrudRepository<Content, Long> {
}
