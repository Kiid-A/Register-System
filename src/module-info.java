module hospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swing;
    requires junit;


    opens Main to javafx.fxml;
    exports Main to javafx.graphics, junit;
    exports Test to junit;
}
