package stay.space.startup.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stay.space.startup.persistence.entity.PropertyImage;

import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {

    List<PropertyImage> findByPropertyId(Long propertyId);

}
