package eu.ecct.synthesysterminal.device;

import eu.ecct.synthesysterminal.account.entity.Account;
import eu.ecct.synthesysterminal.device.entity.Device;
import eu.ecct.synthesysterminal.pin.entity.Pin;
import eu.ecct.synthesysterminal.common.repository.RestApiRepository;
import eu.ecct.synthesysterminal.common.service.AbstractRestApiGetByUUIDService;
import eu.ecct.synthesysterminal.common.service.RestApiServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService extends AbstractRestApiGetByUUIDService<Device, UUID> implements DeviceServiceOperations {

    @Autowired
    public DeviceService(RestApiRepository<Device, UUID> repository,
                         RestApiServiceOperations<Account, UUID> accountService) {
        super(repository, accountService);
    }

    @Override
    public Iterable<Device> getAllElements(UUID userId) {
        Optional<Account> account = accountService.getElementById(userId);
        if (account.isPresent() && account.get().getDevices() != null) {
            return account.get().getDevices();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Device> getElementById(UUID userId, UUID id) {
        Optional<Account> account = accountService.getElementById(userId);
        if (account.isPresent() && account.get().getDevices() != null) {
            for (Device device : account.get().getDevices()) {
                if (device.getId().equals(id)) return Optional.of(device);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Device> getElementById(UUID id) {
        return repository.getByIdAndActive(id, false);
    }

    @Override
    public void addNewElement(Device device, UUID userId) {
        Account account = accountService.getElementById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        account.getDevices().add(device);
        accountService.updateElement(account);
    }

    @Override
    public void updateElement(Device device) {
        repository.save(device);
    }

    @Override
    public void patchElement(Device device, UUID id) {
        Device oldDevice = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (device.getPins() != null) {
            oldDevice.setModified(true);
            oldDevice.setPins(device.getPins());
        }
        if (device.getName() != null) oldDevice.setName(device.getName());
        repository.save(oldDevice);
    }

    @Override
    public void deleteElement(UUID id) {
        Device device = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        device.setActive(false);
        if (device.getPins() != null) {
            for (Pin pin : device.getPins()) {
                pin.setActive(false);
            }
        }
    }

    @Override
    public Optional<Device> getDeviceFromPasswords(Long address, String password) {
        DeviceRepository deviceRepository = (DeviceRepository) repository;
        return deviceRepository.findByAddressAndPassword(address, password);
    }

}
