package eu.ecct.synthesysterminal.key;


import eu.ecct.synthesysterminal.key.entity.Key;
import eu.ecct.synthesysterminal.common.repository.RestApiRepository;
import eu.ecct.synthesysterminal.common.service.AbstractRestApiService;
import eu.ecct.synthesysterminal.common.service.RestApiServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class KeyService extends AbstractRestApiService<Key, Long> implements RestApiServiceOperations<Key, Long>, KeyServiceOperations {

    @Autowired
    public KeyService(RestApiRepository<Key, Long> repository) {
        super(repository);
    }

    @Override
    public Iterable<Key> getAllElements() {
        return repository.getAllByActive(false);
    }

    @Override
    public Optional<Key> getElementById(Long id) {
        return repository.getByIdAndActive(id, false);
    }

    @Override
    public void updateElement(Key key) {
        repository.save(key);
    }

    @Override
    public void patchElement(Key key, Long id) {
        Key oldKey = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (key.getValue() != null) oldKey.setValue(key.getValue());
        if (key.getDescription() != null) oldKey.setDescription(key.getDescription());
        repository.save(oldKey);
    }

    @Override
    public void deleteElement(Long id) {
        Key oldKey = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        oldKey.setActive(false);
        repository.save(oldKey);
    }

    @Override
    public Optional<Key> getKeyByValue(UUID value) {
        KeyRepository keyRepository = (KeyRepository) repository;
        return keyRepository.findByValue(value);
    }

}
