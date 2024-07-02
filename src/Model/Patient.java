package Model;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @author:
 * @date:
 */
public class Patient extends User {
    private String age;
    private String gender;
    private String doctorId;
    private MedicalRecord medicalRecord;

    public Patient(String id, String name, String address, String tel,
                   String age, String gender, String doctorId) {
       super(id, name, address, tel);
       this.age = age;
       this.gender = gender;
       this.doctorId = doctorId;
//       this.medicalRecord = medicalRecord;
    }

    public Patient(ResultSet r) throws SQLException {
        super(r.getString("id"), r.getString("name"), r.getString("address"), r.getString("tel"));
        this.age = r.getString("age");
        this.gender = r.getString("gender");
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public String getDoctorId() {
        return doctorId;
    }
}
