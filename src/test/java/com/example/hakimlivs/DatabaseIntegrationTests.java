package com.example.hakimlivs;

import com.example.hakimlivs.models.Brand;
import com.example.hakimlivs.repositories.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class DatabaseIntegrationTests {





        @Mock
        BrandRepository mockRepository;

        @BeforeEach
        public void init() {
            Brand kellogs = new Brand("Kellogs");
        }
        @Test
        void getBrand() {
            Brand mockBrand = new Brand("Kellogs");
            when(mockRepository.findByBrand("Kellogs")).thenReturn(Optional.of(mockBrand));
           Optional response = mockRepository.findByBrand("Kellogs");
           Brand hej = (Brand) response.get();




           assertEquals(hej.getBrand(),"Kellogs");
            }


}
