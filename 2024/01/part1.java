///usr/bin/env jbang "$0" "$@" ; exit $?


import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class part1 {

    public static void main(String... args) {
        // Input file path
        String filePath = "input01.txt";

        // Lists to store the numbers
        List<Integer> leftNumbers = new ArrayList<>();
        List<Integer> rightNumbers = new ArrayList<>();

        // Read the file
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by whitespace
                String[] numbers = line.trim().split("\\s+");
                if (numbers.length == 2) {
                    leftNumbers.add(Integer.parseInt(numbers[0]));
                    rightNumbers.add(Integer.parseInt(numbers[1]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading or processing the file: " + e.getMessage());
            return;
        }

        // Sort the lists
        Collections.sort(leftNumbers);
        Collections.sort(rightNumbers);

        // Process an item from each list together, calculating the difference between the number in each list and adding it to a counter
        int counter = 0;
        for (int i = 0; i < leftNumbers.size(); i++) {
            counter += Math.abs(leftNumbers.get(i) - rightNumbers.get(i));
        }

        System.out.println("Total difference: " + counter);
        System.out.println("Correct answer: 2367773");
    }
}
