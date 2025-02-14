package stay.space.startup.persistence.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import stay.space.startup.persistence.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //todo Cycle in properties
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = { "properties" })
    Optional<User> findByUsername(String username);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = { "bookings" })
    Optional<User> findByEmail(String email);
}
