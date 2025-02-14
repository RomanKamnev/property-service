package stay.space.startup.service.impl;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import stay.space.startup.persistence.entity.Booking;
import stay.space.startup.persistence.repository.BookingPageAndSortRepository;
import stay.space.startup.persistence.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Getter @Setter
@RequiredArgsConstructor
public class BookingServiceImpl {

    private static final Integer PAGE_SIZE_1000 = 1000;

    private final BookingRepository bookingRepository;
    private final BookingPageAndSortRepository bookingPageAndSortRepository;
    private final TransactionTemplate transactionTemplate;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Transactional
    public void updateCheckInDate(List<Long> bookingIds, String status) {
        Page<Booking> bookings = bookingPageAndSortRepository.findAllByIdIn(bookingIds, PageRequest.of(0, PAGE_SIZE_1000, Sort.Direction.ASC, "createdAt"));
        int totalPages = bookings.getTotalPages();

        for (int i = totalPages - 1; i >= 0; i--) {
            bookings = bookingPageAndSortRepository.findAllByIdIn(bookingIds, PageRequest.of(0, PAGE_SIZE_1000, Sort.Direction.ASC, "createdAt"));
            Page<Booking> finalMultiStopDeliveryOrders = bookings;
            try {
                transactionTemplate.execute(
                        tsStatus -> {
                            setUpdatedAtFieldValue(finalMultiStopDeliveryOrders.getContent(), status);
                            return null;
                        }
                );
            } catch (RuntimeException ex) {
                log.error("Unable to set update at field value, page = {}", i, ex);
            }

        }
    }

    private void setUpdatedAtFieldValue(List<Booking> multiStopDeliveryOrders, String status) {
        multiStopDeliveryOrders.forEach(order -> order.setStatus(status));
        bookingRepository.saveAll(multiStopDeliveryOrders);
    }


    @Cacheable(value = "getBookingById")
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> updateBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setCheckInDate(bookingDetails.getCheckInDate());
                    booking.setCheckOutDate(bookingDetails.getCheckOutDate());
                    booking.setTotalPrice(bookingDetails.getTotalPrice());
                    booking.setStatus(bookingDetails.getStatus());
                    return bookingRepository.save(booking);
                });
    }

    public boolean deleteBooking(Long id) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    bookingRepository.delete(booking);
                    return true;
                })
                .orElse(false);
    }

    public List<Booking> findBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> findBookingsByPropertyId(Long propertyId) {
        return bookingRepository.findByPropertyId(propertyId);
    }

    public List<Booking> findBookingsByStatus(String status) {
        return bookingRepository.findByStatus(status);
    }
}
