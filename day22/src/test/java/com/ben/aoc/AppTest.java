package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day22 day22 = new Day22("test.txt");
        Long expected = 37327623L;
        assertEquals(expected, day22.puzzle1());
    }

}
