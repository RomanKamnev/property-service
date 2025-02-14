package stay.space.startup.web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stay.space.startup.persistence.entity.Booking;
import stay.space.startup.service.impl.BookingServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@Api(value = "Booking Management System", tags = {"Bookings"})
public class BookingController {

    private final BookingServiceImpl bookingServiceImpl;

    @Autowired
    public BookingController(BookingServiceImpl bookingServiceImpl) {
        this.bookingServiceImpl = bookingServiceImpl;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @ApiOperation(value = "View a list of all bookings", response = List.class)
    public List<Booking> getAllBookings() {
        return bookingServiceImpl.getAllBookings();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @ApiOperation(value = "Get a booking by ID", response = Booking.class)
    public ResponseEntity<Booking> getBookingById(
            @ApiParam(value = "ID of the booking to retrieve", required = true)
            @PathVariable Long id) {
        Optional<Booking> booking = bookingServiceImpl.getBookingById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @ApiOperation(value = "Create a new booking")
    public Booking createBooking(
            @ApiParam(value = "Booking object to store in the database", required = true)
            @RequestBody Booking booking) {
        return bookingServiceImpl.createBooking(booking);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @ApiOperation(value = "Update an existing booking")
    public ResponseEntity<Booking> updateBooking(
            @ApiParam(value = "ID of the booking to update", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated booking object", required = true)
            @RequestBody Booking bookingDetails) {
        Optional<Booking> updatedBooking = bookingServiceImpl.updateBooking(id, bookingDetails);
        return updatedBooking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @ApiOperation(value = "Delete a booking")
    public ResponseEntity<Void> deleteBooking(
            @ApiParam(value = "ID of the booking to delete", required = true)
            @PathVariable Long id) {
        boolean isDeleted = bookingServiceImpl.deleteBooking(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

