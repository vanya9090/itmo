package vanya9090.client.connection;

import java.io.Serializable;

public class Response implements Serializable {
    private final String body;
    public final Status code;
    private final Integer client;

    public Response(Object body, Status code, Integer client) {
        this.body = (String) body;
        this.code = code;
        this.client = client;
    }

    public Integer getClient() {
        return client;
    }

    public String getBody() {
        return this.body;
    }
}