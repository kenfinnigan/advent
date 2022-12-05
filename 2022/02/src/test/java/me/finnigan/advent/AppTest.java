package me.finnigan.advent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class AppTest {

    @Test
    void testWinTotal() {
        List<String> lines = Arrays.asList("A Y", "B X", "C Z");
        int gamesTotal = lines.stream()
            .flatMapToInt(line -> IntStream.of(App.playRound(line)))
            .sum();

        assertEquals(15, gamesTotal);
    }

    @Test
    void testWinByShapeTotal() {
        List<String> lines = Arrays.asList("A Y", "B X", "C Z");
        int gamesTotal = lines.stream()
            .flatMapToInt(line -> IntStream.of(App.playRoundShape(line)))
            .sum();

        assertEquals(12, gamesTotal);
    }
}
