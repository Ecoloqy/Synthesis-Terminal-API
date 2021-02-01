package eu.ecct.server.api.address;

import eu.ecct.server.api.address.entity.Address;
import eu.ecct.server.api.common.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AddressRepository extends RestApiRepository<Address, Long> {
}
