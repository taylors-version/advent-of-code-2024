package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day12 day12 = new Day12("test.txt");
        assertEquals(140, day12.puzzle1());
    }

    @Test
    public void test2Puzzle1(){
        Day12 day12 = new Day12("test2.txt");
        assertEquals(1930, day12.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day12 day12 = new Day12("test.txt");
        assertEquals(80, day12.puzzle2());
    }

    @Test
    public void test2Puzzle2(){
        Day12 day12 = new Day12("test3.txt");
        assertEquals(368, day12.puzzle2());
    }

}
