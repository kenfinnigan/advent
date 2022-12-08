package me.finnigan.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.stream.IntStream;

public final class AppDec5 {
    private AppDec5() {
    }

    public static void main(String[] args) {
        List<String> inputLines = readInput();
        partOne(inputLines);
        partTwo(inputLines);
    }

    public static String partTwo(List<String> inputLines) {
        String topCrates = "";
        List<Stack<String>> crates = new ArrayList<>();
        List<String> stackLines = new ArrayList<>();
        boolean gatheringStack = true;
        int numberOfStacks = 0;
        boolean firstCrates = true;

        for (String line : inputLines) {
            if (gatheringStack) {
                stackLines.add(line);

                if (line.isEmpty()) {
                    gatheringStack = false;

                    for (int i = stackLines.size() - 2; i >= 0; i--) {
                        String stackLine = stackLines.get(i);

                        if (i == stackLines.size() - 3) {
                            firstCrates = false;
                        }

                        if (!stackLine.isEmpty()) {
                            if (firstCrates) {
                                numberOfStacks = (stackLine.length() + 1) / 4;
                            }

                            for (int j = 0; j < numberOfStacks; j++) {
                                Stack<String> stack = firstCrates ? new Stack<>() : crates.get(j);

                                String crate = stackLine.substring(j * 4 + 1, j * 4 + 2);
                                if (crate.isBlank()) {
                                    continue;
                                }
                                stack.push(crate);

                                if (firstCrates) {
                                    crates.add(stack);
                                }
                            }
                        }
                    }
                }
            } else {
                String[] move = line.split(" ");

                int from = Integer.parseInt(move[3]) - 1;
                int to = Integer.parseInt(move[5]) - 1;

                Stack<String> fromStack = crates.get(from);
                Stack<String> toStack = crates.get(to);
                int moves = Integer.parseInt(move[1]);

                for (int i = moves; i > 0; i--) {
                    toStack.push(fromStack.remove(fromStack.size() - i));
                }
            }
        }

        for (Stack<String> crate : crates) {
            topCrates += crate.pop();
        }

        System.out.println("Part Two: " + topCrates);
        return topCrates;
    }

    public static String partOne(List<String> inputLines) {
        String topCrates = "";
        List<Stack<String>> crates = new ArrayList<>();
        List<String> stackLines = new ArrayList<>();
        boolean gatheringStack = true;
        int numberOfStacks = 0;
        boolean firstCrates = true;

        for (String line : inputLines) {
            if (gatheringStack) {
                stackLines.add(line);

                if (line.isEmpty()) {
                    gatheringStack = false;

                    for (int i = stackLines.size() - 2; i >= 0; i--) {
                        String stackLine = stackLines.get(i);

                        if (i == stackLines.size() - 3) {
                            firstCrates = false;
                        }

                        if (!stackLine.isEmpty()) {
                            if (firstCrates) {
                                numberOfStacks = (stackLine.length() + 1) / 4;
                            }

                            for (int j = 0; j < numberOfStacks; j++) {
                                Stack<String> stack = firstCrates ? new Stack<>() : crates.get(j);

                                String crate = stackLine.substring(j * 4 + 1, j * 4 + 2);
                                if (crate.isBlank()) {
                                    continue;
                                }
                                stack.push(crate);

                                if (firstCrates) {
                                    crates.add(stack);
                                }
                            }
                        }
                    }
                }
            } else {
                String[] move = line.split(" ");

                int from = Integer.parseInt(move[3]) - 1;
                int to = Integer.parseInt(move[5]) - 1;

                Stack<String> fromStack = crates.get(from);
                Stack<String> toStack = crates.get(to);
                int moves = Integer.parseInt(move[1]);

                for (int i = 0; i < moves; i++) {
                    toStack.push(fromStack.pop());
                }
            }
        }

        for (Stack<String> crate : crates) {
            topCrates += crate.pop();
        }

        System.out.println("Part One: " + topCrates);
        return topCrates;
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
