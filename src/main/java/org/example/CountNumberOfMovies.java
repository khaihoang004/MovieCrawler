package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CountNumberOfMovies {
    public static void main(String[] args) {
        String filePath = "movies.json";

        try {
            File file = new File(filePath);
            int lineCount = getMovieCount(file) - 1;
            System.out.println("Number of movies in the file: " + lineCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static int getMovieCount(File file) {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineCount - 1;
    }

}
