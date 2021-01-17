package eu.ecct.synthesysterminal.repository;

import eu.ecct.synthesysterminal.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
