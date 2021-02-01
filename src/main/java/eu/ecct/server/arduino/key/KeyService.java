package eu.ecct.server.arduino.key;

import eu.ecct.server.arduino.key.entity.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class KeyService implements KeyServiceOperations {

    private final KeyRepository repository;

    @Autowired
    public KeyService(KeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Key> getAllKeys() {
        return repository.findAll();
    }

    @Override
    public Optional<Key> getKeyById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void addOrUpdateKey(Key key) {
        repository.save(key);
    }

    @Override
    public void deleteKey(Long id) {
        Key oldKey = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        oldKey.setActive(false);
        repository.save(oldKey);
    }

    @Override
    public Optional<Key> getKeyByValue(UUID value) {
        return repository.findByValue(value);
    }

}
