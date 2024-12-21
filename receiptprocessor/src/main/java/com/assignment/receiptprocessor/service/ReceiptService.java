package com.assignment.receiptprocessor.service;

import java.time.LocalTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.receiptprocessor.model.Item;
import com.assignment.receiptprocessor.model.Receipt;
import com.assignment.receiptprocessor.repository.ReceiptRepository;

import jakarta.validation.Valid;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    public Receipt saveReceipt(@Valid Receipt receipt) {

        double total = 0.0;
        List<Item> items = receipt.getItems();
        for (Item item : items) {
            total += item.getPrice();
        }
        receipt.setTotal(total);
        return receiptRepository.save(receipt);

    }

    public int calculatePoints(UUID id) {

        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No receipt found for that ID."));
        int points = 0;

        int i = 0;
        String retailerName = receipt.getRetailer();

        while (i < retailerName.length()) {
            if (Character.isLetterOrDigit(retailerName.charAt(i))) {

                points += 1;
            }
            i++;
        }

        if (Math.abs(receipt.getTotal() - Math.round(receipt.getTotal())) < 0.0001) {
            points += 50;

        }

        if (Math.abs(receipt.getTotal() % 0.25) < 0.0001) {
            points += 25;

        }

        int itemCount = receipt.getItems().size();
        points += (5 * (itemCount / 2));

        List<Item> items = receipt.getItems();

        for (Item item : items) {

            if (item.getShortDescription().trim().length() % 3 == 0) {
                points += (int) Math.ceil(item.getPrice() * 0.2);
            }
        }

        if (receipt.getPurchaseDate().getDayOfMonth() % 2 != 0) {
            points += 6;
        }

        if (receipt.getPurchaseTime().isAfter(LocalTime.of(14, 0))
                && receipt.getPurchaseTime().isBefore(LocalTime.of(16, 0))) {
            points += 10;

        }

        return points;

    }

}
