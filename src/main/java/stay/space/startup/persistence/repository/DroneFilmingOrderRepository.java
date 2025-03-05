package stay.space.startup.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stay.space.startup.persistence.entity.DroneFilmingOrder;

public interface DroneFilmingOrderRepository extends JpaRepository<DroneFilmingOrder, Long> {
}
