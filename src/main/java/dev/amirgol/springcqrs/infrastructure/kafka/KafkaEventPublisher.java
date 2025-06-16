package dev.amirgol.springcqrs.infrastructure.kafka;

import dev.amirgol.springcqrs.domain.event.DomainEvent;
import dev.amirgol.springcqrs.domain.event.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaEventPublisher implements DomainEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(event -> {
                    var topic = resolveTopic(event);
                }
        );
    }

    private String resolveTopic(DomainEvent event) {
        return event.getClass().getSimpleName().toLowerCase();
    }

}
