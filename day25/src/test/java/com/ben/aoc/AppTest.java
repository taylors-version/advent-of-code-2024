package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day25 day25 = new Day25("test.txt");
        Long expected = 3L;
        assertEquals(expected, day25.puzzle1());
    }

}
