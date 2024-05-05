package vanya9090.server.managers;

import vanya9090.common.models.HumanBeing;

import java.util.Deque;

public interface StorageManager {
    Deque<HumanBeing> read() throws Exception;
    void write(Deque<HumanBeing> collection) throws Exception;
}
