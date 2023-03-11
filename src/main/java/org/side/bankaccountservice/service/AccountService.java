package org.side.bankaccountservice.service;

import org.side.bankaccountservice.dto.BankAccountRequestDTO;
import org.side.bankaccountservice.dto.BankAccountResponseDTO;

import java.util.List;

public interface AccountService {
    BankAccountResponseDTO AddAccount(BankAccountRequestDTO bankAcountDTORequest);
    List<BankAccountResponseDTO> GetAllAccount();
    BankAccountResponseDTO GetAccount(String id);
    BankAccountResponseDTO UpdateAccount(String id, BankAccountRequestDTO bankAcountDTORequest);
    void DeleteAccount(String id);
}
