package eu.ecct.synthesysterminal.account;

import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.common.repository.RestApiRepository;
import eu.ecct.synthesysterminal.common.service.AbstractRestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public void addNewElement(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        repository.save(account);
    }

    @Override
    public void updateElement(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        repository.save(account);
    }

    @Override
    public void patchElement(Account account, UUID id) {
        Account oldAccount = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
    }

    @Override
    public void deleteElement(UUID id) {
        Account oldAccount = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        oldAccount.setActive(false);
        if (oldAccount.getAddress() != null) {
            oldAccount.getAddress().setActive(false);
        }
        repository.save(oldAccount);
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
