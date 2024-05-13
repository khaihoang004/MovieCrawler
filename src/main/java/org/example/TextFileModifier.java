package org.example;

import java.io.*;

public class TextFileModifier {
    public static void main(String[] args) {
        // Paths to your text files
        String sourceFilePath = "movies_4.txt";
        String destinationFilePath = "movies_3.txt";

        try {
            // Create readers and writers
            BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath));

            // Read and process each line
            String line;
            while ((line = reader.readLine()) != null) {
                // Add \n after "},"
                if (line.contains("},")) {
                    line = line.replace("},", "},\n ");
                }

                // Write modified line to destination file
                writer.write(line);
                writer.newLine();
            }

            // Close readers and writers
            reader.close();
            writer.close();

            System.out.println("Text file modified successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
