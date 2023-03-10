package org.side.bankaccountservice.repositories;

import org.side.bankaccountservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
