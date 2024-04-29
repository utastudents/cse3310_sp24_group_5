package uta.cse3310;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WordList {
    public static List<String> list;

    public static ArrayList<String> getWordList(String wordsFile) {
        ArrayList<String> wordList = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(wordsFile));
            if (lines.isEmpty()) {
                System.out.println("The file is empty: " + wordsFile);
                return wordList; // Return empty list if the file is empty
            }
            
            list = new ArrayList<>(lines);
            shuffleWords();
            wordList.addAll(list);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + wordsFile);
            e.printStackTrace(); // Print the stack trace for debugging
        }

        return wordList;
    }

    public static void shuffleWords() {
        Collections.shuffle(list);
    }

    public static ArrayList<String> updatedWordList(ArrayList<String> wordBank) {
        int totalCharacters = 0;
        int index = 0;
        ArrayList<String> updatedList = new ArrayList<>();

        while (totalCharacters < 500 && index < wordBank.size()) {
            String word = wordBank.get(index);
            if (totalCharacters + word.length() <= 500) {
                updatedList.add(word);
                totalCharacters += word.length();
            } else {
                break;
            }
            index++;
        }

        return updatedList;
    }
}