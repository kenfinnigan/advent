package me.finnigan.advent;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class AppDec9 {
    private AppDec9() {
    }
    
    record Move(Direction direction, int distance) {
        enum Direction {
            U, D, L, R
        }

        public static Move parse(String input) {
            String[] fields = input.split(" ");
            return new Move(Direction.valueOf(fields[0]), Integer.parseInt(fields[1]));
        }
    }

    public static void main(String[] args) {
        List<String> inputLines = readInput();
        partOne(inputLines);
        partTwo(inputLines);
    }

    public static long partTwo(List<String> inputLines) {
        var rope = new Rope(10);
        inputLines.stream()
            .forEach(line -> rope.moveHead(Move.parse(line)));

        var size = rope.getTailVisited();
        System.out.println("Part Two: " + size);
        return size;
    }

    public static int partOne(List<String> inputLines) {
        var rope = new Rope(2);
        inputLines.stream()
            .forEach(line -> rope.moveHead(Move.parse(line)));

        var size = rope.getTailVisited();
        System.out.println("Part One: " + size);
        return size;
    }

    static class Rope {
        private final List<Point2D> rope;
        private final Set<Point2D> tailVisited = new HashSet<>();
        private final Point2D origin = new Point2D.Double(0, 0);
        private int tailIndex;

        Rope(int length) {
            rope = new ArrayList<>(length);
            tailIndex = length - 1;

            for (int i = 0; i < length; i++) {
                rope.add(new Point2D.Double(0, 0));
            }

            tailVisited.add(origin);
        }

        public Point2D getTail() {
            var tail = rope.get(tailIndex);
            return new Point2D.Double(tail.getX(), tail.getY());
        }

        public int getTailVisited() {
            return tailVisited.size();
        }

        void moveHead(Move move) {
            for (int i = 0; i < move.distance; i++) {
                for (int knotNumber = 0; knotNumber < rope.size(); knotNumber++) {
                    var currentKnot = rope.get(knotNumber);
    
                    if (knotNumber == 0) {
                        // Move head knot
                        moveByOne(currentKnot, move.direction);
                    } else {
                        // Move other knots
                        if (!moveKnotIfNecessary(currentKnot, rope.get(knotNumber - 1))) {
                            break;
                        }
                    }
                }
    
                tailVisited.add(getTail());
            }
        }

        private boolean moveKnotIfNecessary(Point2D currentKnot, Point2D previousKnot) {
            var point = getRequiredMove(currentKnot, previousKnot);

            if (!point.equals(origin)) {
                currentKnot.setLocation(currentKnot.getX() + point.getX(), currentKnot.getY() + point.getY());
                return true;
            }
            return false;
        }

        private Point2D getRequiredMove(Point2D currentKnot, Point2D previousKnot) {
            var distance = previousKnot.distance(currentKnot);

            if (distance >= 2.0) {
                var dx = previousKnot.getX() - currentKnot.getX();
                var dy = previousKnot.getY() - currentKnot.getY();

                var x = dx < 0 ? -1 : dx > 0 ? 1 : 0;
                var y = dy < 0 ? -1 : dy > 0 ? 1 : 0;

                return new Point2D.Double(x, y);
            }
            return new Point2D.Double(0, 0);
        }

        private void moveByOne(Point2D knot, Move.Direction direction) {
            var x = knot.getX();
            var y = knot.getY();

            switch (direction) {
                case U -> y++;
                case D -> y--;
                case L -> x--;
                case R -> x++;
            }

            knot.setLocation(x, y);
        }
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
