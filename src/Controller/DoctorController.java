package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.print.Doc;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @Description:
 * @author:
 * @date:
 */

public class DoctorController {

    static class Register {
        public StringProperty registerNumber;
        public StringProperty patientName;
        public StringProperty registerDate;

        public Register(String registerNumber, String patientName, String registerDate, int isSp) {
            this.registerNumber = new SimpleStringProperty(registerNumber);
            this.patientName = new SimpleStringProperty(patientName);
            this.registerDate = new SimpleStringProperty(registerDate);
        }
    }

    static class DoctorInfo {
        public StringProperty departmentName;
        public StringProperty doctorId;
        public StringProperty doctorName;
        public StringProperty registerCount;

        public DoctorInfo(String departmentName, String doctorId, String doctorName, int isSp, int registerCount, double income) {
            this.departmentName = new SimpleStringProperty(departmentName);
            this.doctorId = new SimpleStringProperty(doctorId);
            this.doctorName = new SimpleStringProperty(doctorName);
            this.registerCount = new SimpleStringProperty(Integer.toString(registerCount));
        }
    }

    @FXML
    private Label welcomeLabel;
    @FXML
    private Button exitButton;
    @FXML
    private Label startLabel;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endLabel;
    @FXML
    private Label endTimeLabel;
    @FXML
    private DatePicker pickDateStart;
    @FXML
    private DatePicker pickDateEnd;
    @FXML
    private TabPane mainPane;  //外层tab
    @FXML
    private Tab patinetsTab;  //病人列表页
    @FXML
    private TableView<Register> patientTable;  //病人列表
    @FXML
    private TableColumn<Register, String> registerNum;  //挂号编号
    @FXML
    private TableColumn<Register, String> patientName;  //病人名称
    @FXML
    private TableColumn<Register, String> registerDate;  //挂号时间

    @FXML
    private TableColumn<DoctorInfo, String> departmentName; //科室名
    @FXML
    private TableColumn<DoctorInfo, String> docId;  //医生编号
    @FXML
    private TableColumn<DoctorInfo, String> docName;  //医生名称
    @FXML
    private TableColumn<DoctorInfo, String> isSpecial;  //号种类别
    @FXML
    private TableColumn<DoctorInfo, String> registerCount;  //挂号人次

    private static String doctorId;  //医生编号
    private static String doctorName;  //医生名称

    private ObservableList<Register> patientsList = FXCollections.observableArrayList();
    private ObservableList<DoctorInfo> incomesList = FXCollections.observableArrayList();

    public static String getDoctorId() {
        return doctorId;
    }

    public static void setDoctorId(String doctorId) {
        DoctorController.doctorId = doctorId;
    }

    public static String getDoctorName() {
        return doctorName;
    }

    public static void setDoctorName(String doctorName) {
        DoctorController.doctorName = doctorName;
    }

}
