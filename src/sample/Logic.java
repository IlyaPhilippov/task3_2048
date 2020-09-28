package sample;

import java.awt.image.BandedSampleModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Logic {

    private int x;
    private int y;
    private int[][] field;
    private int win = 2048;
    private int score = 0;

    public Logic(int x, int y) {
        this.x = x;
        this.y = y;
        field = new int[x][y];
    }

    public List<Integer> find_empty(int[][] field){
        List<Integer> emptycells = new ArrayList<>();
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if (field[i][j] == 0){
                    int number = i * y + j + 1;
                    emptycells.add(number);
                }
            }
        }
        return emptycells;
    }
    private boolean fieldChanged(int[][] before){
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                if (field[j][i] != before[j][i]) return true;
            }
        }
        return false;
    }
    public void newGame() {
        add2Or4();
        add2Or4();
    }

    public void add2Or4 (){
        List<Integer> empty = find_empty(field);
        Random rand = new Random();
        int randomEmpty = empty.get(rand.nextInt(empty.size()));
        randomEmpty -= 1;
        int i = randomEmpty / y;
        int j = randomEmpty % y;
        if (rand.nextInt(101) < 20){
            field[i][j] = 4;
        }else field[i][j] = 2;
    }

    public void  moveUp() {
        int[][] before = createArray(field);
        int num = x;
        for (int j = 0; j < y; j++) {
            int[] temp = new int[x];
            for (int i = 0; i < x; i++) {
                temp[i] = field[i][j];
            }
            temp = removeZeroUporLeft(temp, num);
            for (int i = 0; i < x - 1 ; i++) {
                if (temp[i] == temp[i + 1]) {
                    temp[i] *= 2;
                    temp[i + 1] = 0;
                    score += temp[i];
                }
            }
            temp = removeZeroUporLeft(temp , num);
            for (int i = 0; i < x; i++) {
                field[i][j] = temp[i];
            }
        }
        if (fieldChanged(before)) add2Or4();
    }
    public void moveDown() {
        int[][] before = createArray(field);
        int num = x;
        for (int j = 0; j < y; j++) {
            int[] temp = new int[x];
            for (int i = 0; i < x; i++) {
                temp[i] = field[i][j];
            }
            temp = removeZeroDownorRight(temp, num);
            for (int i = 0; i < x - 1; i++){
                if ((temp[i] == temp[i + 1]) && (temp[i] != 0)){
                    temp[i] *= 2;
                    temp[i + 1] = 0;
                    score += temp[i];
                }
            }
            temp = removeZeroDownorRight(temp, num);
            for (int i = 0; i < x; i++){
                field[i][j] = temp[i];
            }
        }
        if (fieldChanged(before)) add2Or4();
    }
    public void moveLeft() {
        int[][] before = createArray(field);
        int num = y;
        for (int i = 0; i < x; i++) {
            int[] temp = new int[y];
            System.arraycopy(field[i], 0, temp, 0, y);
            temp = removeZeroUporLeft(temp ,num);
            for (int j = 0; j < y - 1 ; j++){
                if (temp[j] == temp[j + 1]){
                    temp[j] *= 2;
                    temp[j + 1] = 0;
                    score += temp[j];
                }
            }
            temp = removeZeroUporLeft(temp, num);
            if (y >= 0) System.arraycopy(temp, 0, field[i], 0, y);
        }
        if (fieldChanged(before)) add2Or4();
    }
    public void moveRight() {
        int[][] before = createArray(field);
        int num = y;
        for (int i = 0; i < x; i++) {
            int[] temp = new int[y];
            System.arraycopy(field[i], 0, temp, 0, y);
            temp = removeZeroDownorRight(temp, num);
            for (int j = 0; j < y - 1 ; j++){
                if (temp[j] == temp[j + 1]){
                    temp[j] *= 2;
                    temp[j + 1] = 0;
                    score += temp[j];
                }
            }
            temp = removeZeroDownorRight(temp, num);
            if (y >= 0) System.arraycopy(temp, 0, field[i], 0, y);
        }
        if (fieldChanged(before)) add2Or4();
    }
    public int[] removeZeroUporLeft(int[] array, int num){
        int[] temp = new int[num];
        int counter = 0;
        for (int i = 0; i < num; i++){
            if (array[i] != 0){
                temp[counter] = array[i];
                counter++;
            }
        }
        for (int j = counter; j < num; j++){
            temp[j] = 0;
        }
        return temp;
    }
    public int[] removeZeroDownorRight(int[] array, int num){
        int[] temp = new int[num];
        int counter = num - 1;
        for (int i = 0; i < num; i++){
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
    private int[][] createArray(int[][] before) {
        int[][] answer = new int[x][y];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
               answer[i][j] = before[i][j];
            }
        }
        return answer;
    }
    public boolean checkWin() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (field[i][j] == win) return true;
            }
        }
        return false;
    }
    public int cell(int x, int y){
        return field[y][x];
    }

    public int getX (){
        return x;
    }

    public int getY (){
        return y;
    }

    public boolean possibleMoves() {
        List<Integer> findEmpty = find_empty(field);
        if (findEmpty.size() != 0) return true;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x - 1; j++) if (cell(j, i) == cell(j + 1, i) || cell(j, i) == 0) return true;
        }
        for (int j = 0; j < x; j++) {
            for (int i = 0; i < y - 1; i++) if (cell(j, i) == cell(j, i + 1)) return true;
        }
        return false;
    }
    public String getScore(){
        return Integer.toString(score);
    }
}