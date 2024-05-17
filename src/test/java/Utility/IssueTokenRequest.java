package Utility;

public class IssueTokenRequest {
    private String owner;
    private String symbol;
    private String amount;
    private String name;
    private String walletAddress;
    private String accountNumber;
    private String tokenizedDepositBeneficiary;

    // Getters and setters
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTokenizedDepositBeneficiary() {
        return tokenizedDepositBeneficiary;
    }

    public void setTokenizedDepositBeneficiary(String tokenizedDepositBeneficiary) {
        this.tokenizedDepositBeneficiary = tokenizedDepositBeneficiary;
    }
}
