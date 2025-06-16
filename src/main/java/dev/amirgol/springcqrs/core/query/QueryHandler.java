package dev.amirgol.springcqrs.core.query;

public interface QueryHandler<Q extends Query<R>, R> {
    R handle(Q query);
}
