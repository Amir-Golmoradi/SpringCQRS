package dev.amirgol.springcqrs.core.command;

/**
 * The CommandBus is responsible for dispatching commands to their respective CommandHandlers.
 * It acts as a central mediator, decoupling the command sender from the command receiver.
 */
public interface CommandBus {
    /**
     * Dispatches a command to the appropriate CommandHandler.
     *
     * @param command The command to dispatch.
     * @param <R>     The expected return type of the command handling.
     * @return The result returned by the CommandHandler.
     * @throws IllegalArgumentException if no handler is found for the given command type.
     */
    <R> R dispatch(Command<R> command);
}
