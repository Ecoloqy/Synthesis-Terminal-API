package eu.ecct.server.api.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface RestApiRepository<T, V> extends JpaRepository<T, V> {

    Iterable<T> getAllByActive(boolean status);

    Optional<T> getByIdAndActive(V id, boolean status);

}
