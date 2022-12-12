package me.finnigan.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class AppDec6 {
    private AppDec6() {
    }

    public static void main(String[] args) {
        List<String> inputLines = readInput();
        partOne(inputLines.get(0));
        partTwo(inputLines.get(0));
    }

    public static int partTwo(String input) {
        int markerPosition = findUniqueString(input, 14);

        System.out.println("Part Two: " + markerPosition);
        return markerPosition;
    }

    public static int partOne(String input) {
        int markerPosition = findUniqueString(input, 4);

        System.out.println("Part One: " + markerPosition);
        return markerPosition;
    }

    public static int findUniqueString(String input, int size) {
        int markerPosition = 0;
        Set<String> uniqueChars = new HashSet<>();

        for (int i = size - 1; i < input.length(); i++) {
            uniqueChars.clear();
            for (int j = i - (size - 1); j <= i; j++) {
                String letter = String.valueOf(input.charAt(j));
                uniqueChars.add(letter);
            }

            if (uniqueChars.size() == size) {
                markerPosition = i + 1;
                break;
            }
        }
        return markerPosition;
    }

    private static List<String> readInput() {
        BufferedReader reader;
        List<String> lines = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
