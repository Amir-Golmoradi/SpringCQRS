package dev.amirgol.springcqrs.application.query.handlers;

import dev.amirgol.springcqrs.application.dto.mapper.CustomerMapper;
import dev.amirgol.springcqrs.application.dto.CustomerResponse;
import dev.amirgol.springcqrs.application.query.FindCustomerEmailQuery;
import dev.amirgol.springcqrs.core.query.QueryHandler;
import dev.amirgol.springcqrs.domain.event.DomainEventPublisher;
import dev.amirgol.springcqrs.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindCustomerEmailHandler implements QueryHandler<FindCustomerEmailQuery, CustomerResponse> {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final DomainEventPublisher domainEventPublisher;

    /**
     * @param query The query to be processed.
     * @return email
     */
    @Override
    @Transactional
    public CustomerResponse handle(FindCustomerEmailQuery query) {
        return customerRepository.findByEmail(query.email())
                .stream()
                .map(customerMapper::toResponse)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no customer with email: " + query.email()));
    }
}
