package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {
    @Test
    public void testPuzzle1(){
        Day01 day01 = new Day01("test.txt");
        long expected = 11;
        assertEquals(expected, day01.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day01 day01 = new Day01("test.txt");
        long expected = 31;
        assertEquals(expected, day01.puzzle2());
    }
}
