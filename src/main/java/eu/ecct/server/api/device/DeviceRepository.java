package eu.ecct.server.api.device;

import eu.ecct.server.api.common.repository.RestApiRepository;
import eu.ecct.server.api.device.entity.Device;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface DeviceRepository extends RestApiRepository<Device, UUID> {

    Optional<Device> findByAddressAndPassword(Long address, String password);

}
