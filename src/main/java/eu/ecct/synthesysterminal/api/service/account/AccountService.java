package eu.ecct.synthesysterminal.api.service.account;

import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;
import eu.ecct.synthesysterminal.api.repository.account.AccountRepository;
import eu.ecct.synthesysterminal.api.service.AbstractRestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService extends AbstractRestApiService<Account, UUID> implements AccountServiceOperations {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(RestApiRepository<Account, UUID> repository,
                          PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean addNewElement(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        repository.save(account);
        return repository.existsById(account.getId());
    }

    @Override
    public boolean updateElement(Account account) {
        Optional<Account> oldAccountOptional = repository.findById(account.getId());
        if (oldAccountOptional.isPresent()) {
            Account oldAccount = oldAccountOptional.get();
            oldAccount.setEmailVerified(account.isEmailVerified());
            oldAccount.setPassword(passwordEncoder.encode(account.getPassword()));
            oldAccount.setPinCode(account.getPinCode());
            oldAccount.setUserRole(account.getUserRole());
            oldAccount.setUsername(account.getUsername());
            oldAccount.setAvatarUrl(account.getAvatarUrl());
            oldAccount.setEmail(account.getEmail());
            oldAccount.setAddress(account.getAddress());
            oldAccount.setProvider(account.getProvider());
            oldAccount.setProviderId(account.getProviderId());
            oldAccount.setArchived(account.isArchived());
            oldAccount.setDevices(account.getDevices());
            repository.save(oldAccount);
            return true;
        }
        return false;
    }

    @Override
    public boolean patchElement(Account account, UUID id) {
        Optional<Account> oldAccountOptional = repository.findById(id);
        if (oldAccountOptional.isPresent()) {
            Account oldAccount = oldAccountOptional.get();
            if (account.getPassword() != null) oldAccount.setPassword(passwordEncoder.encode(account.getPassword()));
            if (account.getPinCode() > 0) oldAccount.setPinCode(account.getPinCode());
            if (account.getAvatarUrl() != null) oldAccount.setAvatarUrl(account.getAvatarUrl());
            if (account.getEmail() != null) oldAccount.setEmail(account.getEmail());
            if (account.getAddress() != null) oldAccount.setAddress(account.getAddress());
            if (account.getDevices() != null) oldAccount.setDevices(account.getDevices());
            if (account.getProvider() != null && account.getProviderId() != null) {
                oldAccount.setProvider(account.getProvider());
                oldAccount.setProviderId(account.getProviderId());
            }
            repository.save(oldAccount);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteElement(UUID id) {
        Optional<Account> oldAccountOptional = repository.findById(id);
        if (oldAccountOptional.isPresent()) {
            Account oldAccount = oldAccountOptional.get();
            oldAccount.setArchived(true);
            if (oldAccount.getAddress() != null) {
                oldAccount.getAddress().setArchived(true);
            }
            repository.save(oldAccount);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Account> getElementByUsername(String username) {
        AccountRepository accountRepository = (AccountRepository) repository;
        return accountRepository.findByUsername(username);
    }

    @Override
    public Optional<Account> getElementByEmail(String email) {
        AccountRepository accountRepository = (AccountRepository) repository;
        return accountRepository.findByEmail(email);
    }

}
