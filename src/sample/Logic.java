package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Logic {

    private int x;
    private int y;
    private int[][] field;
    private int win = 2048;

    public Logic(int x, int y) {
        this.x = x;
        this.y = y;
        field = new int[x][y];
    }

    public void printfield(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    public List<Integer> find_empty(int[][] field){
        List<Integer> emptycells = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (field[i][j] == 0){
                    int number = i * 4 + j + 1;
                    emptycells.add(number);
                }
            }
        }
        return emptycells;
    }
    public void add2Or4 (int[][] field){
        List<Integer> empty = find_empty(field);
        Random rand = new Random();
        int randomEmpty = empty.get(rand.nextInt(empty.size()));
        randomEmpty -= 1;
        int x = randomEmpty / 4;
        int y = randomEmpty % 4;
        if (rand.nextInt(101) < 20){
            field[x][y] = 4;
        }else field[x][y] = 2;
    }

    public int[][] moveUp() {
        int[][] newfield = new int[4][4];
        for (int y = 0; y < 4; y++) {
            int[] temp = new int[4];
            for (int x = 0; x < 4; x++) {
                temp[x] = field[x][y];
            }
            temp = removeZeroUporLeft(temp);
            for (int i = 0; i < 4 - 1 ; i++){
                if (temp[i] == temp[i + 1]){
                    temp[i] *= 2;
                    temp[i+ 1] = 0;
                }
            }
            temp = removeZeroUporLeft(temp);
            for (int x = 0; x < 4; x++){
                newfield[x][y] = temp[x];
            }
        }
        return newfield;
    }
    public int[][] moveDown() {
        int[][] newfield = new int[4][4];
        for (int y = 0; y < 4; y++) {
            int[] temp = new int[4];
            for (int x = 0; x < 4; x++) {
                temp[x] = field[x][y];
            }
            temp = removeZeroDownorRight(temp);
            for (int i = 0; i < 4 - 1 ; i++){
                if (temp[i] == temp[i + 1]){
                    temp[i] *= 2;
                    temp[i+ 1] = 0;
                }
            }
            temp = removeZeroDownorRight(temp);
            for (int x = 0; x < 4; x++){
                newfield[x][y] = temp[x];
            }
        }
        return newfield;
    }
    public int[][] moveLeft() {
        int[][] newfield = new int[4][4];
        for (int x = 0; x < 4; x++) {
            int[] temp = new int[4];
            System.arraycopy(field[x], 0, temp, 0, 4);
            temp = removeZeroUporLeft(temp);
            for (int i = 0; i < 4 - 1 ; i++){
                if (temp[i] == temp[i + 1]){
                    temp[i] *= 2;
                    temp[i+ 1] = 0;
                }
            }
            temp = removeZeroUporLeft(temp);
            System.arraycopy(temp, 0, newfield[x], 0, 4);
        }
        return newfield;
    }
    public int[][] moveRight() {
        int[][] newfield = new int[4][4];
        for (int x = 0; x < 4; x++) {
            int[] temp = new int[4];
            System.arraycopy(field[x], 0, temp, 0, 4);
            temp = removeZeroDownorRight(temp);
            for (int i = 0; i < 4 - 1 ; i++){
                if (temp[i] == temp[i + 1]){
                    temp[i] *= 2;
                    temp[i+ 1] = 0;
                }
            }
            temp = removeZeroDownorRight(temp);
            System.arraycopy(temp, 0, newfield[x], 0, 4);
        }
        return newfield;
    }

    public int[] removeZeroUporLeft(int[] array){
        int[] temp = new int[4];
        int counter = 0;
        for (int i = 0; i < 4; i++){
            if (array[i] != 0){
                temp[counter] = array[i];
                counter++;
            }
        }
        for (int j = counter; j < 4; j++){
            temp[j] = 0;
        }
        return temp;
    }
    public int[] removeZeroDownorRight(int[] array){
        int[] temp = new int[4];
        int counter = 3;
        for (int i = 0; i < 4; i++){
            if (array[i] != 0){
                temp[counter] = array[i];
                counter--;
            }
        }
        for (int j = counter; j > -1; j--){
            temp[j] = 0;
        }
        return temp;
    }

    public boolean checkWin() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (field[x][y] == 2048) return true;
            }
        }
        return false;
    }
}
