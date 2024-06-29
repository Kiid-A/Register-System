package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @Description:
 * @author:
 * @date:
 */
public class Attendance {
    private String attendanceId;
    private String doctorId;
    private Date from;
    private Date to;
    private String patientId;
    private String department;
    private Date registerTime;
    private Integer peopleNum;

    public Attendance(String doctorId, Date from, Date to, String patientId, String department, Date registerTime, Integer peopleNum) {
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
        this.from = r.getDate("from");
        this.to = r.getDate("to");
        this.patientId = r.getString("patient_id");
        this.department = r.getString("department");
        this.registerTime = r.getDate("register_time");
        this.peopleNum = r.getInt("people_num");
    }

    public String getDoctorId() {
        return doctorId;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDepartment() {
        return department;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }
}

