///usr/bin/env jbang "$0" "$@" ; exit $?


import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class part1 {

    public static void main(String... args) {
        // Input file path
        String filePath = "input.txt";

        List<String> lines = new ArrayList<>();

        // Read the file
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading or processing the file: " + e.getMessage());
            return;
        }

        char[][] matrix = new char[lines.size()][];

        int rowIndex = 0;

        // Populate matrix
        for (String row: lines) {
            matrix[rowIndex] = row.toCharArray();
            rowIndex++;
        }

        // FOR TESTING
        // char[][] matrix = {
        //     {'M', 'M', 'M', 'S', 'X', 'X', 'M', 'A', 'S', 'M'},
        //     {'M', 'S', 'A', 'M', 'X', 'M', 'S', 'M', 'S', 'A'},
        //     {'A', 'M', 'X', 'S', 'X', 'M', 'A', 'A', 'M', 'M'},
        //     {'M', 'S', 'A', 'M', 'A', 'S', 'M', 'S', 'M', 'X'},
        //     {'X', 'M', 'A', 'S', 'A', 'M', 'X', 'A', 'M', 'M'},
        //     {'X', 'X', 'A', 'M', 'M', 'X', 'X', 'A', 'M', 'A'},
        //     {'S', 'M', 'S', 'M', 'S', 'A', 'S', 'X', 'S', 'S'},
        //     {'S', 'A', 'X', 'A', 'M', 'A', 'S', 'A', 'A', 'A'},
        //     {'M', 'A', 'M', 'M', 'M', 'X', 'M', 'M', 'M', 'M'},
        //     {'M', 'X', 'M', 'X', 'A', 'X', 'M', 'A', 'S', 'X'}
        // };

        int wordCount = 0;
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;
        char[] wordChars = "XMAS".toCharArray();

        // Find XMAS in any direction
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (matrix[i][j] == wordChars[0]) {
                    // Check for word
                    wordCount += countWordFromCell(matrix, wordChars, i, j);
                }
            }
        }

        System.out.println("Word count: " + wordCount);
        System.out.println("Correct answer: 2685");
    }

    // Directions for 8 possible movements: horizontal, vertical, and diagonals
    private static final int[][] DIRECTIONS = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1},   // Up, Down, Left, Right
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  // Diagonal: Top-Left, Top-Right, Bottom-Left, Bottom-Right
    };

    private static int countWordFromCell(char[][] matrix, char[] word, int row, int col) {
        int maxRows = matrix.length;
        int maxCols = matrix[0].length;
        int count = 0;

         // Check all 8 directions
         for (int[] direction : DIRECTIONS) {
            int newRow = row, newCol = col, k;

            // Check each character of the word
            for (k = 0; k < word.length; k++) {
                // Check bounds
                if (newRow < 0 || newRow >= maxRows || newCol < 0 || newCol >= maxCols) {
                    break;
                }

                // Check if the current character matches
                if (matrix[newRow][newCol] != word[k]) {
                    break;
                }

                // Move in the current direction
                newRow += direction[0];
                newCol += direction[1];
            }

            // If all characters matched, increment the count
            if (k == word.length) {
                count++;
            }
        }

        return count;
    }
}
