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
        generateGrid(5); // Initialize grid with default size (e.g., 5x5)  <-no need -muktar
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
        //this.addWord("group",0,0,Direction.Directions.HORIZONTAL); was testing selection function
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
        int dr = 0, dc = 0;
        switch (direction) {
            case HORIZONTAL:
                dc = 1;
                break;
            case VERTICAL:
                dr = 1;
                break;
            case DIAGONAL:
                dr = 1;
                dc = 1;
                break;
        }
        for (int i = 0; i < len; i++) {
            grid[row][column] = word.charAt(i);
            row += dr;
            column += dc;
        }
        wordList.add(word);
        //remainingWords starts off as the size of the updated wordlist
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

    // Method to randomly place words from the word list on the grid
    public void placeRandomWords() {
        Random random = new Random();
        for (String word : wordList) {
            boolean wordPlaced = false;
            while (!wordPlaced) {
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
        int dr = 0, dc = 0;
        switch (direction) {
            case HORIZONTAL:
                dc = 1;
                break;
            case VERTICAL:
                dr = 1;
                break;
            case DIAGONAL:
                dr = 1;
                dc = 1;
                break;
        }
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
