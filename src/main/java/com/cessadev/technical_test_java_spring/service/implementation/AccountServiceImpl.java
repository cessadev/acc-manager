package com.cessadev.technical_test_java_spring.service.implementation;

import com.cessadev.technical_test_java_spring.model.AccountModel;
import com.cessadev.technical_test_java_spring.model.dto.*;
import com.cessadev.technical_test_java_spring.model.enums.EStatusAccount;
import com.cessadev.technical_test_java_spring.persistence.dao.IAccountDAO;
import com.cessadev.technical_test_java_spring.service.IAccountService;
import com.cessadev.technical_test_java_spring.util.account.AccountNumberGenerator;
import com.cessadev.technical_test_java_spring.util.account.ValidateInitialBalance;
import com.cessadev.technical_test_java_spring.util.account.mapper.IAccountMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * AccountServiceImpl is the implementation of the IAccountService interface.
 * This service contains the business logic for managing accounts, such as:
 * - Retrieving all accounts.
 * - Creating new accounts with validations and unique account numbers.
 * - Updating existing accounts.
 * - Checking the status of accounts by account number.
 *
 * The service relies on IAccountDAO for data access operations and IAccountMapper for
 * mapping between entity and DTO objects.
 */
@Service
public class AccountServiceImpl implements IAccountService {

    private final IAccountDAO accountDAO;
    private final IAccountMapper accountMapper;

    /**
     * Constructor for AccountServiceImpl.
     *
     * @param accountDAO   the data access object for account operations.
     * @param accountMapper the mapper for converting between DTOs and entities.
     */
    public AccountServiceImpl(IAccountDAO accountDAO, IAccountMapper accountMapper) {
        this.accountDAO = accountDAO;
        this.accountMapper = accountMapper;
    }

    /**
     * Retrieves a list of all accounts and maps them to DTO responses.
     *
     * @return a list of AccountDTOResponse objects representing all stored accounts.
     */
    @Override
    public List<AccountDTOResponse> findAllAccounts() {
        List<AccountModel> accountModelList = accountDAO.findAllAccounts();
        return accountModelList.stream().map(
                accountMapper::toDTO
        ).toList();
    }

    /**
     * Creates a new account with unique account number validation and initial balance checks.
     *
     * @param accountDTORequest the details of the account to create.
     * @throws IllegalArgumentException if the initial balance is negative.
     */
    @Override
    public void createAccount(CreateAccountDTORequest accountDTORequest) {
        AccountModel accountModel = accountMapper.toEntity(accountDTORequest);

        ValidateInitialBalance.validateInitialBalance(accountModel.getBalance());

        String accountNumber;
        do {
            accountNumber = AccountNumberGenerator.generateAccountNumber();
        } while (accountDAO.existsByAccountNumber(accountNumber));

        accountModel.setAccountNumber(accountNumber);

        accountDAO.createAccount(accountModel);
    }

    /**
     * Updates an existing account by its account number.
     *
     * @param accountNumber the unique account number of the account to update.
     * @param request       the details to update the account with.
     * @return an UpdateAccountDTOResponse object indicating success or failure,
     * and the updated account information if applicable.
     */
    @Override
    public UpdateAccountDTOResponse updateAccount(String accountNumber, UpdateAccountDTORequest request) {
        Optional<AccountModel> accountExist = accountDAO.findByAccountNumber(accountNumber);

        if (accountExist.isEmpty()) {
            return new UpdateAccountDTOResponse("Account Not Found", null);
        }

        AccountModel accountModel = accountExist.get();

        if (request.ownerName() != null && request.status() != null) {
            accountModel.setOwnerName(request.ownerName());
            accountModel.setStatus(request.status());
        }

        AccountModel accountUpdated = accountDAO.updateAccountWithReturn(accountModel);
        AccountDTOResponse accountDTOResponse = accountMapper.toDTO(accountUpdated);

        return new UpdateAccountDTOResponse("Account updated successfully", accountDTOResponse);
    }

    /**
     * Retrieves the status of an account by its account number.
     *
     * @param accountNumber the unique account number to search for.
     * @return a StatusAccountDTOResponse object containing the account's status
     * or a message if the account does not exist.
     */
    @Override
    public StatusAccountDTO findStatusAccount(String accountNumber) {
        Optional<EStatusAccount> statusAccount = accountDAO.findStatusByAccountNumber(accountNumber);

        if (statusAccount.isEmpty()) {
            return new StatusAccountDTO("Account Not Exist", accountNumber, null);
        }

        EStatusAccount status = statusAccount.get();
        return new StatusAccountDTO("Current account status", accountNumber, status);
    }
}
