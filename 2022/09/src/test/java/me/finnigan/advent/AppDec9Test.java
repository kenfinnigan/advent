package me.finnigan.advent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class AppDec9Test {

    @Test
    void testTwoKnotRope() {
        List<String> inputLines = Arrays.asList(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2"
        );

        assertEquals(13, AppDec9.partOne(inputLines));
    }

    @Test
    void testTenKnotRope() {
        List<String> inputLines = Arrays.asList(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2"
        );

        assertEquals(1, AppDec9.partTwo(inputLines));
    }

    @Test
    void testTenKnotRopeAgain() {
        List<String> inputLines = Arrays.asList(
            "R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20"
        );

        assertEquals(36, AppDec9.partTwo(inputLines));
    }
}
