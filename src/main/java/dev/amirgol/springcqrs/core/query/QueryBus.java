package dev.amirgol.springcqrs.core.query;

/**
 * The QueryBus is responsible for dispatching queries to their respective QueryHandlers.
 * It acts as a central mediator, decoupling the query sender from the query receiver.
 */
public interface QueryBus {
    /**
     * Dispatches a query to the appropriate QueryHandler.
     *
     * @param query The query to dispatch.
     * @param <R> The expected return type of the query handling.
     * @return The data result returned by the QueryHandler.
     * @throws IllegalArgumentException if no handler is found for the given query type.
     */
    <R> R dispatch(Query<R> query);
}