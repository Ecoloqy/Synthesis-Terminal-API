package eu.ecct.synthesysterminal.api.repository.address;

import eu.ecct.synthesysterminal.api.entity.address.Address;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends RestApiRepository<Address, Long> {
}
