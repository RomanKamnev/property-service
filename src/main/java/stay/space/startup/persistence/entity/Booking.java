package stay.space.startup.persistence.entity;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The unique ID of the booking")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    @ApiModelProperty(notes = "The property associated with the booking")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ApiModelProperty(notes = "The user who made the booking")
    private User user;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The check-in date for the booking")
    private LocalDate checkInDate;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The check-out date for the booking")
    private LocalDate checkOutDate;

    @Column(nullable = false, precision = 10, scale = 2)
    @ApiModelProperty(notes = "The total price for the booking")
    private BigDecimal totalPrice;

    @Column(nullable = false, length = 20)
    @ApiModelProperty(notes = "The status of the booking (e.g., PENDING, CONFIRMED, CANCELLED)")
    private String status;

    @Column(nullable = false)
    @ApiModelProperty(notes = "The date and time when the booking was created")
    private LocalDateTime createdAt;
}

