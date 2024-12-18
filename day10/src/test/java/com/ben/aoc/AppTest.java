package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day10 day10 = new Day10("test.txt");
        assertEquals(36, day10.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day10 day10 = new Day10("test.txt");
        assertEquals(81, day10.puzzle2());
    }

}
