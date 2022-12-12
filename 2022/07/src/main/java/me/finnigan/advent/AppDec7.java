package me.finnigan.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AppDec7 {
    private AppDec7() {
    }

    private static final Map<String, Directory> directories = new HashMap<>();
    private static final long TOTAL_DISK_SPACE = 70_000_000L;
    private static final long UNUSED_SPACE = 30_000_000L;

    private record File(String name, long size) {
    }

    private record Directory(String directoryName, List<File> files) {
    }

    private record DirectorySize(String directoryName, long size) {
    }
    
    public static void main(String[] args) {
        List<String> inputLines = readInput();
        partOne(inputLines);
        partTwo(inputLines);
    }

    public static long partTwo(List<String> inputLines) {
        directories.clear();
        processInput(inputLines);

        long rootSize = getTotalSize(directories.get("/"), 0L);
        long freeSpace = TOTAL_DISK_SPACE - rootSize;
        long neededSpace = UNUSED_SPACE - freeSpace;

        long smallestDirectory = directories.values()
        .stream()
        .filter(directory -> !directory.directoryName().equals(""))
        .map(directory -> getTotalSize(directory, 0L))
        .filter(size -> size >= neededSpace)
        .min(Long::compareTo)
        .orElseThrow();

        System.out.println("Part Two: " + smallestDirectory);
        return smallestDirectory;
    }

    public static Long partOne(List<String> inputLines) {
        directories.clear();
        processInput(inputLines);

        Long calculateSize = directories.values()
                .stream()
                .map(directory -> getTotalSize(directory, 0L))
                .filter(totalSize -> totalSize <= 100_000L)
                .reduce(Long::sum)
                .orElseThrow();

        System.out.println("Part One: " + calculateSize);
        return calculateSize;
    }

    private static long getTotalSize(Directory directory, long totalSize) {
        List<File> files = directory.files();
        for (File file : files) {
            totalSize += file.size() != -1L
                    ? file.size()
                    : getTotalSize(directories.get(file.name()), 0L);
        }
        return totalSize;
    }

    private static void processInput(List<String> lines) {
        Directory rootDirectory = new Directory("", new ArrayList<>());
        directories.put("/", rootDirectory);
        Directory currentDirectory = rootDirectory;
        for (String line : lines) {
            if (line.startsWith("$ cd")) {
                currentDirectory = changeDirectory(currentDirectory, line);
            } else if (!line.startsWith("$ ls")) {
                addFile(currentDirectory, line);
            }
        }
    }

    private static Directory changeDirectory(Directory currentDirectory, String line) {
        String inputDirectory = line.substring(5);
        if (inputDirectory.equals("/")) {
            return directories.get("/");
        }
        if (inputDirectory.equals("..")) {
            Directory directory = directories.get(
                    currentDirectory.directoryName().substring(0, currentDirectory.directoryName().lastIndexOf('/')));
            return directory == null ? directories.get("/") : directory;
        }
        return directories.get("%s/%s".formatted(currentDirectory.directoryName(), inputDirectory));
    }

    private static void addFile(Directory currentDirectory, String line) {
        if (line.startsWith("dir")) {
            String directoryName = line.substring(4);
            Directory directory = new Directory("%s/%s".formatted(currentDirectory.directoryName(), directoryName),
                    new ArrayList<>());
            String path = "%s/%s".formatted(currentDirectory.directoryName(), directoryName);
            directories.put(path, directory);
            currentDirectory.files().add(new File(path, -1));
        } else {
            String[] fileInfo = line.split(" ");
            currentDirectory.files().add(new File(fileInfo[1], Long.parseLong(fileInfo[0])));
        }
    }

    private static int calculateSize(List<Directory> directories) {
        int totalSize = 0;

        for (Directory directory : directories) {
            if (directory.getSize() <= 100000) {
                totalSize += directory.getSize();
            }
        }

        return totalSize;
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
