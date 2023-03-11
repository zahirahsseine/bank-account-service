package org.side.bankaccountservice.service;

import org.side.bankaccountservice.dto.BankAccountRequestDTO;
import org.side.bankaccountservice.dto.BankAccountResponseDTO;
import org.side.bankaccountservice.entities.BankAccount;
import org.side.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private BankAccountRepository bankAccountRepository;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository)
    {
        this.bankAccountRepository = bankAccountRepository;
    }
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

    @Override
    public List<BankAccountResponseDTO> GetAllAccount() {
        var bankAccounts = bankAccountRepository.findAll();

        List<BankAccountResponseDTO> listBankResponseDTO = new ArrayList<>();
        bankAccounts.forEach(bankAccount->
            listBankResponseDTO.add( BankAccountResponseDTO.builder()
                    .id(bankAccount.getId())
                    .createAt(bankAccount.getCreateAt())
                    .balance(bankAccount.getBalance())
                    .type(bankAccount.getType())
                    .currency(bankAccount.getCurrency())
                    .build())
        );

        return listBankResponseDTO;
    }

    @Override
    public BankAccountResponseDTO GetAccount(String id) {
        BankAccount bankAccount =  bankAccountRepository.findById(id)
                                                        .orElseThrow();
        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .id(bankAccount.getId())
                .createAt(bankAccount.getCreateAt())
                .balance(bankAccount.getBalance())
                .type(bankAccount.getType())
                .currency(bankAccount.getCurrency())
                .build();
        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO UpdateAccount(String id, BankAccountRequestDTO bankAcountDTORequest) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow();
        if(bankAcountDTORequest.getBalance()!=null) bankAccount.setBalance(bankAcountDTORequest.getBalance());
        if(bankAcountDTORequest.getType()!=null) bankAccount.setType(bankAcountDTORequest.getType());
        if(bankAcountDTORequest.getCurrency()!=null) bankAccount.setCurrency(bankAcountDTORequest.getCurrency());

        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder()
                .id(saveBankAccount.getId())
                .createAt(saveBankAccount.getCreateAt())
                .balance(saveBankAccount.getBalance())
                .type(saveBankAccount.getType())
                .currency(saveBankAccount.getCurrency())
                .build();

        return bankAccountResponseDTO;
    }

    @Override
    public void DeleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }
}
