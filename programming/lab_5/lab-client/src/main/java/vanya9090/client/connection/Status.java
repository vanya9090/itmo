package vanya9090.client.connection;

public enum Status {
    OK(200),
    SERVER_ERROR(500);

    public final int code;

    Status(int code) {
        this.code = code;
    }
}