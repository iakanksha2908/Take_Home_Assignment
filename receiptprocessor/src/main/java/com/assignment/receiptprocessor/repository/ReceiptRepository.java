package com.assignment.receiptprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import com.assignment.receiptprocessor.model.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {

}
