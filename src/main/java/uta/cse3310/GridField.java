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
        this.remainingWords = wordList.size();
        generateGrid(5); // Initialize grid with default size (e.g., 5x5)
    }

    public GridField() {
        this(WordList.getWordList("Data/words")); // Using WordList class to fetch word list
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

    public void placeRandomWords() {
        Random random = new Random();
        ArrayList<String> remainingWords = new ArrayList<>(wordList); // Create a copy of the wordList to track remaining words
        for (int i = 0; i < wordList.size(); i++) {
            String word = remainingWords.get(random.nextInt(remainingWords.size())); // Select a random word from the remaining words
            boolean wordPlaced = false;
            int attempt = 0;
            while (!wordPlaced && attempt < 100) { // Limit the number of attempts to prevent infinite loops
                int len = word.length();
                int row = random.nextInt(grid.length);
                int col = random.nextInt(grid[0].length);
                Direction.Directions direction = Direction.Directions.values()[random.nextInt(Direction.Directions.values().length)];
                if (canPlaceWord(word, row, col, direction)) {
                    addWord(word, row, col, direction);
                    wordPlaced = true;
                }
                attempt++;
            }
            if (wordPlaced) {
                remainingWords.remove(word); // Remove the word from remainingWords if successfully placed
            } else {
                System.out.println("Failed to place word: " + word);
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

    public void displayGrid() {
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
}
