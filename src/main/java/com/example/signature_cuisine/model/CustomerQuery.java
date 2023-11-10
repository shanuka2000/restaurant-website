package com.example.signature_cuisine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerQuery {
    private String fullName;
    private String email;
    private String message;
}
