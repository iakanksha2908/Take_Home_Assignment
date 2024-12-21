package com.assignment.receiptprocessor.model;

import java.time.*;
import java.util.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;

@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID receiptId;

    @NotBlank(message = "Retailer name is required")
    private String retailer;

    // @NotBlank(message = "Purchase Date is required")
    @NotNull(message = "Purchase Date is required")
    private LocalDate purchaseDate;

    @NotNull(message = "Purchase Time is required")
    private LocalTime purchaseTime;

    @NotEmpty(message = "Item list is required")
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private double total;

    public UUID getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(UUID receiptId) {
        this.receiptId = receiptId;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}