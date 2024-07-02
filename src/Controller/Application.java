package Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description:
 * @author:
 * @date:
 */
public class Application {
    public String getTime(LocalDateTime l) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String res = l.format(formatter);

        return res;
    }
}
