package io.nirahtech.erp.bank;

public class BankDetails {
    private final AccountHolder accountHolder;
    private final String bankName;
    private final BankCode bankCode;
    private final String ticketOfficeCode;
    private final AccountNumber bankAccountNumber;
    private final String bankDetailsCode;
    private final InternationalBankAccountNumber iban;
    private final BankIdentifierCode bic;

    public BankDetails(AccountHolder accountHolder, String bankName, BankCode bankCode, String ticketOfficeCode,
                       AccountNumber bankAccountNumber, String bankDetailsCode, InternationalBankAccountNumber iban,
                       BankIdentifierCode bic) {
        this.accountHolder = accountHolder;
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.ticketOfficeCode = ticketOfficeCode;
        this.bankAccountNumber = bankAccountNumber;
        this.bankDetailsCode = bankDetailsCode;
        this.iban = iban;
        this.bic = bic;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public BankCode getBankCode() {
        return bankCode;
    }

    public String getTicketOfficeCode() {
        return ticketOfficeCode;
    }

    public AccountNumber getBankAccountNumber() {
        return bankAccountNumber;
    }

    public String getBankDetailsCode() {
        return bankDetailsCode;
    }

    public InternationalBankAccountNumber getIban() {
        return iban;
    }

    public BankIdentifierCode getBic() {
        return bic;
    }
}
