package com.example.hakimlivs.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    String firstName;
    String lastName;
    String address;
    String areaCode;
    String city;
    String email;
    String phoneNumber;
    Long delivery_option_id;
    List<OrderProductResponseDTO> products;
}