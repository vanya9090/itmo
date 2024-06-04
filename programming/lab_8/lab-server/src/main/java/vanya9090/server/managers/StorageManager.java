package vanya9090.server.managers;

import vanya9090.common.models.HumanBeing;
import vanya9090.common.models.User;

import java.util.Deque;
import java.util.Map;

public interface StorageManager {
    Integer add(HumanBeing humanBeing, User user) throws Exception;
    Deque<HumanBeing> read() throws Exception;
    void write(Deque<HumanBeing> collection) throws Exception;
    void remove(int id, User user) throws Exception;
    void deleteByUser(User user) throws Exception;
    void update(HumanBeing humanBeing, int id, User user) throws Exception;
    String getUserByHumanId(Integer humanBeingId) throws Exception;
    Map<Integer, String> getUsersHumans() throws Exception;
}
