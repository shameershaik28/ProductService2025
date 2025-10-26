package com.example.ProductService2025.joined_table;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name="jt_users")
public class User {
    @Id
    private int  id;
    private String name;
    private String email;
    private String password;
}
