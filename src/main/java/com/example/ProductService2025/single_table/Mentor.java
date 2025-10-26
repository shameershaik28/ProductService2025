package com.example.ProductService2025.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("st_mentors")
public class Mentor extends User {
    private double avg_rating;
    private String currentCompany;
}
