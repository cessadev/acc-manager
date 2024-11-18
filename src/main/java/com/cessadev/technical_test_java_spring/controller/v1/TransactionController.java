package com.cessadev.technical_test_java_spring.controller.v1;

import com.cessadev.technical_test_java_spring.model.dto.ErrorTransactionGenericDTO;
import com.cessadev.technical_test_java_spring.model.dto.TransferDTORequest;
import com.cessadev.technical_test_java_spring.service.ITransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit/{accountNumber}")
    public ResponseEntity<?> deposit(
            @PathVariable String accountNumber,
            @RequestBody BigDecimal amount) {
        try {
            transactionService.deposit(accountNumber, amount);
            return ResponseEntity.ok("Deposit successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorTransactionGenericDTO("Error", e.getMessage()));
        }
    }

    @PostMapping("/withdraw/{accountNumber}")
    public ResponseEntity<?> withdraw(
            @PathVariable String accountNumber,
            @RequestBody BigDecimal amount) {
        try {
            transactionService.withdraw(accountNumber, amount);
            return ResponseEntity.ok("Withdraw successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorTransactionGenericDTO("Error", e.getMessage()));
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(
            @RequestBody TransferDTORequest transferDTORequest
            ) {
        try {
            transactionService.transfer(
                    transferDTORequest.sourceAccount(),
                    transferDTORequest.destinationAccount(),
                    transferDTORequest.amount());

            return ResponseEntity.ok("Transfer successful");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorTransactionGenericDTO("Error", e.getMessage()));
        }
    }
}
