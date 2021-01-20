package eu.ecct.synthesysterminal.service;

import eu.ecct.synthesysterminal.entity.Account;
import eu.ecct.synthesysterminal.entity.Address;
import eu.ecct.synthesysterminal.entity.Device;
import eu.ecct.synthesysterminal.entity.details.Provider;
import eu.ecct.synthesysterminal.entity.details.UserRole;
import eu.ecct.synthesysterminal.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Iterable<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public Account getAccountById(UUID id) {
        return accountRepository.findById(id).orElse(null);
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }

    public Account saveAccount(Account account) {
        Optional<Account> oldAccountOptional = accountRepository.findById(account.getId());
        if (oldAccountOptional.isPresent()) {
            Account oldAccount = oldAccountOptional.get();

            // username is not updated
            oldAccount.setEmail(account.getEmail());
            oldAccount.setProviderId(account.getProviderId());
            oldAccount.setProvider(account.getProvider());
            // role is not updated
            oldAccount.setEmailVerified(account.isEmailVerified());
            oldAccount.setPassword(account.getPassword());
            oldAccount.setPinCode(account.getPinCode());
            oldAccount.setAddress(account.getAddress());
            oldAccount.setDevices(account.getDevices());

            return accountRepository.save(oldAccount);
        }
        return accountRepository.save(account);
    }

}
