package stay.space.startup.service;

import stay.space.startup.persistence.entity.DroneFilmingOrder;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing drone filming orders.
 */
public interface DroneFilmingOrderService {

    /**
     * Retrieves all drone filming orders.
     *
     * @return a list of all orders.
     */
    List<DroneFilmingOrder> getAllOrders();

    /**
     * Retrieves a specific drone filming order by its ID.
     *
     * @param id the ID of the order.
     * @return an {@link Optional} containing the order if found, otherwise empty.
     */
    Optional<DroneFilmingOrder> getOrderById(Long id);

    /**
     * Creates a new drone filming order.
     *
     * @param order the order to create.
     * @return the created order.
     */
    DroneFilmingOrder createOrder(DroneFilmingOrder order);

    /**
     * Updates an existing drone filming order.
     *
     * @param id the ID of the order to update.
     * @param orderDetails the updated order details.
     * @return the updated order.
     * @throws RuntimeException if the order with the given ID is not found.
     */
    DroneFilmingOrder updateOrder(Long id, DroneFilmingOrder orderDetails);

    /**
     * Deletes a drone filming order by its ID.
     *
     * @param id the ID of the order to delete.
     */
    void deleteOrder(Long id);
}
