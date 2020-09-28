package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Gamescreen {

        private Logic logic;
        private ImageView[][] images;
        private Map<Integer, Image> pictures;
        private final int WIDTH = 400;
        private final int HEIGHT = 400;
        private FieldController control;

        public Gamescreen(Logic logic) throws FileNotFoundException {
            this.logic = logic;
            int x = logic.getX();
            int y = logic.getY();
            images = new ImageView[x][y];

            PicturesMap();
            for (int j = 0; j < y; j++) {
                for (int i = 0; i < x; i++) {
                    ImageView newImage = new ImageView();
                    newImage.setImage(pictures.get(logic.cell(i, j)));
                    newImage.setFitWidth(WIDTH / x);
                    newImage.setFitHeight(HEIGHT / y);
                    images[i][j] = newImage;
                }
            }
        }

        private void PicturesMap() throws FileNotFoundException {
            pictures = new HashMap<>();
            pictures.put(0, new Image(new FileInputStream("src/pictures/0.png")));
            pictures.put(2, new Image(new FileInputStream("src/pictures/2.png")));
            pictures.put(4, new Image(new FileInputStream("src/pictures/4.png")));
            pictures.put(8, new Image(new FileInputStream("src/pictures/8.png")));
            pictures.put(16, new Image(new FileInputStream("src/pictures/16.jpg")));
            pictures.put(32, new Image(new FileInputStream("src/pictures/32.jpg")));
            pictures.put(64, new Image(new FileInputStream("src/pictures/64.png")));
            pictures.put(128, new Image(new FileInputStream("src/pictures/128.png")));
            pictures.put(256, new Image(new FileInputStream("src/pictures/256.jpg")));
            pictures.put(512, new Image(new FileInputStream("src/pictures/512.png")));
            pictures.put(1024, new Image(new FileInputStream("src/pictures/1024.png")));
            pictures.put(2048, new Image(new FileInputStream("src/pictures/2048.png")));
        }

        public ImageView[][] getImages(){
            return images;
        }

        public void uiChange() {
            if (!logic.possibleMoves()) System.out.println("lose");
            if (logic.checkWin()) System.out.println("win");

            for (int y = 0; y < logic.getY(); y++) {
             for (int x = 0; x < logic.getX(); x++) {
                 int value = logic.cell(x, y);
                 images[x][y].setImage(pictures.get(value));
            }
        }
    }
}
