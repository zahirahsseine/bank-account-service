package org.side.bankaccountservice.service;

import org.side.bankaccountservice.dto.BankAccountRequestDTO;
import org.side.bankaccountservice.dto.BankAccountResponseDTO;
import org.side.bankaccountservice.entities.BankAccount;
import org.side.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private BankAccountRepository bankAccountRepository;
    @Override
    public BankAccountResponseDTO AddAccount(BankAccountRequestDTO bankAcountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAcountDTO.getBalance())
                .type(bankAcountDTO.getType())
                .currency(bankAcountDTO.getCurrency())
                .build();

        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .id(saveBankAccount.getId())
                .createAt(new Date())
                .balance(saveBankAccount.getBalance())
                .type(saveBankAccount.getType())
                .currency(saveBankAccount.getCurrency())
                .build();
        return bankAccountResponseDTO;
    }
}
