package eu.ecct.synthesysterminal.api.repository.device;

import eu.ecct.synthesysterminal.api.entity.device.Device;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends RestApiRepository<Device, UUID> {

    Optional<Device> findByAddressAndPassword(Long address, String password);

}
