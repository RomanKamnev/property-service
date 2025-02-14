package stay.space.startup.service;

//import org.springframework.security.core.userdetails.UserDetails;
import stay.space.startup.persistence.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing users.
 */
public interface UserService {

    /**
     * Retrieves all users.
     *
     * @return a list of users
     */
    List<User> getAllUsers();

    /**
     * Retrieves a user by its ID.
     *
     * @param id the ID of the user to retrieve
     * @return an optional containing the found user or empty if no user was found
     */
    Optional<User> getUserById(Long id);

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    User createUser(User user);

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update
     * @param userDetails the new details of the user
     * @return an optional containing the updated user or empty if no user was found
     */
    Optional<User> updateUser(Long id, User userDetails);

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     * @return true if the user was deleted, false if the user was not found
     */
    boolean deleteUser(Long id);
}

