package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day24 day24 = new Day24("test.txt");
        Long expected = 4L;
        assertEquals(expected, day24.puzzle1());
    }

    @Test
    public void test2Puzzle1(){
        Day24 day24 = new Day24("test2.txt");
        Long expected = 2024L;
        assertEquals(expected, day24.puzzle1());
    }

}
