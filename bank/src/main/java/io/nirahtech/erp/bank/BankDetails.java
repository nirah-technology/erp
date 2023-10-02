package io.nirahtech.erp.bank;

public record BankDetails(
    AccountHolder accountHolder,
    String bankName,
    BankCode bankCode,
    String ticketOfficeCode,
    AccountNumber bankAccountNumber,
    String bankDetailsCode,
    InternationalBankAccountNumber iban,
    BankIdentifierCode bic
) {
    
}
