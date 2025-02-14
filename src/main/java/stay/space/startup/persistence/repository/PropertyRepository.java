package stay.space.startup.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stay.space.startup.persistence.entity.Property;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByOwnerId(Long ownerId);

    List<Property> findByCity(String city);

    List<Property> findByCountry(String country);

}

