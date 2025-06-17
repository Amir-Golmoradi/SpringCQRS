package dev.amirgol.springcqrs.application.command;

import dev.amirgol.springcqrs.core.command.Command;
import dev.amirgol.springcqrs.core.command.CommandBus;
import dev.amirgol.springcqrs.core.command.CommandHandler;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class SpringCommandBus implements CommandBus {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCommandBus.class);
    private final ApplicationContext applicationContext;
    // Map to store handlers: CommandType -> CommandHandler instance
    private final Map<Class<? extends Command<?>>, CommandHandler<?, ?>> commandHandlers = new HashMap<>();

    public SpringCommandBus(ApplicationContext applicationContext) {
        this.applicationContext = Objects.requireNonNull(applicationContext, "ApplicationContext must not be null");
    }

    /**
     * This method is called after the Spring bean has been initialized.
     * It scans the ApplicationContext for all CommandHandler beans and
     * registers them in the internal map based on the Command type they handle.
     */

    @PostConstruct
    public void init() {
        applicationContext.getBeansOfType(CommandHandler.class)
                .forEach((name, handler) -> {
                    ResolvableType resolvableType = ResolvableType.forClass(handler.getClass());
                    ResolvableType commandType = resolvableType.as(CommandHandler.class).getGeneric(0);

                    if (!commandType.isAssignableFrom(ResolvableType.NONE)) {
                        Class<? extends Command<?>> commandClass = (Class<? extends Command<?>>) commandType.resolve();
                        commandHandlers.put(commandClass, handler);
                    }
                });

        if (commandHandlers.isEmpty()) {
            LOGGER.warn("Warning: No CommandHandler beans found by SpringCommandBus.");
        } else {
            LOGGER.info("SpringCommandBus initialized with {} handlers.", commandHandlers.size());
        }
    }

    @Override
    public <R> R dispatch(Command<R> command) {
        return null;
    }
}
