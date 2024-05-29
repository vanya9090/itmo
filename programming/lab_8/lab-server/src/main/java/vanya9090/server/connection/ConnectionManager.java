package vanya9090.server.connection;

import vanya9090.common.connection.ICallback;
import vanya9090.common.connection.Request;

public abstract class ConnectionManager {
    public ICallback<Request> requestCallback;

    public abstract void run();

    public void setRequestCallback(ICallback<Request> requestCallback) {
        this.requestCallback = requestCallback;
    }
}