package stay.space.startup.web;

import org.springframework.security.access.prepost.PreAuthorize;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import stay.space.startup.messaging.Customer;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send-message")
public class MessagingController {

    private final ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/publish")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public void sendEvents(@RequestBody Customer customer) {
        applicationEventPublisher.publishEvent(customer);
    }
}
