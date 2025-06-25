package dev.amirgol.springcqrs.presentation;

import dev.amirgol.springcqrs.core.command.CommandBus;
import dev.amirgol.springcqrs.core.query.QueryBus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final QueryBus queryBus;
    private final CommandBus commandBus;
}
