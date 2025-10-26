package com.example.ProductService2025.single_table;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("st_learners")
public class Learner extends User
{
    private String college;
    private String company;
}
