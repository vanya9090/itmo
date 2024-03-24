package vanya9090.common.connection;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Connection implements Serializable {
    private final Map<String, String> packet = new HashMap<>();

    public void setPacket(String header, String value) {
        this.packet.put(header, value);
    }

    public String getValue(String header) {
        return this.packet.get(header);
    }
}
