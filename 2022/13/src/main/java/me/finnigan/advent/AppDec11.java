package me.finnigan.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class AppDec11 {
    private AppDec11() {
    }
    
    public static void main(String[] args) {
        List<String> inputLines = readInput();
        partOne(String.join("\n", inputLines));
        partTwo(String.join("\n", inputLines));
    }

    public static long partTwo(String input) {
        var monkeys = parseInput(input);

        var monkeyInspections = runRounds(monkeys, 10000, false);

        var monkeyBusiness = calculateMonkeyBusiness(monkeyInspections);

        System.out.println("Part Two: " + monkeyBusiness);
        return monkeyBusiness;
    }

    public static long partOne(String input) {
        var monkeys = parseInput(input);

        var monkeyInspections = runRounds(monkeys, 20, true);

        var monkeyBusiness = calculateMonkeyBusiness(monkeyInspections);
        System.out.println("Part One: " + monkeyBusiness);
        return monkeyBusiness;
    }

    record Monkey(List<Long> items, Function<Long, Long> operation, int modulus, int throwToTrue, int throwToFalse) {
    }

    private static Monkey[] parseInput(String input) {
        var monkeyInputs = input.trim().split("\n\n");
        var monkeys = new Monkey[monkeyInputs.length];

        for (var i = 0; i < monkeyInputs.length; i++) {
            var monkeyInputLines = monkeyInputs[i].split("\n");

            String[] stringItems = monkeyInputLines[1].substring(18).split(", ");
            List<Long> items = Arrays.stream(stringItems).map(Long::parseLong).collect(Collectors.toList());

            Function<Long, Long> operation = null;
            char operator = monkeyInputLines[2].charAt(23);

            if (operator == '*') {
                if (monkeyInputLines[2].charAt(25) == 'o') {
                    operation = (a) -> a * a;
                } else {
                    var val = Integer.parseInt(monkeyInputLines[2], 25, monkeyInputLines[2].length(), 10);
                    operation = (a) -> a * val;
                }
            } else if (operator == '+') {
                var val = Integer.parseInt(monkeyInputLines[2], 25, monkeyInputLines[2].length(), 10);
                operation = (a) -> a + val;
            }

            var modulus = Integer.parseInt(monkeyInputLines[3], 21, monkeyInputLines[3].length(), 10);

            int throwToTrue = Integer.parseInt(monkeyInputLines[4].substring(29, 30));
            int throwToFalse = Integer.parseInt(monkeyInputLines[5].substring(30, 31));

            monkeys[i] = new Monkey(items, operation, modulus, throwToTrue, throwToFalse);
        }

        return monkeys;
    }

    private static Long[] runRounds(Monkey[] monkeys, int rounds, boolean hasWorry) {
        Long[] monkeyInspections = new Long[monkeys.length];
        Arrays.fill(monkeyInspections, Long.valueOf(0));

        // Calculate the product of all the moduli for worry level
        var prod = 1;
        for (var monkey: monkeys) {
            prod *= monkey.modulus;
        }

        for (int round = 0; round < rounds; round++) {
            int monkeyNum = 0;
            for (var monkey : monkeys) {
                while(!monkey.items.isEmpty()) {
                    monkeyInspections[monkeyNum]++;
                
                    var newItem = monkey.operation.apply(monkey.items.remove(0));
    
                    if (hasWorry) {
                        newItem /= 3;
                    } else {
                        newItem %= prod;
                    }
    
                    if (newItem % monkey.modulus == 0) {
                        monkeys[monkey.throwToTrue].items.add(newItem);
                    } else {
                        monkeys[monkey.throwToFalse].items.add(newItem);
                    }                    
                }

                monkeyNum++;
            }
        }

        return monkeyInspections;
    }

    private static long calculateMonkeyBusiness(Long[] monkeyInspections) {
        Arrays.sort(monkeyInspections, Collections.reverseOrder());

        return monkeyInspections[0] * monkeyInspections[1];
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
