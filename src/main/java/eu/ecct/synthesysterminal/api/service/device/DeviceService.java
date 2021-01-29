package eu.ecct.synthesysterminal.api.service.device;

import eu.ecct.synthesysterminal.api.entity.account.Account;
import eu.ecct.synthesysterminal.api.entity.device.Device;
import eu.ecct.synthesysterminal.api.entity.pin.Pin;
import eu.ecct.synthesysterminal.api.repository.RestApiRepository;
import eu.ecct.synthesysterminal.api.repository.device.DeviceRepository;
import eu.ecct.synthesysterminal.api.service.AbstractApiGetByUserOperations;
import eu.ecct.synthesysterminal.api.service.AbstractRestApiService;
import eu.ecct.synthesysterminal.api.service.RestApiServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService extends AbstractApiGetByUserOperations<Device, UUID> implements DeviceServiceOperations {

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
        return repository.getByIdAndArchived(id, false);
    }

    @Override
    public boolean addNewElement(Device device, UUID userId) {
        Optional<Account> accountOptional = accountService.getElementById(userId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.getDevices().add(device);
            accountService.updateElement(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateElement(Device device) {
        Optional<Device> oldDeviceOptional = repository.findById(device.getId());
        if (oldDeviceOptional.isPresent()) {
            Device oldDevice = oldDeviceOptional.get();
            oldDevice.setAddress(device.getAddress());
            oldDevice.setPassword(device.getPassword());
            oldDevice.setModified(device.isModified());
            oldDevice.setActivated(device.isActivated());
            oldDevice.setArchived(device.isArchived());
            oldDevice.setName(device.getName());
            oldDevice.setPins(device.getPins());
            repository.save(oldDevice);
            return true;
        }
        return false;
    }

    @Override
    public boolean patchElement(Device device, UUID id) {
        Optional<Device> oldDeviceOptional = repository.findById(id);
        if (oldDeviceOptional.isPresent()) {
            Device oldDevice = oldDeviceOptional.get();
            if (device.getPins() != null) {
                oldDevice.setModified(true);
                oldDevice.setPins(device.getPins());
            }
            if (device.getName() != null) oldDevice.setName(device.getName());
            repository.save(oldDevice);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteElement(UUID id) {
        Optional<Device> oldDeviceOptional = repository.findById(id);
        if (oldDeviceOptional.isPresent()) {
            Device oldDevice = oldDeviceOptional.get();
            oldDevice.setArchived(true);
            if (oldDevice.getPins() != null) {
                for (Pin pin : oldDevice.getPins()) {
                    pin.setArchived(true);
                }
            }
            repository.save(oldDevice);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Device> getDeviceFromPasswords(Long address, String password) {
        DeviceRepository deviceRepository = (DeviceRepository) repository;
        return deviceRepository.findByAddressAndPassword(address, password);
    }

}
