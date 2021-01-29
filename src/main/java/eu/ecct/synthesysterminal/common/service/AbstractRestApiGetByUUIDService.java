package eu.ecct.synthesysterminal.common.service;

import eu.ecct.synthesysterminal.common.entity.EntityOperations;
import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.common.repository.RestApiRepository;

import java.util.UUID;

public abstract class AbstractRestApiGetByUUIDService<T extends EntityOperations<V>, V> extends AbstractCommonApiService<T, V> implements RestApiGetByUUIDServiceOperations<T, V> {

    protected final RestApiServiceOperations<Account, UUID> accountService;

    public AbstractRestApiGetByUUIDService(RestApiRepository<T, V> repository, RestApiServiceOperations<Account, UUID> accountService) {
        super(repository);
        this.accountService = accountService;
    }

}
