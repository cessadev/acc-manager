package com.cessadev.technical_test_java_spring.controller.v1;

import com.cessadev.technical_test_java_spring.model.dto.AccountDTOResponse;
import com.cessadev.technical_test_java_spring.model.dto.CreateAccountDTORequest;
import com.cessadev.technical_test_java_spring.model.dto.UpdateAccountDTORequest;
import com.cessadev.technical_test_java_spring.model.dto.UpdateAccountDTOResponse;
import com.cessadev.technical_test_java_spring.service.IAccountService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * GET - http://localhost:8080/api/v1/account/all-accounts
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
    @GetMapping("/")
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
     * POST - http://localhost:8080/api/v1/account/create-account
     *
     * Creates a new account for the specified owner.
     *
     * @param ownerName the name of the account owner.
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
    @PostMapping("create")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountDTORequest accountDTORequest) {
        try {
            accountService.createAccount(accountDTORequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully");
        } catch (DataIntegrityViolationException e) {
            // Handling data integrity errors (such as duplicates)
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Account number already exists or data is invalid");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the account");
        }
    }

    /**
     * Endpoint v1:
     * PUT - http://localhost:8080/api/v1/accounts/update/{accountNumber}
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
     *     "accountNumber": "123456",
     *     "ownerName": "Updated Name",
     *     "status": "ACTIVE"
     *   }
     * }
     */
    @PutMapping("/update/{accountNumber}")
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
     * GET - http://localhost:8080/api/v1/account/{accountNumber}/status
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
     *   "accountNumber": "1234-5678-9123-4567",
     *   "status": "ACTIVE"
     * }
     */
}
