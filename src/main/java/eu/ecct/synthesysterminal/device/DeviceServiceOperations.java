package eu.ecct.synthesysterminal.device;

import eu.ecct.synthesysterminal.device.entity.Device;
import eu.ecct.synthesysterminal.common.service.RestApiGetByUUIDServiceOperations;

import java.util.Optional;
import java.util.UUID;

interface DeviceServiceOperations extends RestApiGetByUUIDServiceOperations<Device, UUID> {

    Optional<Device> getDeviceFromPasswords(Long address, String password);

    Optional<Device> getElementById(UUID id);

}
