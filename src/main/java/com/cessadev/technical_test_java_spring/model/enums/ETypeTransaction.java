package com.cessadev.technical_test_java_spring.model.enums;

/**
 * Enumeration representing possible types of transactions in an account.
 */
public enum ETypeTransaction {

    /** A deposit transaction, adding funds to an account */
    DEPOSIT,

    /** A withdrawal transaction, removing funds from an account */
    WITHDRAW,

    /** A transfer transaction, moving funds between accounts */
    TRANSFER
}
