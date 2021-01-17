package eu.ecct.synthesysterminal.service;

import eu.ecct.synthesysterminal.entity.Account;
import eu.ecct.synthesysterminal.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(UUID uuid) {
        return accountRepository.findById(uuid).orElse(null);
    }

    public Account postAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    public Account putAccount(Account account) {
        Account oldAccount = accountRepository.getOne(account.getId());
        oldAccount.setName(account.getName());
        oldAccount.setPassword(account.getPassword());
        oldAccount.setPinCode(account.getPinCode());
        oldAccount.setAddress(account.getAddress());
        oldAccount.setDevices(account.getDevices());
        accountRepository.save(oldAccount);
        return oldAccount;
    }

}
