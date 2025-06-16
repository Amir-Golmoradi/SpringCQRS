package dev.amirgol.springcqrs.core.command;

/**
 * Marker interface for commands.
 * Created by: Amir-Golmoradi
 * Created on: 2025-06-14 18:27:03
 <br/>
 * Marker interface for commands.
 * Commands represent an intent to change the state of the system.
 * They are typically immutable data-only objects.
 *
 * @param <R> The type of result returned by the CommandHandler after processing this command.
 */
public interface Command<R> {

}
