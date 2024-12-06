///usr/bin/env jbang "$0" "$@" ; exit $?


import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part1 {

    public static void main(String... args) {
        // Input file path
        String filePath = "input.txt";

        StringBuilder buffer = new StringBuilder();

        // Read the file
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading or processing the file: " + e.getMessage());
            return;
        }

        Pattern pattern = Pattern.compile("(mul\\((\\d+),(\\d+)\\))");
        Matcher matcher = pattern.matcher(buffer.toString());

        int result = matcher
            .results()
            .mapToInt(operation -> {
                if (operation.group(1) != null) {
                    return Integer.parseInt(operation.group(2)) * Integer.parseInt(operation.group(3));
                }
                return 0;
            })
            .sum();
        
        System.out.println("Result: " + result);

    }
}
