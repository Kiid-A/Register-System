package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description:
 * @author:
 * @date:
 */
public class Attendance {
    private String attendanceId;
    private String doctorId;
    private String from;
    private String to;
    private String patientId;
    private String department;
    private String registerTime;
    private Integer peopleNum;

    public Attendance(String doctorId, String from, String to, String patientId, String department, String registerTime, Integer peopleNum) {
        this.doctorId = doctorId;
        this.from = from;
        this.to = to;
        this.patientId = patientId;
        this.department = department;
        this.registerTime = registerTime;
        this.peopleNum = peopleNum;
    }

    public Attendance(ResultSet r) throws SQLException {
        this.doctorId = r.getString("doctor_id");
        this.from = r.getString("from_time");
        this.to = r.getString("to_time");
        this.patientId = r.getString("patient_id");
        this.department = r.getString("department");
        this.registerTime = r.getString("register_time");
        this.peopleNum = r.getInt("people_num");
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDepartment() {
        return department;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    @Override
    public String toString() {
        return new String("doctorId = " + this.doctorId + "from = " + this.from +
                "to = " + this.to + "patient id = " + this.patientId + "department = " + this.department);
    }
}

