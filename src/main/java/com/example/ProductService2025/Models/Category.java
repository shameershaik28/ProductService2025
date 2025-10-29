package com.example.ProductService2025.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue // do NOT specify IDENTITY
    @UuidGenerator // Hibernate 6
    @JdbcTypeCode(SqlTypes.BINARY) // store as BINARY(16)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;
}

