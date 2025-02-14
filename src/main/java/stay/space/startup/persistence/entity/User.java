package stay.space.startup.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique ID of the user")
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    @ApiModelProperty(notes = "The unique username of the user")
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    @ApiModelProperty(notes = "The unique email of the user")
    private String email;

    @Column(nullable = false, length = 255)
    @ApiModelProperty(notes = "The hashed password of the user")
    private String passwordHash;

    @Column(length = 50)
    @ApiModelProperty(notes = "The first name of the user")
    private String firstName;

    @Column(length = 50)
    @ApiModelProperty(notes = "The last name of the user")
    private String lastName;

    @Column(nullable = false, length = 20)
    @ApiModelProperty(notes = "The role of the user (e.g., ADMIN, USER)")
    private String role;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The date and time when the user was created")
    private Timestamp createdAt;

//    //todo Implement with LocalDateTime(set mapping)
//    @Column(nullable = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @ApiModelProperty(notes = "The date and time when the user was created")
//    private LocalDateTime createdAt;

    //todo lazy doesn't work
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ApiModelProperty(notes = "The list of properties owned by the user")
    private List<Property> properties;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ApiModelProperty(notes = "The list of bookings made by the user")
    private List<Booking> bookings;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}

