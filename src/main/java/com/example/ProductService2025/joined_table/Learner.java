package com.example.ProductService2025.joined_table;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name="jt_learners")
@PrimaryKeyJoinColumn(name= "user_id")
public class Learner extends User
{
    private String college;
    private String company;
}
