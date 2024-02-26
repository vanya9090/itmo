package utils;

import java.time.LocalDateTime;
import java.util.Date;

public interface ILogger {
    void info(Object obj);
    void success(Object obj);
    void warning(Object obj);
    void error(Object obj);
    void table(Object obj1, Object obj2);
    void field(Object obj);
}
