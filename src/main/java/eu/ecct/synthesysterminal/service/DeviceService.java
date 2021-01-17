package eu.ecct.synthesysterminal.service;

import eu.ecct.synthesysterminal.entity.Device;
import eu.ecct.synthesysterminal.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Device getDevice(UUID uuid) {
        return deviceRepository.findById(uuid).orElse(null);
    }

    public Device postDevice(Device device) {
        deviceRepository.save(device);
        return device;
    }

    public Device putDevice(Device device) {
        Device oldDevice = deviceRepository.getOne(device.getId());
        deviceRepository.save(oldDevice);
        return oldDevice;
    }

}
