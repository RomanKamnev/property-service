package stay.space.startup.service;

import stay.space.startup.persistence.entity.Booking;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing bookings.
 */
public interface BookingService {

    /**
     * Retrieves all bookings.
     *
     * @return a list of bookings
     */
    List<Booking> getAllBookings();

    /**
     * Retrieves a booking by its ID.
     *
     * @param id the ID of the booking to retrieve
     * @return an optional containing the found booking or empty if no booking was found
     */
    Optional<Booking> getBookingById(Long id);

    /**
     * Creates a new booking.
     *
     * @param booking the booking to create
     * @return the created booking
     */
    Booking createBooking(Booking booking);

    /**
     * Updates an existing booking.
     *
     * @param id the ID of the booking to update
     * @param bookingDetails the new details of the booking
     * @return an optional containing the updated booking or empty if no booking was found
     */
    Optional<Booking> updateBooking(Long id, Booking bookingDetails);

    /**
     * Deletes a booking by its ID.
     *
     * @param id the ID of the booking to delete
     * @return true if the booking was deleted, false if the booking was not found
     */
    boolean deleteBooking(Long id);

    /**
     * Retrieves bookings by user ID.
     *
     * @param userId the ID of the user who made the bookings
     * @return a list of bookings made by the specified user
     */
    List<Booking> findBookingsByUserId(Long userId);

    /**
     * Retrieves bookings by property ID.
     *
     * @param propertyId the ID of the property the bookings are associated with
     * @return a list of bookings for the specified property
     */
    List<Booking> findBookingsByPropertyId(Long propertyId);

    /**
     * Retrieves bookings by status.
     *
     * @param status the status of the bookings to retrieve
     * @return a list of bookings with the specified status
     */
    List<Booking> findBookingsByStatus(String status);
}

