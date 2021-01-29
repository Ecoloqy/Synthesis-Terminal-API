package eu.ecct.synthesysterminal.api.repository.pin;

import eu.ecct.synthesysterminal.api.entity.pin.Pin;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinRepository extends RestApiRepository<Pin, Long> {
}
