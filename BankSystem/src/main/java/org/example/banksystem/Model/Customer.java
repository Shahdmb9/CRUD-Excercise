package org.example.banksystem.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private String userName;
    private String id;
    private double balance;
}
