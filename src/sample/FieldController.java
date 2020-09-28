package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;

public class FieldController {

    private Logic logic;
    private Gamescreen screen;
    public FlowPane flowPane;
    public Label score;

    public void picturesFlowPane() {
        flowPane.getChildren().clear();
        ImageView[][] images = screen.getImages();
        for (int y = 0; y < logic.getY(); y++) {
            for (int x = 0; x < logic.getX(); x++) {
                flowPane.getChildren().add(images[x][y]);
            }
        }
    }

    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W: {
                up();
                break;
            }
            case A: {
                left();
                break;
            }
            case S: {
                down();
                break;
            }
            case D: {
                right();
            }
        }
    }

    @FXML
    public void up() {
        logic.moveUp();
        screen.uiChange();
        score.setText(logic.getScore());
    }

    @FXML
    public void down() {
        logic.moveDown();
        screen.uiChange();
        score.setText(logic.getScore());
    }

    @FXML
    public void left() {
        logic.moveLeft();
        screen.uiChange();
        score.setText(logic.getScore());
    }

    @FXML
    public void right() {
        logic.moveRight();
        screen.uiChange();
        score.setText(logic.getScore());
    }
    public void createGameField(Logic logic) {
        this.logic = logic;
    }

    public void createGamescreen(Gamescreen screen) {
        this.screen = screen;
        picturesFlowPane();

    }
}