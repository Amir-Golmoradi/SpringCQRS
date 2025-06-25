package dev.amirgol.springcqrs.application.query.handlers;

import dev.amirgol.springcqrs.application.dto.mapper.CustomerMapper;
import dev.amirgol.springcqrs.application.dto.CustomerResponse;
import dev.amirgol.springcqrs.application.query.FindCustomerIdQuery;
import dev.amirgol.springcqrs.core.query.QueryHandler;
import dev.amirgol.springcqrs.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindCustomerIdHandler implements QueryHandler<FindCustomerIdQuery, CustomerResponse> {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    /**
     * @param query The query to be processed.
     * @return
     */
    @Override
    public CustomerResponse handle(FindCustomerIdQuery query) {
        return customerRepository
                .findById(query.id())
                .stream()
                .map(customerMapper::toResponse)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no customer with id: " + query.id()));
    }
}
