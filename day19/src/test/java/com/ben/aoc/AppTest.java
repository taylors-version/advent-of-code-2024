package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day19 day19 = new Day19("test.txt");
        Long expected = 6L;
        assertEquals(expected, day19.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day19 day19 = new Day19("test.txt");
        Long expected = 16L;
        assertEquals(expected, day19.puzzle2());
    }

}
