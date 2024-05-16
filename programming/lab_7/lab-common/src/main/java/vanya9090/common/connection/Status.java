package vanya9090.common.connection;

public enum Status {
    OK(200),
    FORBIDDEN(400),
    SERVER_ERROR(500),
    CREATED(201);

    public final int code;

    Status(int code) {
        this.code = code;
    }
}