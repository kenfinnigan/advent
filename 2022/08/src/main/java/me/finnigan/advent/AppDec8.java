package me.finnigan.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class AppDec8 {
    private AppDec8() {
    }
    
    public static void main(String[] args) {
        List<String> inputLines = readInput();
        partOne(inputLines);
        partTwo(inputLines);
    }

    public static long partTwo(List<String> inputLines) {
        int height = inputLines.size();
        int width = inputLines.get(0).length();
        int bestTreeScore = 0;

        int[][] forest = new int[height][width];
        buildForest(inputLines, forest);

        bestTreeScore = findBestTreeScore(forest, height, width);

        System.out.println("Part Two: " + bestTreeScore);
        return bestTreeScore;
    }

    private static int findBestTreeScore(int[][] forest, int height, int width) {
        int bestTreeScore = 0;
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                int current = forest[i][j];
                int treeScoreNorth = 0;
                int treeScoreSouth = 0;
                int treeScoreEast = 0;
                int treeScoreWest = 0;

                // Check vertically higher for taller trees
                for (int k = i - 1; k >= 0; k--) {
                    treeScoreNorth++;
                    if (forest[k][j] >= current) {
                        break;
                    }
                }
                // Check vertically lower for taller trees
                for (int k = i + 1; k < height; k++) {
                    treeScoreSouth++;
                    if (forest[k][j] >= current) {
                        break;
                    }
                }
                // Check horizontally left for taller trees
                for (int k = j - 1; k >= 0; k--) {
                    treeScoreWest++;
                    if (forest[i][k] >= current) {
                        break;
                    }
                }
                // Check horizontally right for taller trees
                for (int k = j + 1; k < width; k++) {
                    treeScoreEast++;
                    if (forest[i][k] >= current) {
                        break;
                    }
                }

                int treeScore = treeScoreNorth * treeScoreSouth * treeScoreEast * treeScoreWest;
                if (treeScore > bestTreeScore) {
                    bestTreeScore = treeScore;
                }
            }
        }
        return bestTreeScore;
    }

    public static int partOne(List<String> inputLines) {
        int height = inputLines.size();
        int width = inputLines.get(0).length();
        int visibleTrees = height * width;

        int[][] forest = new int[height][width];
        buildForest(inputLines, forest);

        visibleTrees = findTrees(forest, height, width, visibleTrees);

        System.out.println("Part One: " + visibleTrees);
        return visibleTrees;
    }

    private static void buildForest(List<String> inputLines, int[][] forest) {
        for (int i = 0; i < inputLines.size(); i++) {
            String line = inputLines.get(i);
            for (int j = 0; j < line.length(); j++) {
                forest[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }
    }

    private static int findTrees(int[][] forest, int height, int width, int visibleTrees) {
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                int current = forest[i][j];
                boolean treeInvisibleNorth = false;
                boolean treeInvisibleSouth = false;
                boolean treeInvisibleEast = false;
                boolean treeInvisibleWest = false;

                // Check vertically higher for taller trees
                for (int k = i - 1; k >= 0; k--) {
                    if (forest[k][j] >= current) {
                        treeInvisibleNorth = true;
                        break;
                    }
                }
                // Check vertically lower for taller trees
                for (int k = i + 1; k < height; k++) {
                    if (forest[k][j] >= current) {
                        treeInvisibleSouth = true;
                        break;
                    }
                }
                // Check horizontally left for taller trees
                for (int k = j - 1; k >= 0; k--) {
                    if (forest[i][k] >= current) {
                        treeInvisibleWest = true;
                        break;
                    }
                }
                // Check horizontally right for taller trees
                for (int k = j + 1; k < width; k++) {
                    if (forest[i][k] >= current) {
                        treeInvisibleEast = true;
                        break;
                    }
                }

                if (treeInvisibleNorth && treeInvisibleSouth && treeInvisibleEast && treeInvisibleWest) {
                    visibleTrees--;
                }
            }
        }
        return visibleTrees;
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
