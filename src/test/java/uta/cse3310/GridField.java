package uta.cse3310;

import java.util.ArrayList;
import java.util.Random;

public class GridField {
    private char[][] grid;
    private ArrayList<String> wordList = new ArrayList<>();
    private int remainingWords;
   
    public GridField(ArrayList<String> wordList) {
        this.wordList = wordList;
        this.remainingWords = wordList.size();
        generateGrid(5); // Initialize grid with default size (e.g., 5x5)
    }

    public GridField() {
        this.wordList = WordList.getWordList("Data/words");
        this.wordList = WordList.updatedWordList(wordList);
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
                grid[i][j] = (char) ('A' + random.nextInt(26)); // Randomly fill grid with alphabets
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
        wordList.add(word);
        remainingWords++;
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
                attemptCount++;
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
            }
        }
    }

    private boolean canPlaceWord(String word, int row, int column, Direction.Directions direction) {
        int len = word.length();
        int dr = direction.getRowIncrement();
        int dc = direction.getColumnIncrement();
        for (int i = 0; i < len; i++) {
            if (row < 0 || row >= grid.length || column < 0 || column >= grid[0].length || grid[row][column] != 0) {
                return false; // Check if the word goes out of bounds or overlaps with existing letters
            }
            row += dr;
            column += dc;
        }
        return true;
    }
}