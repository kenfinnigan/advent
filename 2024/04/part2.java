///usr/bin/env jbang "$0" "$@" ; exit $?


import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class part2 {

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

        int xCount = 0;
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;

        for (int i = 1; i < maxRow - 1; i++) {
            for (int j = 1; j < maxCol - 1; j++) {
                // Check if the current cell forms the center of an "X" with 'MAS'
                if (matrix[i][j] == 'A' &&
                    matrix[i - 1][j - 1] == 'M' && // Top-left
                    matrix[i - 1][j + 1] == 'S' && // Top-right
                    matrix[i + 1][j - 1] == 'M' && // Bottom-left
                    matrix[i + 1][j + 1] == 'S') { // Bottom-right
                        xCount++;
                } else if (matrix[i][j] == 'A' &&
                    matrix[i - 1][j - 1] == 'S' && // Top-left
                    matrix[i - 1][j + 1] == 'S' && // Top-right
                    matrix[i + 1][j - 1] == 'M' && // Bottom-left
                    matrix[i + 1][j + 1] == 'M') { // Bottom-right
                        xCount++;
                } else if (matrix[i][j] == 'A' &&
                    matrix[i - 1][j - 1] == 'M' && // Top-left
                    matrix[i - 1][j + 1] == 'M' && // Top-right
                    matrix[i + 1][j - 1] == 'S' && // Bottom-left
                    matrix[i + 1][j + 1] == 'S') { // Bottom-right
                        xCount++;
                } else if (matrix[i][j] == 'A' &&
                    matrix[i - 1][j - 1] == 'S' && // Top-left
                    matrix[i - 1][j + 1] == 'M' && // Top-right
                    matrix[i + 1][j - 1] == 'S' && // Bottom-left
                    matrix[i + 1][j + 1] == 'M') { // Bottom-right
                        xCount++;
                }
            }
        }

        System.out.println("X count: " + xCount);
        System.out.println("Correct answer: 2048");
    }
}
