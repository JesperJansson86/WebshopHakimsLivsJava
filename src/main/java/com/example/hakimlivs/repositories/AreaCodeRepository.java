package com.example.hakimlivs.repositories;

import com.example.hakimlivs.models.AreaCode;
import org.springframework.data.repository.CrudRepository;


public interface AreaCodeRepository  extends CrudRepository<AreaCode, Long> {
    public AreaCode findAreaCodeByareaCode(String a);
    public boolean existsByAreaCode(String a);

}
