package eu.ecct.synthesysterminal.api.service;

import eu.ecct.synthesysterminal.api.entity.EntityOperations;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;

import java.util.Optional;

public abstract class AbstractRestApiService<T extends EntityOperations<V>, V> implements RestApiServiceOperations<T, V> {

    protected final RestApiRepository<T, V> repository;

    public AbstractRestApiService(RestApiRepository<T, V> repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<T> getAllElements() {
        return repository.getAllByArchived(false);
    }

    @Override
    public Optional<T> getElementById(V id) {
        return repository.getByIdAndArchived(id, false);
    }

    @Override
    public boolean addNewElement(T element) {
        repository.save(element);
        return repository.existsById(element.getId());
    }

    @Override
    public boolean isElementExist(V id) {
        return repository.existsById(id);
    }

}
