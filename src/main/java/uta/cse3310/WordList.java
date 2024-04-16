package uta.cse3310;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WordList //extends Direction 
{
    public ArrayList<String> Word;
    //private [] words;

    //method for accesing the word list from file
    List<String> getWordList(String wordsFile) 
    {
        List<String> wordList = new ArrayList<>();
        
        try {
            wordList = Files.readAllLines(Paths.get(wordsFile));
        } catch (IOException e) {
           System.out.println("not a valid file!");
        }
        
        return wordList;
    }

    //method to update word list
    public void updateWordList(GridField grid) 
    {
        
    }

    //method for word shuffling
    public void shuffleWords() 
    {
      //Collections.shuffle(list);
    }
}

