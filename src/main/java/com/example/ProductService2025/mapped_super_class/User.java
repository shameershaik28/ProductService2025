package com.example.ProductService2025.mapped_super_class;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class User {
    @Id
    private int  id;
    private String name;
    private String email;
    private String password;
}
