package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day06 day06 = new Day06("test.txt");
        assertEquals(41, day06.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day06 day06 = new Day06("test.txt");
        assertEquals(6, day06.puzzle2());
    }

}
