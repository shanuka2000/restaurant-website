package com.example.signature_cuisine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity extends UserEntity {
}
