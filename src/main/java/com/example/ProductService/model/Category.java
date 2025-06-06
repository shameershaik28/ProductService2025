package com.example.ProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn (name = "category_id")
    private List<Product> products;


}
