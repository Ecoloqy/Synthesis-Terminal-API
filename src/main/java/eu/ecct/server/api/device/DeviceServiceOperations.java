package eu.ecct.server.api.device;

import eu.ecct.server.api.common.service.RestApiGetByUUIDServiceOperations;
import eu.ecct.server.api.device.entity.Device;

import java.util.Optional;
import java.util.UUID;

interface DeviceServiceOperations extends RestApiGetByUUIDServiceOperations<Device, UUID> {

    Optional<Device> getDeviceFromPasswords(Long address, String password);

    Optional<Device> getElementById(UUID id);

}
