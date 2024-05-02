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
    // Static list to store words read from file
    public static List<String> list;

    // Method to read words from a file and return shuffled word list
    public static ArrayList<String> getWordList(String wordsFile) {
        ArrayList<String> wordList = new ArrayList<>();

        try {
            // Read all lines from the specified file
            List<String> lines = Files.readAllLines(Paths.get(wordsFile));
            
            // If file is empty, print message and return empty list
            if (lines.isEmpty()) {
                System.out.println("The file is empty: " + wordsFile);
                return wordList;
            }
            
            // Store lines in a static list and shuffle the words
            list = new ArrayList<>(lines);
            shuffleWords();
            
            // Add shuffled words to word list
            wordList.addAll(list);
        } catch (IOException e) {
            // Print error message and stack trace if an exception occurs
            System.out.println("Error reading the file: " + wordsFile);
            e.printStackTrace();
        }

        return wordList;
    }

    // Method to shuffle words in the static list
    public static void shuffleWords() {
        // Shuffling the list to randomize word order
        Collections.shuffle(list);
    }

    // Method to update word list based on character limit
    public static ArrayList<String> updatedWordList(ArrayList<String> wordBank) {
        int totalCharacters = 0;
        int index = 0;
        ArrayList<String> updatedList = new ArrayList<>();

        // Iterate through word bank and add words until character limit is reached
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

    // Method to remove duplicate words from the list
    public static void removeDuplicates() {
        // Create a temporary list to store unique words
        List<String> uniqueWords = new ArrayList<>();

        // Iterate through the list and add unique words to temporary list
        for (String word : list) {
            if (!uniqueWords.contains(word)) {
                uniqueWords.add(word);
            }
        }

        // Replace the original list with the list of unique words
        list = uniqueWords;
    }
}