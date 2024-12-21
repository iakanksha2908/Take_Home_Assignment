package com.assignment.receiptprocessor.model;
import java.time.*;
import java.util.*;
public class Receipt {

    @Id
    private String id;
    
    private String retailer;
    private LocalDate purchaseDate;
    private LocalTime purchaseTime;
    private List<Item> items;
    private double total;

}