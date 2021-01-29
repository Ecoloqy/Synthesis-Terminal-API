package eu.ecct.synthesysterminal.common.service;

import eu.ecct.synthesysterminal.common.entity.EntityOperations;
import eu.ecct.synthesysterminal.common.repository.RestApiRepository;

import java.util.Optional;

public abstract class AbstractRestApiService<T extends EntityOperations<V>, V> extends AbstractCommonApiService<T, V> implements RestApiServiceOperations<T, V> {

    public AbstractRestApiService(RestApiRepository<T, V> repository) {
        super(repository);
    }

    @Override
    public Iterable<T> getAllElements() {
        return repository.getAllByActive(false);
    }

    @Override
    public Optional<T> getElementById(V id) {
        return repository.getByIdAndActive(id, false);
    }

    @Override
    public void addNewElement(T element) {
        repository.save(element);
    }

}
