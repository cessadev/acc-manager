package com.cessadev.technical_test_java_spring.controller.v1;

import com.cessadev.technical_test_java_spring.model.dto.*;
import com.cessadev.technical_test_java_spring.model.enums.ETypeTransaction;
import com.cessadev.technical_test_java_spring.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Endpoint v1:
     * GET - http://localhost:8080/api/v1/accounts
     *
     * Retrieves a list of all existing accounts.
     *
     * @return a ResponseEntity containing a list of stored AccountModels.
     *         Possible responses:
     *         - 200 OK: if the accounts are retrieved successfully.
     *         - 204 No Content: if there are no accounts to display.
     *         - 500 Internal Server Error: if there is a server error.
     *
     * Example response:
     * [
     *   {
     *     "id": 123x-h09b-18m3-4417,
     *     "ownerName": "John Doe",
     *     "status": "ACTIVE"
     *   },
     *   {
     *     "id": 74m3-e89b-12d3-a456,
     *     "ownerName": "Jane Smith",
     *     "status": "ACTIVE"
     *   }
     * ]
     */
    @GetMapping
    public ResponseEntity<List<AccountDTOResponse>> getAllAccounts() {
        try {
            List<AccountDTOResponse> accounts = accountService.findAllAccounts();
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Endpoint v1:
     * POST - http://localhost:8080/api/v1/accounts
     *
     * Creates a new account for the specified owner.
     *
     * @param accountDTORequest (ownerName) the name of the account owner.
     * @return a message confirming whether the account was created successfully.
     *         Possible responses:
     *         - 201 Created: if the account is created successfully.
     *         - 400 Bad Request: if the input data is invalid.
     *         - 500 Internal Server Error: if there is a server error.
     *
     * Example response:
     * {
     *   "message": "Account created successfully."
     * }
     */
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountDTORequest accountDTORequest) {
        try {
            accountService.createAccount(accountDTORequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ErrorCreateAccountDTO("Error", ex.getMessage()));
        }
    }

    /**
     * Endpoint v1:
     * PUT - http://localhost:8080/api/v1/accounts/{accountNumber}
     *
     * Updates the information of an existing account identified by account number.
     *
     * @param accountNumber the unique identifier for the account to be updated.
     * @param accountDetails an object containing the updated details for the account.
     *                       Fields may include:
     *                       - ownerName: the updated name of the account owner.
     *                       - status: the new status of the account (ACTIVE, CLOSED, SUSPENDED).
     * @return a ResponseEntity indicating the result of the update operation.
     *         Possible responses:
     *         - 200 OK: if the account is updated successfully.
     *         - 400 Bad Request: if the provided data is invalid.
     *         - 404 Not Found: if no account is found with the specified account number.
     *         - 500 Internal Server Error: if there is a server error.
     *
     * Example request body:
     * {
     *   "ownerName": "Updated Name",
     *   "status": "ACTIVE"
     * }
     *
     * Example response:
     * {
     *   "message": "Account updated successfully",
     *   "account": {
     *     "accountNumber": "1234-b2h3-23n3-556k",
     *     "ownerName": "Updated Name",
     *     "status": "ACTIVE"
     *   }
     * }
     */
    @PutMapping("/{accountNumber}")
    public ResponseEntity<UpdateAccountDTOResponse> updateAccountByAccountNumber(
            @PathVariable String accountNumber,
            @RequestBody UpdateAccountDTORequest accountDetails) {
        try {
            UpdateAccountDTOResponse updateAccountDTOResponse = accountService.updateAccount(accountNumber, accountDetails);
            return ResponseEntity.ok(updateAccountDTOResponse);
        } catch (IllegalArgumentException e) {
        // Handles invalid argument errors
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new UpdateAccountDTOResponse("Invalid input: " + e.getMessage(), null));
        } catch (Exception e) {
        // General handling for unexpected errors
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new UpdateAccountDTOResponse("An unexpected error occurred", null));
        }
    }

    /**
     * Endpoint v1:
     * GET - http://localhost:8080/api/v1/accounts/status/{accountNumber}
     *
     * Retrieves the status of an existing account by account number.
     *
     * @param accountNumber the unique identifier for the account.
     * @return a ResponseEntity containing the account status (ACTIVE, CLOSED, SUSPENDED).
     *         Possible responses:
     *         - 200 OK: if the account status is retrieved successfully.
     *         - 404 Not Found: if no account is found with the specified account number.
     *         - 500 Internal Server Error: if there is a server error.
     *
     * Example response:
     * {
     *   "message": "Current account status",
     *   "accountNumber": "1234-5678-9123-4567",
     *   "status": "ACTIVE"
     * }
     */
    @GetMapping("/status/{accountNumber}")
    public ResponseEntity<StatusAccountDTOResponse> getStatusByAccountNumber(
            @PathVariable String accountNumber
    ) {
        try {
            StatusAccountDTOResponse statusAccount = accountService.findStatusAccount(accountNumber);
            return ResponseEntity.ok(statusAccount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StatusAccountDTOResponse("An unexpected error occurred", accountNumber, null));
        }
    }

    /**
     * Endpoint v1:
     * GET - http://localhost:8080/api/v1/accounts/history/{accountNumber}
     *
     * Endpoint to retrieve the transaction history of an account.
     *
     * @param accountNumber   the unique identifier of the account to retrieve the history for.
     * @param startDate       optional query parameter to filter transactions starting from this date.
     * @param endDate         optional query parameter to filter transactions up to this date.
     * @param typeTransaction optional query parameter to filter transactions by type (e.g., DEPOSIT, WITHDRAWAL, TRANSFER).
     *
     * @return a list of {@link TransactionHistoryDTOResponse} objects representing the account's transaction history,
     * or an error response with status 500 if an exception occurs.
     *
     * Example response:
     * [
     *     {
     *         "id": "38d8a441-65e0-4ea7-9027-537632758936",
     *         "typeTransaction": "DEPOSIT",
     *         "amount": 32000.0000,
     *         "accountOrigin": null,
     *         "accountDestination": "abd0-76f9-37a0-4a7a",
     *         "date": "2024-11-20T11:44:08.378816"
     *     },
     *     {
     *         "id": "8aea9cff-d139-4b6d-8b44-ebe796879ff3",
     *         "typeTransaction": "WITHDRAW",
     *         "amount": 10000.0000,
     *         "accountOrigin": "abd0-76f9-37a0-4a7a",
     *         "accountDestination": null,
     *         "date": "2024-11-20T11:44:31.004056"
     *     },
     *     {
     *          "id": "8aea9cff-d139-4b6d-8b44-ebe796879ff3",
     *          "typeTransaction": "DEPOSIT",
     *          "amount": 12000.0000,
     *          "accountOrigin": "abd0-76f9-37a0-4a7a",
     *          "accountDestination": "92m3-mms2-8j23-2mk4",
     *          "date": "2024-11-20T11:44:32.004056"
     *     }
     * ]
     *
     */
    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<TransactionHistoryDTOResponse>> getTransactionHistory(
            @PathVariable String accountNumber,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) ETypeTransaction typeTransaction) {
      try {
          List<TransactionHistoryDTOResponse> transactionHistory = accountService.getTransactionHistory(accountNumber, startDate, endDate, typeTransaction);
          return ResponseEntity.ok(transactionHistory);
      } catch (IllegalArgumentException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
      }
    }
}
