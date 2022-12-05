package me.finnigan.advent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class AppTest {

    @Test
    void testMaxCalories() {
        List<String> lines = Arrays.asList("1000", "2000", "3000", "", "4000", "", "5000", "6000", "", "7000", "8000", "9000", "", "10000");
        List<Integer> calorieCounts = App.getElfCalorieCounts(lines);
        int maxValue = App.getMaxElfCalories(calorieCounts);

        assertEquals(maxValue, 24000);
    }
}
