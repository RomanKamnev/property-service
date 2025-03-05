package stay.space.startup.service.impl;

import org.springframework.stereotype.Service;
import stay.space.startup.persistence.entity.DroneFilmingOrder;
import stay.space.startup.persistence.repository.DroneFilmingOrderRepository;
import stay.space.startup.service.DroneFilmingOrderService;

import java.util.List;
import java.util.Optional;

@Service
public class DroneFilmingOrderServiceImpl implements DroneFilmingOrderService {

    private final DroneFilmingOrderRepository repository;

    public DroneFilmingOrderServiceImpl(DroneFilmingOrderRepository repository) {
        this.repository = repository;
    }

    public List<DroneFilmingOrder> getAllOrders() {
        return repository.findAll();
    }

    public Optional<DroneFilmingOrder> getOrderById(Long id) {
        return repository.findById(id);
    }

    public DroneFilmingOrder createOrder(DroneFilmingOrder order) {

        return repository.save(order);
    }

    public DroneFilmingOrder updateOrder(Long id, DroneFilmingOrder orderDetails) {
        return repository.findById(id).map(order -> {
            order.setCustomerName(orderDetails.getCustomerName());
            order.setDate(orderDetails.getDate());
            order.setAddress(orderDetails.getAddress());
            order.setPrice(orderDetails.getPrice());
            return repository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}
