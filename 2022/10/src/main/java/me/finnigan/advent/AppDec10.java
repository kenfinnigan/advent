package me.finnigan.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class AppDec10 {
    private AppDec10() {
    }
    
    public static void main(String[] args) {
        List<String> inputLines = readInput();
        partOne(inputLines);
        partTwo(inputLines);
    }

    public static void partTwo(List<String> inputLines) {
        var cycle = 0;
        var sprintCenter = 1;
        String screen = "";

        for (var line : inputLines) {
            cycle++;
            screen = writeScreen(screen, (cycle - 1) % 40, sprintCenter);

            String[] command = line.split(" ");
            if (command[0].equals("noop")) {
                // Do nothing
            } else {
                var change = Integer.parseInt(command[1]);
                cycle++;
                screen = writeScreen(screen, (cycle - 1) % 40, sprintCenter);
                sprintCenter += change;
            }
        }

        System.out.println("Part Two:");
        System.out.println(screen.substring(0, 39));
        System.out.println(screen.substring(40, 79));
        System.out.println(screen.substring(80, 119));
        System.out.println(screen.substring(120, 159));
        System.out.println(screen.substring(160, 199));
        System.out.println(screen.substring(200, 239));
    }

    private static String writeScreen(String screen, int pixel, int sprintCenter) {
        System.out.println("Pixel: " + pixel + " Sprint Center: " + sprintCenter);
        if (pixel == sprintCenter || pixel == sprintCenter + 1 || pixel == sprintCenter - 1) {
            return screen + "#";
        } else {
            return screen + ".";
        }
    }

    public static int partOne(List<String> inputLines) {
        var cycle = 0;
        var register = 1;
        var signalStrengthSum = 0;

        for (var line : inputLines) {
            cycle++;
            signalStrengthSum += getSignalStrength(cycle, register);

            String[] command = line.split(" ");
            if (command[0].equals("noop")) {
                // Do nothing
            } else {
                var change = Integer.parseInt(command[1]);

                cycle++;
                signalStrengthSum += getSignalStrength(cycle, register);

                register += change;
            }
        }

        System.out.println("Part One: " + signalStrengthSum);
        return signalStrengthSum;
    }

    private static int getSignalStrength(int cycle, int register) {
        if (cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220) {
            return cycle * register;
        }

        return 0;
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
