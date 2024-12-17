package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day05 day05 = new Day05("test.txt");
        assertEquals(143, day05.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day05 day05 = new Day05("test.txt");
        assertEquals(123, day05.puzzle2());
    }
}
