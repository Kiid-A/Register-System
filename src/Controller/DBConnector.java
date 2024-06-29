package Controller;

import Model.Admin;
import Model.Attendance;
import Model.Doctor;
import Model.Patient;

import java.sql.*;
import java.util.ArrayList;

/**
 * @Description: Provide Basic Connection with a database
 * @author: Kid_A
 * @date: 2024.6.28
 */
public class DBConnector {
    private static volatile DBConnector instance = null;
    private Connection conn = null;
    private Statement statement;

    /**
     * 加载mysql驱动
     */
    private DBConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("not found mysql driver");
            e.printStackTrace();
        }
    }

    /**
     * 线程安全的单例模式
     *
     * @return 数据库连接实例
     */
    public static DBConnector getInstance() {
        if (instance == null) {
            synchronized (DBConnector.class) {
                if (instance == null)
                    instance = new DBConnector();
            }
        }
        return instance;
        //return instanceHolder.instance;
    }

    /**
     * 建立数据库连接
     *
     * @param hostName 主机名
     * @param port     端口号
     * @param dbName   数据库名
     * @param user     用户名
     * @param passwd   密码
     */
    public void connectDB(String hostName, int port, String dbName, String user, String passwd) {
        String url = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName + "?autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&serverTimezone=UTC";
        try {
            conn = DriverManager.getConnection(url, user, passwd);
            statement = conn.createStatement();
        } catch (SQLException throwables) {
            System.out.println("cannot connect to database");
            throwables.printStackTrace();
        }
    }

    /**
     * 关闭数据库连接
     */
    public void closeConnect() {
        try {
            conn.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Admin getAdminInfo(String adminId) {
        try {
            return new Admin(statement.executeQuery(
                    "select * from admin where id =" + adminId));
        } catch (SQLException e) {
            return null;
        }
    }

    public Patient getPatientInfo(String patientId) {
        try {
            return new Patient(statement.executeQuery(
                    "select * from patient where id =" + patientId));
        } catch (SQLException e) {
            return null;
        }
    }

    public Doctor getDoctorInfo(String doctorId) {
        try {
            return new Doctor(statement.executeQuery(
                    "select * from doctor where id =" + doctorId));
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Doctor> getAllDoctor() {
        ArrayList<Doctor> d = new ArrayList<Doctor>();
        try {
            ResultSet r = statement.executeQuery("select * from doctor");
            if (r.next()) {
                d.add(new Doctor(r));
            }
            return d;
        } catch (SQLException e) {
            return null;
        }
    }

    public void modifyAdminInfo(Admin a) throws SQLException {
        String upd = "update admin set name='" + a.getName() + "',address='" + a.getAddress() + "',tel='" + a.getTel() + "',password='" + a.getPassword() + "' where id='" + a.getId() + "'";
        statement.executeUpdate(upd);
    }

    public void modifyPatientInfo(Patient p) throws SQLException {
        String upd = "update patient set name='" + p.getName() +
                "',age = '" + p.getAge() + "',gender = '" + p.getGender() +
                "',address='" + p.getAddress() + "',tel='" + p.getTel() +
                "',password='" + p.getPassword() + "' where id='" + p.getId() + "'";
        statement.executeUpdate(upd);
    }

    public void modifyDoctorInfo(Doctor d) throws SQLException {
        String upd = "update doctor set name='" + d.getName() +
                "',age = '" + d.getAge() + "',gender = '" + d.getGender() +
                "',address='" + d.getAddress() + "',tel='" + d.getTel() +
                "',password='" + d.getPassword() + "',hospital = '" + d.getHospital() +
                "',department='" + d.getDepartment() + "',title = '" + d.getTitle() +
                "',speciality='" + d.getSpeciality() + "' where id='" + d.getId() + "'";
        statement.executeUpdate(upd);
    }

    public void deleteAdmin(String id) throws SQLException {
        String sql = "delete from admin where id='" + id + "'";
        statement.execute(sql);
    }

    public void deletePatient(String id) throws SQLException {
        String sql = "delete from patient where id='" + id + "'";
        statement.execute(sql);
    }

    public void deleteDoctor(String id) throws SQLException {
        String sql = "delete from doctor where id='" + id + "'";
        statement.execute(sql);
    }

    public void insertAttendance(Attendance a) throws SQLException {
        String c = "select * from attendance where doctor_id = '" + a.getDoctorId() + "'";
        ResultSet r = statement.executeQuery(c);
        r.last();
        Integer cnt = r.getRow();

        String sql = "insert into attendance(doctor_id, from_time, to_time, patient_id, " +
                "department, register_time, people_num, id)" + "values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, a.getDoctorId());
        ps.setDate(2, new Date(a.getFrom().getTime()));
        ps.setDate(3, new Date(a.getTo().getTime()));
        ps.setString(4, a.getPatientId());
        ps.setString(5, a.getDepartment());
        ps.setString(6, a.getDepartment());
        ps.setDate(7, new Date(a.getRegisterTime().getTime()));
        ps.setInt(8, a.getPeopleNum());
        ps.setInt(9, cnt + 1);

        ps.execute();
    }
    
    public ArrayList<Attendance> attendanceViaDoctor(String doctorId) throws SQLException {
        String sql = "select * from attendance where doctor_id='" + doctorId + "'";
        ArrayList<Attendance> res = new ArrayList<Attendance>();
        try {
            ResultSet r = statement.executeQuery(sql);
            while(r.next()) {
                res.add(new Attendance(r));
            }
        } catch (SQLException e) {
            return null;   
        }
        return res;
    }

    public void delAttendance(String id) throws SQLException {
        try {
            statement.execute("delete from attendance where attendance_id='" + id + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
