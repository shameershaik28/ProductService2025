package com.example.ProductService2025.single_table;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "st_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    private int  id;
    private String name;
    private String email;
    private String password;
}
