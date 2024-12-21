package com.assignment.receiptprocessor.controller;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.receiptprocessor.model.Item;
import com.assignment.receiptprocessor.model.Receipt;
import com.assignment.receiptprocessor.repository.ReceiptRepository;
import com.assignment.receiptprocessor.service.ReceiptService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/process")
    public ResponseEntity<?> saveReceipts(@Valid @RequestBody(required = true) Receipt receipt) {

        for (Item item : receipt.getItems()) {
            item.setReceipt(receipt);
        }
        Receipt savedReceipt = null;
        try {
            savedReceipt = receiptService.saveReceipt(receipt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Unable to save receipt"));
        }

        return ResponseEntity.ok(Map.of("id", receipt.getReceiptId()));

    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable(required = true) UUID id) {

        int points = 0;
        try {
            points = receiptService.calculatePoints(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));

        }

        return ResponseEntity.ok(Map.of("points : ", points));

    }

}
