package dev.amirgol.springcqrs.core.command;

/**
 * Marker interface for commands.
 * Created by: Amir-Golmoradi
 * Created on: 2025-06-14 18:27:03  =>

 * Interface for handling a specific type of Command.
 * Each CommandHandler is responsible for orchestrating the domain logic
 * required to execute the Command.
 *
 * @param <C> The specific Command type that this handler processes.
 * @param <R> The type of result returned after processing the Command.
 */
public interface CommandHandler<C extends Command<R>, R> {
    /**
     * Handles the given command, executing the associated business logic.
     *
     * @param command The command to be processed.
     * @return The result of the command execution.
     */

    R handle(C command);
}
