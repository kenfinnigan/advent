package me.finnigan.advent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class AppDec12Test {

    @Test
    void testMonkeyShenanigans() {
        List<String> inputLines = Arrays.asList(
            "Monkey 0:",
          "  Starting items: 79, 98",
          "  Operation: new = old * 19",
          "  Test: divisible by 23",
          "    If true: throw to monkey 2",
          "    If false: throw to monkey 3",
          "",
          "Monkey 1:",
          "  Starting items: 54, 65, 75, 74",
          "  Operation: new = old + 6",
          "  Test: divisible by 19",
          "    If true: throw to monkey 2",
          "    If false: throw to monkey 0",
          "",
          "Monkey 2:",
          "  Starting items: 79, 60, 97",
          "  Operation: new = old * old",
          "  Test: divisible by 13",
          "    If true: throw to monkey 1",
          "    If false: throw to monkey 3",
          "",
          "Monkey 3:",
          "  Starting items: 74",
          "  Operation: new = old + 3",
          "  Test: divisible by 17",
          "    If true: throw to monkey 0",
          "    If false: throw to monkey 1",
          ""
        );

        assertEquals(10605, AppDec12.partOne(String.join("\n", inputLines)));
    }

    @Test
    void testMonkeyShenanigansWithMoreRounds() {
        List<String> inputLines = Arrays.asList(
            "Monkey 0:",
          "  Starting items: 79, 98",
          "  Operation: new = old * 19",
          "  Test: divisible by 23",
          "    If true: throw to monkey 2",
          "    If false: throw to monkey 3",
          "",
          "Monkey 1:",
          "  Starting items: 54, 65, 75, 74",
          "  Operation: new = old + 6",
          "  Test: divisible by 19",
          "    If true: throw to monkey 2",
          "    If false: throw to monkey 0",
          "",
          "Monkey 2:",
          "  Starting items: 79, 60, 97",
          "  Operation: new = old * old",
          "  Test: divisible by 13",
          "    If true: throw to monkey 1",
          "    If false: throw to monkey 3",
          "",
          "Monkey 3:",
          "  Starting items: 74",
          "  Operation: new = old + 3",
          "  Test: divisible by 17",
          "    If true: throw to monkey 0",
          "    If false: throw to monkey 1",
          ""
        );

        assertEquals(2713310158L, AppDec12.partTwo(String.join("\n", inputLines)));
    }
}
