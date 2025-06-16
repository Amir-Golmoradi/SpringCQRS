package dev.amirgol.springcqrs.core.query;

/**
 * Marker interface for queries.
 * Created by: <br /> Amir-Golmoradi
 * Created on: 2025-06-14 18:27:03
 * <br />
 * <br />
 * Marker interface for queries.
 * Queries represent a request for data and do not change the state of the system.
 * They are typically immutable data-only objects.
 *
 * @param <R> The type of result returned by the QueryHandler after processing this query.
 */
public interface Query<R> {
}
