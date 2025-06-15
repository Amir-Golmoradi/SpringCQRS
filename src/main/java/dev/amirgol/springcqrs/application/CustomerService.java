package dev.amirgol.springcqrs.application;

import dev.amirgol.springcqrs.application.dto.CustomerDto;
import dev.amirgol.springcqrs.domain.model.Customer;
import dev.amirgol.springcqrs.domain.vo.CustomerId;
import dev.amirgol.springcqrs.domain.vo.Email;
import dev.amirgol.springcqrs.domain.vo.FullName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CustomerService {
    // ============= QUERIES =============
    Optional<CustomerDto> findById(CustomerId id);
    Optional<CustomerDto> findByName(FullName fullName);
    Optional<CustomerDto> findByEmail(Email email);

    // ============= COMMANDS =============

    void save(Customer customer);
    void update(Customer customer);
    void deleteByEmail(Email email);

}
