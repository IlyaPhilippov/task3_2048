package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private Logic logic;
    private FieldController controller;
    private Gamescreen ui;
    private Stage stage;

    @FXML
    public void play() throws IOException {
        logic = new Logic(4,4);
        logic.newGame();
        ui = new Gamescreen(logic);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/game.fxml"));
        Parent content = loader.load();
        controller = loader.getController();
        controller.createGameField(logic);
        controller.createGamescreen(ui);

        Scene scene = new Scene(content, 500, 400);
        stage.setScene(scene);
        content.requestFocus();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
