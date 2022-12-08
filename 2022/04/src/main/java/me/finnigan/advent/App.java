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
        int countOfOverlaps = 0;

        for (String line : inputLines) {
            String[] elfRanges = line.split(",");
            String[] elfRange1 = elfRanges[0].split("-");
            String[] elfRange2 = elfRanges[1].split("-");

            int elfRange1Start = Integer.parseInt(elfRange1[0]);
            int elfRange1End = Integer.parseInt(elfRange1[1]);
            int elfRange2Start = Integer.parseInt(elfRange2[0]);
            int elfRange2End = Integer.parseInt(elfRange2[1]);

            if (elfRange1Start <= elfRange2Start && elfRange2Start <= elfRange1End) {
                    countOfOverlaps++;
            } else if (elfRange1Start <= elfRange2End && elfRange2End <= elfRange1End) {
                    countOfOverlaps++;
            } else if (elfRange2Start <= elfRange1Start && elfRange1Start <= elfRange2End) {
                    countOfOverlaps++;
            } else if (elfRange2Start <= elfRange1End && elfRange1End <= elfRange2End) {
                    countOfOverlaps++;
            }
        }

        System.out.println("Part Two: " + countOfOverlaps);
        return countOfOverlaps;
    }

    public static int partOne(List<String> inputLines) {
        int countOfOverlaps = 0;

        for (String line : inputLines) {
            String[] elfRanges = line.split(",");
            String[] elfRange1 = elfRanges[0].split("-");
            String[] elfRange2 = elfRanges[1].split("-");

            if ((Integer.parseInt(elfRange1[0]) >= Integer.parseInt(elfRange2[0]))
                && (Integer.parseInt(elfRange1[1]) <= Integer.parseInt(elfRange2[1]))) {
                    countOfOverlaps++;
            } else if ((Integer.parseInt(elfRange1[0]) <= Integer.parseInt(elfRange2[0]))
                && (Integer.parseInt(elfRange1[1]) >= Integer.parseInt(elfRange2[1]))) {
                    countOfOverlaps++;
            }
        }

        System.out.println("Part One: " + countOfOverlaps);
        return countOfOverlaps;
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
