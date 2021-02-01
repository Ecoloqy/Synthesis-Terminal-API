package eu.ecct.server.api.pin;

import eu.ecct.server.api.common.repository.RestApiRepository;
import eu.ecct.server.api.pin.entity.Pin;
import org.springframework.stereotype.Repository;

@Repository
interface PinRepository extends RestApiRepository<Pin, Long> {
}
