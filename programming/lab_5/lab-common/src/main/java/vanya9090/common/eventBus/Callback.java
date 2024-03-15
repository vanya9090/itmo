package vanya9090.common.eventBus;

import java.io.IOException;

@FunctionalInterface
public interface Callback<T> {
    void call(T data) throws IOException;
}