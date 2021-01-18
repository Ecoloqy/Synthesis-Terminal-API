package eu.ecct.synthesysterminal.controller;

import eu.ecct.synthesysterminal.entity.Device;
import eu.ecct.synthesysterminal.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public Iterable<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{uuid}")
    public Device getDevice(@PathVariable("uuid") UUID uuid) {
        return deviceService.getDevice(uuid);
    }

    @PostMapping
    public Device postDevice(@RequestBody Device device) {
        return deviceService.postDevice(device);
    }

    @PutMapping
    public Device putDevice(@RequestBody Device device) {
        return deviceService.putDevice(device);
    }

}
