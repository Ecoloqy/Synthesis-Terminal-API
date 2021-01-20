package eu.ecct.synthesysterminal.repository;

import eu.ecct.synthesysterminal.entity.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends CrudRepository<Device, UUID> {
}
