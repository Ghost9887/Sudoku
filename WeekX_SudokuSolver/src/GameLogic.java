import javax.swing.*;
import java.util.*;

public class GameLogic {
    GUI gui = new GUI();
    public static int[][] grid;

    public void createGame() {
        // Initialize an empty grid
        grid = new int[9][9];
        fillGrid(grid);

        // Convert the generated grid to the GUI format
        Random rand = new Random();

        for (int i = 0; i < gui.numbers.length; i++) {
            for (int j = 0; j < gui.numbers[i].length; j++) {
                int randNumber = rand.nextInt(0, 2);
                if(randNumber == 1) {
                    gui.numbers[i][j].setText(String.valueOf(grid[i][j]));
                }else{
                    gui.numbers[i][j].setText("");
                    gui.numbers[i][j].setEditable(true);
                }
            }
        }
    }

    private boolean fillGrid(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) { // Find an empty cell
                    List<Integer> numbers = getShuffledNumbers();
                    for (int num : numbers) {
                        if (isValid(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (fillGrid(grid)) {
                                return true;
                            }
                            grid[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number found, backtrack
                }
            }
        }
        return true; // Grid is fully filled
    }

    private List<Integer> getShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, new Random());
        return numbers;
    }

    private boolean isValid(int[][] grid, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }
        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        return true; // No conflicts, safe to place
    }

    public void fillAnswer() {
        // Fills in the grid with the correct answers!!
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                gui.numbers[i][j].setText(String.valueOf(grid[i][j]));
            }
        }
    }
    public boolean isValid(JTextField[][] grid){
        //ROW CHECK
        for(int i = 0; i < grid.length; i++){
            Set<String> rowSet = new HashSet<>();
            for(int j = 0; j < grid[i].length; j++){
                if(!rowSet.contains(grid[i][j].getText())){
                    rowSet.add(grid[i][j].getText());
                }else{
                    return false;
                }
            }
        }

        //COLUMN CHECK
        for(int i = 0; i < grid.length; i++){
            Set<String> columnSet = new HashSet<>();
            for(int j = 0; j < grid[i].length; j++){
                if(!columnSet.contains(grid[j][i].getText())){
                    columnSet.add(grid[j][i].getText());
                }else{
                    return false;
                }
            }
        }

        for(int k = 0; k < grid.length; k+=3){
            for(int l = 0; l < grid[k].length; l+=3){
                Set<String> boxSet = new HashSet<>();
                for(int i = k; i < k+3; i++){
                    for(int j = l; j < l+3; j++){
                        System.out.println(j);
                        if(!boxSet.contains(grid[i][j].getText())){
                            boxSet.add(grid[i][j].getText());
                        }else{
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }


}

