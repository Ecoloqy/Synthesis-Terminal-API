package eu.ecct.synthesysterminal.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface RestApiRepository<T, V> extends JpaRepository<T, V> {

    Iterable<T> getAllByArchived(boolean status);

    Optional<T> getByIdAndArchived(V id, boolean status);

}
