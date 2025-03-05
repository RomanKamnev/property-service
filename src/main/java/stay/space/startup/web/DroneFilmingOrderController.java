package stay.space.startup.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import stay.space.startup.persistence.entity.DroneFilmingOrder;
import stay.space.startup.service.DroneFilmingOrderService;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class DroneFilmingOrderController {

    private final DroneFilmingOrderService service;

    public DroneFilmingOrderController(DroneFilmingOrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<DroneFilmingOrder> getOrderById(@PathVariable Long id) {
        Optional<DroneFilmingOrder> order = service.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public DroneFilmingOrder createOrder(@RequestBody DroneFilmingOrder order) {
        return service.createOrder(order);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<DroneFilmingOrder> updateOrder(@PathVariable Long id, @RequestBody DroneFilmingOrder orderDetails) {
        try {
            return ResponseEntity.ok(service.updateOrder(id, orderDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
