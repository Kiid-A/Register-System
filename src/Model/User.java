package Model;

/**
 * @Description: Basic Object of util.User
 * @author: Kid_A
 * @date: 2024.6.24
 */
public class User {
    private String id;
    private String name;
    private String identity;
    private String address;
    private String tel;
    private String userId;
    private String password;

    public User(String id, String name, String identity, String address, String tel) {
        this.id = id;
        this.name = name;
        this.identity = identity;
        this.address = address;
        this.tel = tel;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public String getIdentity() {
        return identity;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

}
