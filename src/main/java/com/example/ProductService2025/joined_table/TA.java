package com.example.ProductService2025.joined_table;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name="jt_tas")
@PrimaryKeyJoinColumn(name= "user_id")
public class TA extends User {
    private int numOfQuestions;
    private String college;
}
