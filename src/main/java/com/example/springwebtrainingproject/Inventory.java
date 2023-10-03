package com.example.springwebtrainingproject;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Inventory {
    private String category;
    private String productName;
    private Double price;
    private Double discount;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String orderDate;
    private String id;
    public Inventory() {
        id = UUID.randomUUID().toString();
    }

}
