package stay.space.startup.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stay.space.startup.persistence.entity.User;
import stay.space.startup.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Api(value = "User Management System", tags = {"Users"})
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }



    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
//    @PreAuthorize("permitAll()")
    @ApiOperation(value = "View a list of all users", response = List.class)
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    //@PreAuthorize("permitAll()")
    @ApiOperation(value = "Get a user by ID", response = User.class)
    public ResponseEntity<User> getUserById(
            @ApiParam(value = "ID of the user to retrieve", required = true)
            @PathVariable Long id) {
        Optional<User> user = userServiceImpl.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "Create a new user")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public User createUser(
            @ApiParam(value = "User object to store in the database", required = true)
            @RequestBody User user) {
        return userServiceImpl.createUser(user);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing user")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<User> updateUser(
            @ApiParam(value = "ID of the user to update", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated user object", required = true)
            @RequestBody User userDetails) {
        Optional<User> updatedUser = userServiceImpl.updateUser(id, userDetails);
        return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(
            @ApiParam(value = "ID of the user to delete", required = true)
            @PathVariable Long id) {
        boolean isDeleted = userServiceImpl.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    @ApiOperation(value = "Get users by name")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userServiceImpl.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @ApiOperation(value = "Get users by email")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userServiceImpl.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
