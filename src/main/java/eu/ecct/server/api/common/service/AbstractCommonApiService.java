package eu.ecct.server.api.common.service;

import eu.ecct.server.api.common.entity.EntityOperations;
import eu.ecct.server.api.common.repository.RestApiRepository;

public abstract class AbstractCommonApiService<T extends EntityOperations<V>, V> implements CommonApiServiceOperations<T, V> {

    protected final RestApiRepository<T, V> repository;

    public AbstractCommonApiService(RestApiRepository<T, V> repository) {
        this.repository = repository;
    }

    @Override
    public void updateElement(T element) {
        repository.save(element);
    }

    @Override
    public boolean isElementExist(V id) {
        return repository.existsById(id);
    }

}
