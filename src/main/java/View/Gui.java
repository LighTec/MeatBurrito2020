package View;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Gui extends Application {

    private Stage stage = null;
    
    @Override
    public void start(Stage stage) {
	this.stage = stage;
	this.setGroup(getOpeningGroup());
    }

    private void setGroup(Group group) {
	this.stage.setScene(new Scene(group, 500, 500));
	this.stage.show();
    }

    private Group getOpeningGroup() {
	Group x = new Group();

        TextField userInput = new TextField("");
	userInput.setLayoutX(0);
	userInput.setLayoutY(0);
	x.getChildren().add(userInput);

	Button submitButton = new Button("SUBMIT");
	submitButton.setLayoutX(0);
	submitButton.setLayoutY(25);
	submitButton.setOnMouseClicked(new SubmitButtonOutput());
	x.getChildren().add(submitButton);
	
	return x;
    }

    private class SubmitButtonOutput implements EventHandler<MouseEvent> {
	@Override
	public void handle(MouseEvent event) {
	    System.out.println("Hello World!");
	}
    }
    /*
    public static void main(String args[]) {
	launch(args);
    }*/
}
