package me.finnigan.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    public static void partTwo() {
        List<String> lines = readInput();
        List<Integer> calorieCounts = getElfCalorieCounts(lines);

        Collections.sort(calorieCounts);
        int topThreeCalories = calorieCounts.get(calorieCounts.size() - 1) + calorieCounts.get(calorieCounts.size() - 2) + calorieCounts.get(calorieCounts.size() - 3);
        System.out.println("Top three calorie count: " + topThreeCalories);
    }

    public static void partOne() {
        List<String> lines = readInput();
        List<Integer> calorieCounts = getElfCalorieCounts(lines);
        int maxValue = getMaxElfCalories(calorieCounts);
        System.out.println("Part One: " + maxValue);
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

    public static int getMaxElfCalories(List<Integer> calorieCounts) {
        return calorieCounts.stream().max((i, j) -> i.compareTo(j)).orElseThrow(NoSuchElementException::new);
    }

    public static List<Integer> getElfCalorieCounts(List<String> lines) {
        List<Integer> elfCalorieCounts = new ArrayList<>();
        int total = 0;

        for (String line : lines) {
            if (line.isEmpty()) {
                elfCalorieCounts.add(total);
                total = 0;
                continue;
            }
            total += Integer.parseInt(line);
        }
        elfCalorieCounts.add(total);

        return elfCalorieCounts;
    }
}
