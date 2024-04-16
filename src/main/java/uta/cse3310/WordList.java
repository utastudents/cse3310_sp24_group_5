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
    public ArrayList<String> list;
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

    //method for word shuffling
    public void shuffleWords() 
    {
      Collections.shuffle(list);
    }

    public List<String> updatedWordList(List<String> wordBank) {
        if (wordBank.size() < 500) {
            System.err.println("Word bank does not contain at least 500 words.");
            return Collections.emptyList();
        }
        
        List<String> updatedList = new ArrayList<>(wordBank.subList(0, 500)); 
        
        return updatedList;
    }

}

