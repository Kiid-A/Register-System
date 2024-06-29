package Model;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @author:
 * @date:
 */

public class Admin extends User {

    public Admin(String id, String name, String address, String tel) {
        super(id, name, address, tel);
    }

    public Admin(ResultSet r) throws SQLException {
        super(r.getString("id"), r.getString("name"), r.getString("address"), r.getString("tel"));
    }
}
