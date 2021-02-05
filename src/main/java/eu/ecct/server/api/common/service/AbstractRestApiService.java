package eu.ecct.server.api.common.service;

import eu.ecct.server.api.common.entity.EntityOperations;
import eu.ecct.server.api.common.repository.RestApiRepository;

import java.util.Optional;

public abstract class AbstractRestApiService<T extends EntityOperations<V>, V> extends AbstractCommonApiService<T, V> implements RestApiServiceOperations<T, V> {

    public AbstractRestApiService(RestApiRepository<T, V> repository) {
        super(repository);
    }

    @Override
    public Iterable<T> getAllElements() {
        return repository.getAllByActive(true);
    }

    @Override
    public Optional<T> getElementById(V id) {
        return repository.getByIdAndActive(id, true);
    }

    @Override
    public void addNewElement(T element) {
        repository.save(element);
    }

}
