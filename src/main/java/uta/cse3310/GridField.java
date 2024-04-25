package uta.cse3310;

import java.util.ArrayList;
import java.util.Random;

public class GridField {
    private char[][] grid;
    private ArrayList<String> wordList;
    private int remainingWords;

    public GridField(ArrayList<String> wordList) {
        this.wordList = wordList;
        this.remainingWords = wordList.size();
        generateGrid(5); // Initialize grid with default size (e.g., 5x5)
    }
    public GridField() {
        this.wordList = WordList.getWordList("Data/words");
        this.wordList = WordList.updatedWordList(wordList);
        this.remainingWords = wordList.size();
         // Initialize grid with default size (e.g., 5x5)
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
}
