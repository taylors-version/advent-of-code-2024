package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day07 day07 = new Day07("test.txt");
        assertEquals(3749, day07.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day07 day07 = new Day07("test.txt");
        assertEquals(11387, day07.puzzle2());
    }

}
