package org.side.bankaccountservice.web;

import org.side.bankaccountservice.dto.BankAccountRequestDTO;
import org.side.bankaccountservice.dto.BankAccountResponseDTO;
import org.side.bankaccountservice.entities.BankAccount;
import org.side.bankaccountservice.repositories.BankAccountRepository;
import org.side.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    public AccountRestController(BankAccountRepository bankAccountRepository)
    {
         this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts()
    {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id)
    {
        return bankAccountRepository.findById(id).orElseThrow(()->
        new RuntimeException(String.format("Account is not found", id)));
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccount)
    {
        return accountService.AddAccount(bankAccount);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount)
    {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreateAt()!=null) account.setCreateAt(new Date());
        if(bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());

        return  bankAccountRepository.save(account);
    }

    @PostMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id)
    {
         bankAccountRepository.deleteById(id);
    }
}
