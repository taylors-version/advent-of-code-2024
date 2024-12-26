package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testPuzzle029A(){
        Day21 day21 = new Day21("029A.txt");
        Long expected = 68*29L;
        assertEquals(expected, day21.puzzle1());
    }

    @Test
    public void testPuzzle980A(){
        Day21 day21 = new Day21("980A.txt");
        Long expected = 60*980L;
        assertEquals(expected, day21.puzzle1());
    }

    @Test
    public void testPuzzle179A(){
        Day21 day21 = new Day21("179A.txt");
        Long expected = 68*179L;
        assertEquals(expected, day21.puzzle1());
    }

    @Test
    public void testPuzzle456A(){
        Day21 day21 = new Day21("456A.txt");
        Long expected = 64*456L;
        assertEquals(expected, day21.puzzle1());
    }

    @Test
    public void testPuzzle379A(){
        Day21 day21 = new Day21("379A.txt");
        Long expected = 64*379L;
        assertEquals(expected, day21.puzzle1());
    }

    @Test
    public void testPuzzle1(){
        Day21 day21 = new Day21("test.txt");
        Long expected = 126384L;
        assertEquals(expected, day21.puzzle1());
    }


}
