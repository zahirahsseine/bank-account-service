package org.side.bankaccountservice.service;

import org.side.bankaccountservice.dto.BankAccountRequestDTO;
import org.side.bankaccountservice.dto.BankAccountResponseDTO;
import org.side.bankaccountservice.entities.BankAccount;

public interface AccountService {
    BankAccountResponseDTO AddAccount(BankAccountRequestDTO bankAcountDTOResponse);
}
