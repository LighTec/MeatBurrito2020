package View;
import Model.TwitterResources;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Group;
import twitter4j.TwitterException;

import java.io.IOException;

public class Gui extends Application {

    private static String nameString = "literallyDTrump", outputFileNameString = "TestFile.txt";

    public void init(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(
                getGroup1(),
                javafx.stage.Screen.getPrimary().getBounds().getWidth(),
                javafx.stage.Screen.getPrimary().getBounds().getHeight()));
        stage.show();
    }

    private Group getGroup1() {
        Group x = new Group();

        TextField name = new TextField("literallyDTrump");
        name.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Gui.nameString = name.getText();
            }
        });
        name.setLayoutX(0);
        name.setLayoutY(0);
        x.getChildren().add(name);

        TextField outputFileName = new TextField("TestFile.txt");
        outputFileName.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Gui.outputFileNameString = outputFileName.getText();
            }
        });
        outputFileName.setLayoutX(0);
        outputFileName.setLayoutY(25);
        x.getChildren().add(outputFileName);

        TextArea outputArea = new TextArea("");
        outputArea.setLayoutX(0);
        outputArea.setLayoutY(75);
        outputArea.setPrefRowCount(10);
        x.getChildren().add(outputArea);

        System.out.println("Hello World! \n");

        Button submitButton = new Button("Submit");
        submitButton.setLayoutX(0);
        submitButton.setLayoutY(50);
        submitButton.setOnMouseClicked(new SubmitButtonClick((TextArea)x.getChildren().get(2)));
        x.getChildren().add(submitButton);

        return x;
    }

    private class SubmitButtonClick implements EventHandler<MouseEvent> {
        TextArea outputField;
        private SubmitButtonClick(TextArea outputField) {
            this.outputField = outputField;
        }
        @Override
        public void handle(MouseEvent event) {
            try {
                System.out.println("Hello World!\n");
                TwitterResources.getAnalysisTweets("@" + Gui.nameString, Gui.outputFileNameString, outputField);
            } catch (TwitterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
