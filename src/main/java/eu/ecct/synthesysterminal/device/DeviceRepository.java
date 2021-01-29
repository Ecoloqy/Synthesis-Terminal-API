package eu.ecct.synthesysterminal.device;

import eu.ecct.synthesysterminal.device.entity.Device;
import eu.ecct.synthesysterminal.common.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface DeviceRepository extends RestApiRepository<Device, UUID> {

    Optional<Device> findByAddressAndPassword(Long address, String password);

}
