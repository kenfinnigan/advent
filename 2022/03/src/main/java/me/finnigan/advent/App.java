package me.finnigan.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        List<String> inputLines = readInput();
        partOne(inputLines);
        partTwo(inputLines);
    }

    public static int partTwo(List<String> inputLines) {
        int badgePriorityTotal = 0;

        for (int i = 2; i < inputLines.size(); i = i + 3) {
            String line1 = inputLines.get(i - 2);
            String line2 = inputLines.get(i - 1);
            String line3 = inputLines.get(i);

            char[] line1Chars = line1.toCharArray();
    
            List<Character> duplicateChars = new ArrayList<>();
            char duplicateChar = ' ';
    
            for (char line1Char : line1Chars) {
                if (line2.contains(String.valueOf(line1Char))) {
                    duplicateChars.add(line1Char);
                }
            }
    
            for (Character dupChar : duplicateChars) {
                if (line3.contains(String.valueOf(dupChar))) {
                    duplicateChar = dupChar;
                    break;
                }
            }

            badgePriorityTotal += calculatePriority(duplicateChar);
        }
        System.out.println("Part Two: " + badgePriorityTotal);
        return badgePriorityTotal;
    }

    public static int partOne(List<String> inputLines) {
        int priorityTotal = inputLines.stream()
            .flatMapToInt(line -> IntStream.of(App.findAndCalculatePriority(line)))
            .sum();
        System.out.println("Part One: " + priorityTotal);
        return priorityTotal;
    }

    public static int findAndCalculatePriority(String line) {
        int middle = line.length() / 2;
        char[] leftChars = line.substring(0, middle).toCharArray();
        String rightChars = line.substring(middle);

        char duplicateChar = ' ';

        for (char leftChar : leftChars) {
            if (rightChars.contains(String.valueOf(leftChar))) {
                duplicateChar = leftChar;
                break;
            }
        }

        return calculatePriority(duplicateChar);
    }

    public static int calculatePriority(char character) {
        int UpperOffset = 38;
        int LowerOffset = 96;

        if (character >= 'A' && character <= 'Z') {
            return character - UpperOffset;
        } else if (character >= 'a' && character <= 'z') {
            return character - LowerOffset;
        } else {
            return 0;
        }
    }

    public static int playRoundShape(String line) {
        String[] split = line.split(" ");
        int playerOneScore = split[0].equals("A") ? 1 : split[0].equals("B") ? 2 : split[0].equals("C") ? 3 : 0;
        int matchResult = split[1].equals("X") ? 0 : split[1].equals("Y") ? 3 : split[1].equals("Z") ? 6 : 0;

        int playerTwoScore = 0;
        
        if (matchResult == 0) {
            if (playerOneScore == 1) {
                playerTwoScore = 3;
            } else {
                playerTwoScore = playerOneScore - 1;
            }
        } else if (matchResult == 3) {
            playerTwoScore = playerOneScore;
        } else if (matchResult == 6) {
            if (playerOneScore == 3) {
                playerTwoScore = 1;
            } else {
                playerTwoScore = playerOneScore + 1;
            }
        }

        return playerTwoScore + matchResult;
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
