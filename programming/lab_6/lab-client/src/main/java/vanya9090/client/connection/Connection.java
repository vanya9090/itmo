package vanya9090.client.connection;

import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;

public abstract class Connection {
    public abstract Response request(Request request) throws Exception;
}