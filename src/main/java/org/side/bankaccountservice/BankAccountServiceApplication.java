package org.side.bankaccountservice;

import org.side.bankaccountservice.entities.BankAccount;
import org.side.bankaccountservice.enums.AccountType;
import org.side.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.UUID;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"org.side.bankaccountservice.entities","org.side.bankaccountservice.enums","org.side.bankaccountservice.repositories"})
@ComponentScan
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner Start(BankAccountRepository bankAccountRepository){
		return args -> {
			for (int i = 0; i<10; i++)
			{
				BankAccount bankAccount = BankAccount
						.builder()
						.id(UUID.randomUUID().toString())
						.type(Math.random()>0.5
								? AccountType.CURRENT_ACCOUNT
								: AccountType.SAVING_ACCOUNT)
						.balance(10000+Math.random())
						.currency("MAD")
						.createAt(new Date())
						.build();

				bankAccountRepository.save(bankAccount);
			}
		};
	}

}
