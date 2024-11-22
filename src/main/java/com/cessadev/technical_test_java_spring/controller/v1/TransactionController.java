package com.cessadev.technical_test_java_spring.controller.v1;

import com.cessadev.technical_test_java_spring.model.dto.ErrorTransactionGenericDTO;
import com.cessadev.technical_test_java_spring.model.dto.TransferDTORequest;
import com.cessadev.technical_test_java_spring.service.ITransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * TransactionController handles HTTP requests for managing transactions,
 * including deposits, withdrawals, and transfers.
 */
@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final ITransactionService transactionService;

    /**
     * Constructor to initialize the TransactionController with the required service.
     *
     * @param transactionService the transaction service to handle business logic.
     */
    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Endpoint v1:
     * POST - http://localhost:8080/api/v1/transactions/deposit/{accountNumber}
     *
     * Endpoint to perform a deposit into an account.
     *
     * @param accountNumber the unique identifier of the account to deposit into.
     * @param amount        the amount to deposit; provided in the request body.
     * @return a success message if the deposit is completed, or an error message if the deposit fails.
     */
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

    /**
     * Endpoint v1:
     * POST - http://localhost:8080/api/v1/transactions/withdraw/{accountNumber}
     *
     * Endpoint to perform a withdrawal from an account.
     *
     * @param accountNumber the unique identifier of the account to withdraw from.
     * @param amount        the amount to withdraw; provided in the request body.
     * @return a success message if the withdrawal is completed, or an error message if the withdrawal fails.
     */
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

    /**
     * Endpoint v1:
     * POST - http://localhost:8080/api/v1/transactions/transfer
     *
     * Endpoint to transfer funds between two accounts.
     *
     * @param transferDTORequest the transfer details, including source account, destination account, and amount.
     * @return a success message if the transfer is completed, or an error message if the transfer fails.
     */
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(
            @RequestBody TransferDTORequest transferDTORequest) {
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
