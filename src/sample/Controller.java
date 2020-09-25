package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.IOException;

public class Controller {
    private Logic logic;
    private Interface ui;
    private Stage stage;

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public void setInterface(Interface ui) {
        this.ui = ui;
    }


    @FXML
    public void up() {
        logic.moveUp();
    }
    @FXML
    public void down() {
        logic.moveDown();
    }
    @FXML
    public void left() {
        logic.moveLeft();
    }
    @FXML
    public void right() {
        logic.moveRight();
    }

    @FXML
    public void play() throws IOException {
        logic = new Logic(4,4);
        ui = new Interface(logic);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/game.fxml"));
        Parent content = loader.load();

        Scene scene = new Scene(content, 570, 393);
        stage.setScene(scene);
        content.requestFocus();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
