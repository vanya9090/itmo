package vanya9090.common.eventBus;

import java.io.IOException;
import java.util.HashMap;

public class EventBus {
    public static final HashMap<String, Callback<Object>> callbacks = new HashMap<>();

    public static void on(String event, Callback<Object> callback) {
        EventBus.callbacks.put(event, callback);
    }

    public static void emit(String event, Object data) {
        try {
            EventBus.callbacks.get(event).call(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}