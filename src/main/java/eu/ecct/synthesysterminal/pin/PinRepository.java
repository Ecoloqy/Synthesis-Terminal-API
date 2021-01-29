package eu.ecct.synthesysterminal.pin;

import eu.ecct.synthesysterminal.common.repository.RestApiRepository;
import eu.ecct.synthesysterminal.pin.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PinRepository extends RestApiRepository<Pin, Long> {
}
