package org.side.bankaccountservice.web;

import org.side.bankaccountservice.dto.BankAccountRequestDTO;
import org.side.bankaccountservice.dto.BankAccountResponseDTO;
import org.side.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Api")
public class AccountRestController {
    private AccountService accountService;
    public AccountRestController(AccountService accountService)
    {
         this.accountService = accountService;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDTO> bankAccounts()
    {
        return accountService.GetAllAccount();
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO bankAccount(@PathVariable String id)
    {
        try{
            return accountService.GetAccount(id);
        }
        catch (Exception ex){
            throw (new RuntimeException(String.format("Account is not found", id)));
        }
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccount)
    {
        return accountService.AddAccount(bankAccount);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO update(@PathVariable String id,@RequestBody BankAccountRequestDTO bankAccount)
    {
       return  accountService.UpdateAccount(id, bankAccount);
    }

    @PostMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id)
    {
         accountService.DeleteAccount(id);
    }
}
