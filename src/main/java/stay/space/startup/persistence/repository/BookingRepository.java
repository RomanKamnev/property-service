package stay.space.startup.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stay.space.startup.persistence.entity.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByPropertyId(Long propertyId);

    List<Booking> findByStatus(String status);

}
