package uta.cse3310;

import java.util.ArrayList;


public class GridField //extends WordList
{
    private ArrayList<String> word=new ArrayList<String>();
    private int wordLength;
    private int remainingWords;
    private int gridSize;
    private char[][] grid;

    public char[][] getGrid()
    {
        return grid;
    }
    public ArrayList<String> getWordList()
    {
        return word;
    }
    
    public void generateGrid() {
        // Logic for generating the grid

    }

    public boolean checkWord(String word) {
        // Logic for checking if the word is valid
        return true;
    }

    public int getRemainingWords() {
        // Logic for getting the remaining words
        return 0;
    }

    public void revealWord(String words) {
        // Logic for revealing a word
    }

    public void addWord(String word, Direction direction) {
        // Logic for adding a word to the grid
    }

    public void displayGrid() {
        // Logic for displaying the grid
    }
}
