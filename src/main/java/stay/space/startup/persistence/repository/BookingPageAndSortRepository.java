package stay.space.startup.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import stay.space.startup.persistence.entity.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingPageAndSortRepository extends PagingAndSortingRepository<Booking, UUID> {

    Page<Booking> findAllByIdIn(List<Long> ids, Pageable pageable);
}
