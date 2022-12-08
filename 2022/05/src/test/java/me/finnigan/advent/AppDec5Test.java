package me.finnigan.advent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class AppDec5Test {

    @Test
    void testContainedRanges() {
        List<String> lines = Arrays.asList(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 ",
            "",
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2");

        assertEquals("CMZ", AppDec5.partOne(lines));
    }

    @Test
    void testOverlappedRanges() {
        List<String> lines = Arrays.asList(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 ",
            "",
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2");

        assertEquals("MCD", AppDec5.partTwo(lines));
    }
}
