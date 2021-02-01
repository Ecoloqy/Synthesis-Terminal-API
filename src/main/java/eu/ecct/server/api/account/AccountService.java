package eu.ecct.server.api.account;

import eu.ecct.server.api.account.entity.Account;
import eu.ecct.server.api.common.repository.RestApiRepository;
import eu.ecct.server.api.common.service.AbstractRestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService extends AbstractRestApiService<Account, UUID> implements AccountServiceOperations {

    @Autowired
    public AccountService(RestApiRepository<Account, UUID> repository) {
        super(repository);
    }

    @Override
    public void patchElement(Account account, UUID id) {
        Account oldAccount = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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

    @Override
    public Optional<Account> getElementByProviderId(Long providerId) {
        AccountRepository accountRepository = (AccountRepository) repository;
        return accountRepository.findByProviderId(providerId);
    }

    @Override
    public boolean isExistsByProviderId(Long providerId) {
        AccountRepository accountRepository = (AccountRepository) repository;
        return accountRepository.existsByProviderId(providerId);
    }

}
