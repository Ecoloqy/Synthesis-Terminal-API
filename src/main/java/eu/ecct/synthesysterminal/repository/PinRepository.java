package eu.ecct.synthesysterminal.repository;

import eu.ecct.synthesysterminal.entity.Pin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinRepository extends CrudRepository<Pin, Long> {
}
