package Model;

/**
 * @Description: Basic Object of util.User
 * @author: Kid_A
 * @date: 2024.6.24
 */

public class User {
    private String id;
    private String name;
    private String address;
    private String tel;
    private String password;

    public User(String id, String name, String address, String tel) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return new String("id = " + this.id + ", name = " + this.name + ", address = " + this.address + ", tel = " + this.tel);
    }
}
