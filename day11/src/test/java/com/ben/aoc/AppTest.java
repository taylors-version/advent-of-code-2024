package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day11 day11 = new Day11("test.txt");
        assertEquals(55312, day11.puzzle1());
    }

}
