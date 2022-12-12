package me.finnigan.advent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppDec6Test {

    @Test
    void testFindFirstMarker() {
        String input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
        assertEquals(7, AppDec6.partOne(input));

        input = "bvwbjplbgvbhsrlpgdmjqwftvncz";
        assertEquals(5, AppDec6.partOne(input));

        input = "nppdvjthqldpwncqszvftbrmjlhg";
        assertEquals(6, AppDec6.partOne(input));

        input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
        assertEquals(10, AppDec6.partOne(input));

        input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
        assertEquals(11, AppDec6.partOne(input));
    }

    @Test
    void testMessageStartMarker() {
        String input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";
        assertEquals(19, AppDec6.partTwo(input));

        input = "bvwbjplbgvbhsrlpgdmjqwftvncz";
        assertEquals(23, AppDec6.partTwo(input));

        input = "nppdvjthqldpwncqszvftbrmjlhg";
        assertEquals(23, AppDec6.partTwo(input));

        input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg";
        assertEquals(29, AppDec6.partTwo(input));

        input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw";
        assertEquals(26, AppDec6.partTwo(input));
    }
}
