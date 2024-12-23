package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day18 day18 = new Day18("test.txt");
        day18.fall = 12;
        Day18.maxSide = 6;
        Long expected = 22L;
        assertEquals(expected, day18.puzzle1());
    }


}
