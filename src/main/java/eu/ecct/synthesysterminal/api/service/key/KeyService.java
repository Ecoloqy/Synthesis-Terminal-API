package eu.ecct.synthesysterminal.api.service.key;


import eu.ecct.synthesysterminal.api.entity.key.Key;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;
import eu.ecct.synthesysterminal.api.repository.key.KeyRepository;
import eu.ecct.synthesysterminal.api.service.AbstractRestApiService;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.getAllByArchived(false);
    }

    @Override
    public Optional<Key> getElementById(Long id) {
        return repository.getByIdAndArchived(id, false);
    }

    @Override
    public boolean updateElement(Key key) {
        Optional<Key> oldKeyOptional = repository.findById(key.getId());
        if (oldKeyOptional.isPresent()) {
            Key oldKey = oldKeyOptional.get();
            oldKey.setValue(key.getValue());
            oldKey.setDescription(key.getDescription());
            oldKey.setArchived(key.isArchived());
            repository.save(oldKey);
            return true;
        }
        return false;
    }

    @Override
    public boolean patchElement(Key key, Long id) {
        Optional<Key> oldKeyOptional = repository.findById(id);
        if (oldKeyOptional.isPresent()) {
            Key oldKey = oldKeyOptional.get();
            if (key.getValue() != null) oldKey.setValue(key.getValue());
            if (key.getDescription() != null) oldKey.setDescription(key.getDescription());
            repository.save(oldKey);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteElement(Long id) {
        Optional<Key> oldKeyOptional = repository.findById(id);
        if (oldKeyOptional.isPresent()) {
            Key oldKey = oldKeyOptional.get();
            oldKey.setArchived(true);
            repository.save(oldKey);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Key> getKeyByValue(UUID value) {
        KeyRepository keyRepository = (KeyRepository) repository;
        return keyRepository.findByValue(value);
    }

}
