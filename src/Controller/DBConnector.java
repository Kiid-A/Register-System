package Controller;

import Model.Admin;
import Model.Attendance;
import Model.Doctor;
import Model.Patient;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public String getTime(LocalDateTime l) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String res = l.format(formatter);

        return res;
    }

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
            while (r.next()) {
                d.add(new Doctor(r));
            }
            return d;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Patient> getPatient(String doctorId) {
        ArrayList<Patient> p = new ArrayList<Patient>();
        try {
            ResultSet r = statement.executeQuery("select * from patient where doctor_id =" + doctorId);
            while (r.next()) {
                p.add(new Patient(r));
            }
            return p;
        } catch (SQLException e) {
            return null;
        }
    }

    public void insertAdmin(Admin a) throws SQLException {
        String sql = "insert into admin(id, name, address, tel, created_at, deleted_at) " +
                "values(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, a.getId());
        ps.setString(2, a.getName());
        ps.setString(3, a.getAddress());
        ps.setString(4, a.getTel());
        ps.setString(5, getTime(LocalDateTime.now()));
        ps.setString(6, null);

        ps.execute();
    }

    public void insertDoctor(Doctor d) throws SQLException {
        String sql = "insert into doctor(id, name, age, gender, address, tel, hospital, " +
                "department, title, speciality, created_at, deleted_at) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, d.getId());
        ps.setString(2, d.getName());
        ps.setString(3, d.getAge());
        ps.setString(4, d.getGender());
        ps.setString(5, d.getAddress());
        ps.setString(6, d.getTel());
        ps.setString(7, d.getHospital());
        ps.setString(8, d.getDepartment());
        ps.setString(9, d.getTitle());
        ps.setString(10, d.getSpeciality());
        ps.setString(11, getTime(LocalDateTime.now()));
        ps.setString(12, null);

        ps.execute();
    }

    public void insertPatient(Patient p) throws SQLException {
        String sql = "insert into patient(id, name, age, gender, address, tel, " +
                "created_at, deleted_at) " +
                "values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getId());
        ps.setString(2, p.getName());
        ps.setString(3, p.getAge());
        ps.setString(4, p.getGender());
        ps.setString(5, p.getAddress());
        ps.setString(6, p.getTel());
        ps.setString(7, getTime(LocalDateTime.now()));
        ps.setString(8, null);

        ps.execute();
    }

    public void modifyAdminInfo(Admin a) throws SQLException {
        String sql = "update admin set name=?, address=?, tel=?, password=?, created_at=? " +
                "where id = '" + a.getId() + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, a.getName());
        ps.setString(2, a.getAddress());
        ps.setString(3, a.getTel());
        ps.setString(4, a.getPassword());
        ps.setString(5, getTime(LocalDateTime.now()));

        ps.executeUpdate();
    }

    public void modifyPatientInfo(Patient p) throws SQLException {
        String sql = "update patient set name=?, age=?, gender=?, address=?, tel=?, password=?, created_at=? " +
                "where id = '" + p.getId() + "'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getName());
        ps.setString(2, p.getAge());
        ps.setString(3, p.getGender());
        ps.setString(4, p.getAddress());
        ps.setString(5, p.getTel());
        ps.setString(6, p.getPassword());
        ps.setString(7, getTime(LocalDateTime.now()));

        ps.executeUpdate();
    }

    public void modifyDoctorInfo(Doctor d) throws SQLException {
        String sql = "update doctor set name=?, age=?, gender=?, address=?, tel=?, " +
                "password=?, created_at=?, hospital=?, department=?, title=?, speciality=? " +
                "where id = '" + d.getId() + "'";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, d.getName());
        ps.setString(2, d.getAge());
        ps.setString(3, d.getGender());
        ps.setString(4, d.getAddress());
        ps.setString(5, d.getTel());
        ps.setString(6, d.getPassword());
        ps.setString(7, getTime(LocalDateTime.now()));
        ps.setString(8, d.getHospital());
        ps.setString(9, d.getDepartment());
        ps.setString(10, d.getTitle());
        ps.setString(11, d.getSpeciality());

        ps.executeUpdate();
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
        String sql = "insert into attendance(doctor_id, from_time, to_time, patient_id, " +
                "department, register_time, people_num, created_at, deleted_at)" +
                "values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, a.getDoctorId());
        ps.setString(2, a.getFrom());
        ps.setString(3, a.getTo());
        ps.setString(4, a.getPatientId());
        ps.setString(5, a.getDepartment());
        ps.setString(6, a.getRegisterTime());
        ps.setInt(7, a.getPeopleNum());
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = currentDateTime.format(formatter);
        ps.setString(8, now);
        ps.setDate(9, null);

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
            e.printStackTrace();
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

    public ArrayList<Attendance> getTimeOrderAttendance(String doctorId) throws SQLException {
        String sql = "select * from attendance where doctor_id='" + doctorId + "' order by register_time desc";
        ArrayList<Attendance> res = new ArrayList<>();
        try {
            ResultSet r = statement.executeQuery(sql);
            while(r.next()) {
                res.add(new Attendance(r));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }

    public ArrayList<Attendance> getPeriodAttendance(String doctorId, Date from, Date to) throws SQLException {
        String sql = "select * from attendance where doctor_id='" + doctorId + "' and register_time between ? and ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDate(1, from);
        ps.setDate(2, to);
        ArrayList<Attendance> res = new ArrayList<Attendance>();
        try {
            ResultSet r = ps.executeQuery();
            while(r.next()) {
                res.add(new Attendance(r));
            }
        } catch (SQLException e) {
            return null;
        }
        return res;
    }
}
