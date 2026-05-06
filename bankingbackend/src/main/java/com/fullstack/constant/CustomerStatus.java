package com.fullstack.constant;

public enum CustomerStatus {

    ACTIVE,            // Customer is active and can perform all operations

    INACTIVE,          // Customer exists but not yet activated

    PENDING_KYC,       // KYC verification is pending

    KYC_VERIFIED,      // KYC completed successfully

    SUSPENDED,         // Temporarily blocked due to suspicious activity

    BLOCKED,           // Permanently blocked (fraud, policy violation)

    CLOSED,            // Account closed by customer or bank

    DORMANT            // No activity for long time (e.g., 12 months)
}