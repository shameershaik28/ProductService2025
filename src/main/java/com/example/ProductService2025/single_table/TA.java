package com.example.ProductService2025.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@DiscriminatorValue("st_tas")
public class TA extends User {
    private int numOfQuestions;
    private String college;
}
