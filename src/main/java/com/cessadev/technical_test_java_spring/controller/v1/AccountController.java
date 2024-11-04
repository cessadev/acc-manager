package com.cessadev.technical_test_java_spring.controller.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    /*
    * FUNCTIONS
    *
    * Endpoints
    * 1. Create new account
    * 2. Update existing account data
    * 3. Check status of an account by -ACCOUNT NUMBER-
    *
    * VALIDATE:
    * - Validate that the initial balance is not negative when creating an account.
    * */

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
     *     "id": 123e4567-h09b-18m3-aty6-44174000,
     *     "ownerName": "John Doe",
     *     "status": "ACTIVE"
     *   },
     *   {
     *     "id": 74m3e7-e89b-12d3-a456-qw3o44o003,
     *     "ownerName": "Jane Smith",
     *     "status": "ACTIVE"
     *   }
     * ]
     */


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
