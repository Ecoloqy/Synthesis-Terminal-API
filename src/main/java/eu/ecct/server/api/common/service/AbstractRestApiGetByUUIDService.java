package eu.ecct.server.api.common.service;

import eu.ecct.server.api.account.entity.Account;
import eu.ecct.server.api.common.entity.EntityOperations;
import eu.ecct.server.api.common.repository.RestApiRepository;

import java.util.UUID;

public abstract class AbstractRestApiGetByUUIDService<T extends EntityOperations<V>, V> extends AbstractCommonApiService<T, V> implements RestApiGetByUUIDServiceOperations<T, V> {

    protected final RestApiServiceOperations<Account, UUID> accountService;

    public AbstractRestApiGetByUUIDService(RestApiRepository<T, V> repository, RestApiServiceOperations<Account, UUID> accountService) {
        super(repository);
        this.accountService = accountService;
    }

}
