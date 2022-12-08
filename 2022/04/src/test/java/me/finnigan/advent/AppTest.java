package me.finnigan.advent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class AppTest {

    @Test
    void testContainedRanges() {
        List<String> lines = Arrays.asList(
            "2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8");

        assertEquals(2, App.partOne(lines));
    }

    @Test
    void testOverlappedRanges() {
        List<String> lines = Arrays.asList(
            "2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8");

        assertEquals(4, App.partTwo(lines));
    }
}
