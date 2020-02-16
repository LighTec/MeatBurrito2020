package View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;


public class Gui extends Application {

    public void init(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new Group(), 100, 100));
        stage.show();
    }
}
