package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @author:
 * @date:
 */
public class Doctor extends User {
    private String age;
    private String gender;
    private String hospital;
    private String speciality;
    private String title;
    private String department;

    public Doctor(String id, String name, String address, String tel,
                  String age, String gender, String hospital, String speciality, String title, String department) {
        super(id, name, address, tel);
        this.age = age;
        this.gender = gender;
        this.hospital = hospital;
        this.speciality = speciality;
        this.title = title;
        this.department = department;
    }

    public Doctor(ResultSet r) throws SQLException {
        super(r.getString("id"), r.getString("name"), r.getString("address"), r.getString("tel"));
        this.age = r.getString("age");
        this.gender = r.getString("gender");
        this.hospital = r.getString("hospital");
        this.speciality = r.getString("speciality");
        this.title = r.getString("title");
        this.department = r.getString("department");
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getHospital() {
        return hospital;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return department;
    }
}
