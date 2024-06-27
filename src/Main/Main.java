package Main;

/**

* @Description:
* @author: 
* @date: 
*/

import Controller.DBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public void start(Stage stage) throws Exception {
        DBConnector.getInstance().connectDB("localhost",3306,
                "hospital","root","1234");
        stage.setTitle("简易医院挂号系统");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("src/src/login.fxml")));
        Scene scene = new Scene(root, 598, 343);
        stage.setScene(scene);
        stage.show();
    }
}