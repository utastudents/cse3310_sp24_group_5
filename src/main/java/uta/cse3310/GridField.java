package uta.cse3310;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GridField {
    private char[][] grid;
    private ArrayList<String> wordList = new ArrayList<>();
    private int remainingWords;

    public GridField(ArrayList<String> wordList) {
        this.wordList = wordList;
        //this.remainingWords = wordList.size();
        this.remainingWords =0;
        generateGrid(15); // Initialize grid with default size (e.g., 5x5)
    }

    public GridField() {
        this.wordList = WordList.getWordList("Data/words");
        this.wordList = WordList.updatedWordList(wordList);
        this.remainingWords = 0;
        //generateGrid(5); // Initialize grid with default size (e.g., 5x5)
    }

    public char[][] getGrid() {
        return grid;
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public void generateGrid(int gridSize) {
        grid = new char[gridSize][gridSize];
        Random random = new Random();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = '-'; // Randomly fill grid with 0s
            }
        }
        
        placeRandomWords();
        //fill remaining space with random letters
        for (int i = 0; i < gridSize; i++) 
        {
            for (int j = 0; j < gridSize; j++) 
            {
                if(grid[i][j]=='-')
                {
                    grid[i][j] = (char) ('A' + random.nextInt(26));
                }
                
            }
        }
    }

    public boolean checkWord(String word) {
        return wordList.contains(word);
    }

    public int getRemainingWords() {
        return remainingWords;
    }

    public void revealWord(String word) {
        if (wordList.remove(word)) {
            remainingWords--;
        }
    }

    public void addWord(String word, int row, int column, Direction.Directions direction) {
        int len = word.length();
        int dr = direction.getRowIncrement();
        int dc = direction.getColumnIncrement();
        for (int i = 0; i < len; i++) {
            grid[row][column] = word.charAt(i);
            row += dr;
            column += dc;
        }
        remainingWords++;//words that actually make it into the grid get counted
    }

    public void displayGrid() {
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public void placeRandomWords() {
        Random random = new Random();
        for (String word : wordList) {
            boolean wordPlaced = false;
            int attemptCount = 0; // Track the number of attempts to place the word
            while (!wordPlaced) {
                
                if (attemptCount > 100) {
                    // Add a safeguard to prevent infinite loops; abort if attempts exceed a certain threshold
                    System.out.println("Failed to place word: " + word);
                    break;
                }

                int len = word.length();
                int row = random.nextInt(grid.length);
                int col = random.nextInt(grid[0].length);
                Direction.Directions direction = Direction.Directions.values()[random.nextInt(Direction.Directions.values().length)];
                if (canPlaceWord(word, row, col, direction)) {
                    addWord(word, row, col, direction);
                    wordPlaced = true;
                }
                attemptCount++;
            }
            
        }
        this.displayGrid();
    }

    private boolean canPlaceWord(String word, int row, int column, Direction.Directions direction) {
        int len = word.length();
        int dr = direction.getRowIncrement();
        int dc = direction.getColumnIncrement();
        for (int i = 0; i < len; i++) {
            if (row < 0 || row >= grid.length || column < 0 || column >= grid[0].length ||( grid[row][column] != '-' && grid[row][column]!=word.charAt(i))) {
                if(row < 0 || row >= grid.length || column < 0 || column >= grid[0].length)
                {
                    return false;
                }
                System.out.println("Current cell value: "+grid[row][column]+"\nletter to add :"+word.charAt(i));
                return false; // Check if the word goes out of bounds or overlaps with existing letters that don't match (allows for intersecting words)
            }
            row += dr;
            column += dc;
        }
        return true;
    }

    

}
