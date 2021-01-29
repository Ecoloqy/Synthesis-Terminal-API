package eu.ecct.synthesysterminal.address;

import eu.ecct.synthesysterminal.address.entity.Address;
import eu.ecct.synthesysterminal.common.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AddressRepository extends RestApiRepository<Address, Long> {
}
