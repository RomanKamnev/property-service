package stay.space.startup.service;

import stay.space.startup.persistence.entity.Role;
import java.util.Optional;

import java.util.List;

public interface RoleService {

    /**
     * Retrieve all roles.
     *
     * @return a list of roles.
     */
    List<Role> getAllRoles();

    /**
     * Retrieve a role by its ID.
     *
     * @param id the ID of the role.
     * @return an optional role.
     */
    Optional<Role> getRoleById(Long id);

    /**
     * Save or update a role.
     *
     * @param role the role to save.
     * @return the saved role.
     */
    Role saveRole(Role role);

    /**
     * Delete a role by its ID.
     *
     * @param id the ID of the role to delete.
     */
    void deleteRole(Long id);
}
