package eu.ecct.synthesysterminal.api.service;

import eu.ecct.synthesysterminal.api.entity.EntityOperations;
import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class AbstractApiGetByUserOperations<T extends EntityOperations<V>, V> implements RestApiGetByUserOperations<T, V> {

    protected final RestApiRepository<T, V> repository;
    protected final RestApiServiceOperations<Account, UUID> accountService;

    public AbstractApiGetByUserOperations(RestApiRepository<T, V> repository, RestApiServiceOperations<Account, UUID> accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    @Override
    public boolean isElementExist(V id) {
        return repository.existsById(id);
    }

}
