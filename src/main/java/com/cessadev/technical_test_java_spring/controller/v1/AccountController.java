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
    * 1. CREATE NEW ACCOUNT
    * 2. UPDATE EXISTING ACCOUNT DATA
    * 3. CHECK THE STATUS OF AN ACCOUNT BY -ACCOUNT NUMBER-
    *
    * VALIDATE:
    *
    * -
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
     * http://localhost:8080/api/v1/account/all-accounts
     *
     * Endpoint to obtain the weather of a specific city.
     *
     * @param city Name of the city. It cannot be empty and must contain only alphabetic characters.
     * @return A ResponseEntity object with weather information for the requested city.
     */
}
