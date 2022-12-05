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
        partOne();
        partTwo();
    }

    public static void partTwo() {
        List<String> lines = readInput();
        int gamesTotal = lines.stream()
            .flatMapToInt(line -> IntStream.of(App.playRoundShape(line)))
            .sum();
        System.out.println("Part Two: " + gamesTotal);
    }

    public static void partOne() {
        List<String> lines = readInput();
        int gamesTotal = lines.stream()
            .flatMapToInt(line -> IntStream.of(App.playRound(line)))
            .sum();
        System.out.println("Part One: " + gamesTotal);
    }

    public static List<String> readInput() {
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

    public static int playRound(String line) {
        String[] split = line.split(" ");
        int playerOneScore = split[0].equals("A") ? 1 : split[0].equals("B") ? 2 : split[0].equals("C") ? 3 : 0;
        int playerTwoScore = split[1].equals("X") ? 1 : split[1].equals("Y") ? 2 : split[1].equals("Z") ? 3 : 0;
        int matchResult = 0;

        if (playerOneScore ==1 && playerTwoScore == 3) {
            matchResult = 0;
        } else if (playerTwoScore > playerOneScore) {
            matchResult = 6;
        } else if (playerTwoScore == 1 && playerOneScore == 3) {
            matchResult = 6;
        } else if (playerOneScore == playerTwoScore) {
            matchResult = 3;
        }

        return playerTwoScore + matchResult;
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
}
