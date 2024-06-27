package Model;

/**
 * @Description:
 * @author:
 * @date:
 */
public class Patient extends User {
    private String age;
    private String gender;
    private MedicalRecord medicalRecord;

    public Patient(String id, String name, String identity, String address, String tel,
                   String age, String gender, MedicalRecord medicalRecord) {
       super(id, name, identity, address, tel);
       this.age = age;
       this.gender = gender;
       this.medicalRecord = medicalRecord;
    }

}
