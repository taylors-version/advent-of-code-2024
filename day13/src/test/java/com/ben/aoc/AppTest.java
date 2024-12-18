package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day13 day13 = new Day13("test.txt");
        assertEquals(480, day13.puzzle1());
    }

}
