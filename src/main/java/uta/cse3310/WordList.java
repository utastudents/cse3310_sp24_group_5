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
    public static List<String> list;
    //private [] words;

    //method for accesing the word list from file
    public static ArrayList<String> getWordList(String wordsFile) 
    {
        ArrayList<String> wordList = new ArrayList<>();
        
        try {
            list = Files.readAllLines(Paths.get(wordsFile));
            shuffleWords();
            wordList.addAll(list);
        } catch (IOException e) {
           System.out.println("not a valid file!");
        }
        
        return wordList;
    }

    //method for word shuffling
    public static void shuffleWords() 
    {
      Collections.shuffle(list);
    }

    public static ArrayList<String> updatedWordList(ArrayList<String> wordBank)
    {
        int totalCharacters = 0;
        int index = 0;
        ArrayList<String> updatedList = new ArrayList<>();

        while (totalCharacters < 2000 && index < wordBank.size())
        {
            String word = wordBank.get(index);
            if (totalCharacters + word.length() <= 2000)
            {
                updatedList.add(word);
                totalCharacters += word.length();
            } else
            {
                break;
            }
            index++;
        }
        
        return updatedList;
    }

}

