package eu.ecct.synthesysterminal.api.service.device;

import eu.ecct.synthesysterminal.api.entity.device.Device;
import eu.ecct.synthesysterminal.api.service.RestApiGetByUserOperations;

import java.util.Optional;
import java.util.UUID;

public interface DeviceServiceOperations extends RestApiGetByUserOperations<Device, UUID> {

    Optional<Device> getDeviceFromPasswords(Long address, String password);

    Optional<Device> getElementById(UUID id);

}
