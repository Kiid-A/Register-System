package Test;

import Controller.DBConnector;
import Model.Admin;
import Model.Admin;
import Model.Attendance;
import Model.Doctor;
import Model.Patient;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @Description:
 * @author:
 * @date:
 */
public class DBTest {
    public static void main(String[] args) throws SQLException {
        DBConnector.getInstance().connectDB("localhost",3306,
                "hospital","root","1234");

    }

    public String getTime(LocalDateTime l) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String res = l.format(formatter);

        return res;
    }

    @Test
    public void insertAtTest()  throws SQLException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String from = getTime(currentDateTime);
        String to = getTime(currentDateTime.plusDays(1));

        Attendance a = new Attendance("2022210109", from, to, "2022210108",
                "肛肠科", from, 5);
        DBConnector.getInstance().connectDB("localhost",3306,
                "hospital","root","1234");
        DBConnector.getInstance().insertAttendance(a);
    }

    @Test
    public void insertUserTest()  throws SQLException {
        DBConnector.getInstance().connectDB("localhost",3306,
                "hospital","root","1234");

        Admin a = new Admin("2022210109", "陈俊杰", "北京邮电大学学二",
                "10086");

        Doctor d = new Doctor("2022210109", "陈俊杰", "北京邮电大学",
                "10086", "19", "male", "北京邮电大学校医院",
                "痔疮切除", "主任", "肛肠科");

        Patient p = new Patient("2022210109", "陈俊杰", "北京邮电大学", "10086", "19", "male", "2022210109");

        DBConnector.getInstance().insertAdmin(a);
        DBConnector.getInstance().insertDoctor(d);
        DBConnector.getInstance().insertPatient(p);
    }

    @Test
    public void getUserTest() throws SQLException {
        DBConnector.getInstance().connectDB("localhost",3306,
                "hospital","root","1234");

        ArrayList<Doctor> d = DBConnector.getInstance().getAllDoctor();
        ArrayList<Patient> p = DBConnector.getInstance().getPatient("2022210109");
        for (Doctor doctor : d) {
            System.out.println(doctor.toString());
        }
        for (Patient patient : p) {
            System.out.println(patient.toString());
        }
    }

    @Test
    public void modifyInfoTest() throws SQLException {
        Admin a = new Admin("2022210109", "kid_a", "北京邮电大学学二",
                "10086");

        Doctor d = new Doctor("2022210109", "kid_a", "北京邮电大学",
                "10086", "19", "male", "北京邮电大学校医院",
                "痔疮切除", "主任", "肛肠科");

        Patient p = new Patient("2022210109", "kid_a", "北京邮电大学", "10086", "19", "male", "2022210109");

        DBConnector.getInstance().connectDB("localhost",3306,
                "hospital","root","1234");

        DBConnector.getInstance().modifyAdminInfo(a);
        DBConnector.getInstance().modifyDoctorInfo(d);
        DBConnector.getInstance().modifyPatientInfo(p);
    }

    @Test
    public void deleteTest() throws SQLException {
        DBConnector.getInstance().connectDB("localhost",3306,
                "hospital","root","1234");

        DBConnector.getInstance().deleteDoctor("2022210108");
        DBConnector.getInstance().deleteAdmin("2022210108");
        DBConnector.getInstance().deletePatient("2022210108");
    }

    @Test
    public void getAttendanceTest() throws SQLException {
        DBConnector.getInstance().connectDB("localhost",3306,
                "hospital","root","1234");

//        DBConnector.getInstance().getPeriodAttendance("2022210109", );
        ArrayList<Attendance> b = DBConnector.getInstance().getTimeOrderAttendance("2022210109");
        ArrayList<Attendance> a = DBConnector.getInstance().attendanceViaDoctor("2022210109");
        for (Attendance attendance : a) {
            System.out.println(attendance.toString());
        }
        for(Attendance attendance : b) {
            System.out.println(attendance.toString());
        }
    }
}
