package dev.amirgol.springcqrs.core.query;

/**
 * Interface for handling a specific type of Query.
 * Each QueryHandler is responsible for retrieving data, typically from a read model,
 * without modifying the system's state.
 *
 * @param <Q> The specific Query type that this handler processes.
 * @param <R> The type of result returned after processing the Query.
 */
public interface QueryHandler<Q extends Query<R>, R> {
    /**
     * Handles the given query, retrieving the associated data.
     *
     * @param query The query to be processed.
     * @return The data result of the query.
     */

    R handle(Q query);
}
