package stay.space.startup.persistence.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "properties")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique ID of the property")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @ApiModelProperty(notes = "The owner of the property")
    private User owner;

    @Column(nullable = false, length = 100)
    @ApiModelProperty(notes = "The title of the property")
    private String title;

    @Column(columnDefinition = "TEXT")
    @ApiModelProperty(notes = "The description of the property")
    private String description;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The address of the property")
    private String address;

    @Column(nullable = false, length = 100)
    @ApiModelProperty(notes = "The city where the property is located")
    private String city;

    @Column(length = 100)
    @ApiModelProperty(notes = "The state where the property is located")
    private String state;

    @Column(nullable = false, length = 100)
    @ApiModelProperty(notes = "The country where the property is located")
    private String country;

    @Column(nullable = false, precision = 10, scale = 2)
    @ApiModelProperty(notes = "The price per night for the property")
    private BigDecimal pricePerNight;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The maximum number of guests the property can accommodate")
    private Integer maxGuests;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The date and time when the property was created")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The date and time when the property was last updated")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "The list of bookings associated with the property")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    @ApiModelProperty(notes = "The list of images associated with the property")
    private List<PropertyImage> images;
}
