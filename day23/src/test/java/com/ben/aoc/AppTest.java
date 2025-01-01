package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day23 day23 = new Day23("test.txt");
        Long expected = 7L;
        assertEquals(expected, day23.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day23 day23 = new Day23("test.txt");
        String expected = "co,de,ka,ta";
        assertEquals(expected, day23.puzzle2());
    }

}
