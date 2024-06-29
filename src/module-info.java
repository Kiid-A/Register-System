module hospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swing;
    requires static lombok;


    opens Main to javafx.fxml;
    exports Main to javafx.graphics;
}
