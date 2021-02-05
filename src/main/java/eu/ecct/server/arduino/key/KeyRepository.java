package eu.ecct.server.arduino.key;

import eu.ecct.server.arduino.key.entity.Key;
import eu.ecct.server.api.common.repository.RestApiRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface KeyRepository extends JpaRepository<Key, Long> {

    Optional<Key> findByValueAndActive(UUID value, boolean active);

}
