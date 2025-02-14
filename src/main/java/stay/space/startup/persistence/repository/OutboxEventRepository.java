package stay.space.startup.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stay.space.startup.persistence.entity.OutboxEvent;
import stay.space.startup.persistence.entity.OutboxStatus;

import java.util.List;

@Repository
public interface OutboxEventRepository extends JpaRepository<OutboxEvent, Long> {

    List<OutboxEvent> findByStatus(OutboxStatus status);
}
