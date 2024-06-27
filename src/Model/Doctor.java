package Model;

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

    public Doctor(String id, String name, String identity, String address, String tel,
                  String age, String gender, String hospital, String speciality, String title, String department) {
        super(id, name, identity, address, tel);
        this.age = age;
        this.gender = gender;
        this.hospital = hospital;
        this.speciality = speciality;
        this.title = title;
        this.department = department;
    }
}
