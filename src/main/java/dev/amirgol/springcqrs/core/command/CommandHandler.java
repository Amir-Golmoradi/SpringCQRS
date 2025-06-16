package dev.amirgol.springcqrs.core.command;

public interface CommandHandler<C extends Command<R>, R> {
    R handle(C command);
}
