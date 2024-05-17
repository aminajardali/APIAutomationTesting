package Utility;

public class CreateDepositRequest {
    private String beneficiary;
    private String tokenizedBalance;
    private String commercialDepositCurrency;
    private String tokenizedDepositCurrency;
    private String accountNumber;
    private String accountType;

    // Getters and Setters

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getTokenizedBalance() {
        return tokenizedBalance;
    }

    public void setTokenizedBalance(String tokenizedBalance) {
        this.tokenizedBalance = tokenizedBalance;
    }

    public String getCommercialDepositCurrency() {
        return commercialDepositCurrency;
    }

    public void setCommercialDepositCurrency(String commercialDepositCurrency) {
        this.commercialDepositCurrency = commercialDepositCurrency;
    }

    public String getTokenizedDepositCurrency() {
        return tokenizedDepositCurrency;
    }

    public void setTokenizedDepositCurrency(String tokenizedDepositCurrency) {
        this.tokenizedDepositCurrency = tokenizedDepositCurrency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}

