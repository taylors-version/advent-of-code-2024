package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day08 day08 = new Day08("test.txt");
        assertEquals(14, day08.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day08 day08 = new Day08("test.txt");
        assertEquals(34, day08.puzzle2());
    }

}
