package com.example.ProductService2025.cardinalities.manytoone;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "learners_3")
public class Learners {
    @Id
    Long id;
    String name;
    @ManyToOne
    Batch b;
}
