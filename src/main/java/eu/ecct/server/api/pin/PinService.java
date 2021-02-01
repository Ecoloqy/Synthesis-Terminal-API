package eu.ecct.server.api.pin;

import eu.ecct.server.api.account.entity.Account;
import eu.ecct.server.api.common.repository.RestApiRepository;
import eu.ecct.server.api.common.service.AbstractRestApiGetByUUIDService;
import eu.ecct.server.api.common.service.CommonApiServiceOperations;
import eu.ecct.server.api.common.service.RestApiServiceOperations;
import eu.ecct.server.api.device.entity.Device;
import eu.ecct.server.api.pin.entity.Pin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class PinService extends AbstractRestApiGetByUUIDService<Pin, Long> implements PinServiceOperations {

    private final CommonApiServiceOperations<Device, UUID> deviceService;

    @Autowired
    public PinService(RestApiRepository<Pin, Long> pinRepository,
                      RestApiServiceOperations<Account, UUID> accountService,
                      CommonApiServiceOperations<Device, UUID> deviceService) {
        super(pinRepository, accountService);
        this.deviceService = deviceService;
    }

    @Override
    public Iterable<Pin> getAllElements(UUID deviceId) {
        Device device = ((RestApiServiceOperations<Device, UUID>) deviceService).getElementById(deviceId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return device.getPins();
    }

    @Override
    public Optional<Pin> getElementById(UUID deviceId, Long id) {
        Device device = ((RestApiServiceOperations<Device, UUID>) deviceService).getElementById(deviceId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return device.getPins().stream()
                .filter(pin -> pin.getId().equals(id))
                .findFirst();
    }

    @Override
    public void addNewElement(Pin pin, UUID deviceId) {
        Device device = ((RestApiServiceOperations<Device, UUID>) deviceService).getElementById(deviceId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        device.getPins().add(pin);
        deviceService.updateElement(device);
    }

    @Override
    public void patchElement(Pin pin, Long id) {
        Pin oldPin = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (pin.getPinType() != null) oldPin.setPinType(pin.getPinType());
        if (pin.getSwitchName() != null) oldPin.setSwitchName(pin.getSwitchName());
        oldPin.setSensorValue(pin.getSensorValue());
        oldPin.setSwitchStatus(pin.isSwitchStatus());
        repository.save(oldPin);
    }

    @Override
    public void deleteElement(Long id) {
        Pin oldPin = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        oldPin.setActive(false);
        repository.save(oldPin);
    }

}
