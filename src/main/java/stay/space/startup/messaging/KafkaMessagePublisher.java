package stay.space.startup.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessagePublisher {

    private final KafkaTemplate<String, Property> template;

    public KafkaMessagePublisher(KafkaTemplate<String, Property> template) {
        this.template = template;
    }

    @TransactionalEventListener(fallbackExecution = true)
    public void sendEventsToTopic(Property message) {
        try {
            CompletableFuture<SendResult<String, Property>> future = template.send("customers", message);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Sent message=[" + message.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    log.error("Unable to send message=[" +
                            message.toString() + "] due to : " + ex.getMessage());
                }
            });
        } catch (Exception ex) {
            log.error("ERROR : " + ex.getMessage());
        }
    }

}
