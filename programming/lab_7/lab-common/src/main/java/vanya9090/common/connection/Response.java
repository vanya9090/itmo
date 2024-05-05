package vanya9090.common.connection;

public class Response  extends Connection {
    private Object[] body = new String[]{""};
    private String message = "";
    public  Status code = Status.OK;

    public Response withMessage(String message) {
        this.message = message;
        return this;
    }

    public Response(String message, Status code) {
        this.message = message;
        this.code = code;
    }

    public Response(Object[] body, Status code) {
        this.body = body;
        this.code = code;
    }

    public void setBody(Object[] body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public Object[] getBody() {
        return this.body;
    }
    public Object getCode() {
        return this.code;
    }
}
