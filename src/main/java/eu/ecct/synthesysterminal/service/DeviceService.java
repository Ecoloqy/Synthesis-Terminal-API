package eu.ecct.synthesysterminal.service;

import eu.ecct.synthesysterminal.entity.Address;
import eu.ecct.synthesysterminal.entity.Device;
import eu.ecct.synthesysterminal.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Iterable<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(UUID id) {
        return deviceRepository.findById(id).orElse(null);
    }

    public Device saveDevice(Device device) {
        Optional<Device> oldDeviceOptional = deviceRepository.findById(device.getId());
        if (oldDeviceOptional.isPresent()) {
            Device oldDevice = oldDeviceOptional.get();

            oldDevice.setName(device.getName());
            // address is not updated
            // password is not updated
            oldDevice.setPins(device.getPins());

            return deviceRepository.save(oldDevice);
        }
        return deviceRepository.save(device);
    }

}
