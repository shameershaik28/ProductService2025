package com.example.ProductService2025.cardinalities.manytoone;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity(name = "batch_3")
public class Batch {
    @Id
    long id;
    String name;
    @OneToMany
    List<Learners> l;
}
