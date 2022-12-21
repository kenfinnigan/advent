package me.finnigan.advent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class AppDec8Test {

    @Test
    void testVisibleTrees() {
        List<String> inputLines = Arrays.asList(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390"
        );

        assertEquals(21, AppDec8.partOne(inputLines));
    }

    @Test
    void testHighestTreeSceneScore() {
        List<String> inputLines = Arrays.asList(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390"
        );

        assertEquals(8, AppDec8.partTwo(inputLines));
    }
}
