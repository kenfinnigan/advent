///usr/bin/env jbang "$0" "$@" ; exit $?


import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class part1 {

    public static void main(String... args) {
        // Input file path
        String filePath = "input.txt";

        int safeReportCount = 0;
        boolean increasing = false;
        boolean decreasing = false;
        int levelDiff = 1;

        // Read the file
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by whitespace
                String[] numbers = line.trim().split("\\s+");
                int i = 0;
                while (i < numbers.length - 1 && (levelDiff >= 1 && levelDiff <= 3)) {
                    int first = Integer.parseInt(numbers[i]);
                    int second = Integer.parseInt(numbers[i+1]);

                    levelDiff = Math.abs(first - second);
                    if (first > second) {
                        decreasing = true;
                    } else if (second > first) {
                        increasing = true;
                    }
                    i++;
                }

                if (i == numbers.length - 1 && (increasing != decreasing) && (levelDiff >= 1 && levelDiff <= 3)) {
                    safeReportCount++;
                }
                increasing = false;
                decreasing = false;
                levelDiff = 1;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading or processing the file: " + e.getMessage());
            return;
        }

        System.out.println("Number of safe reports: " + safeReportCount);
        System.out.println("Correct answer: 379");
    }
}