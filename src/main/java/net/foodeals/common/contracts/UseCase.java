package net.foodeals.common.contracts;

public interface UseCase<Argument, Result> {
    Result execute(Argument arg);
}
