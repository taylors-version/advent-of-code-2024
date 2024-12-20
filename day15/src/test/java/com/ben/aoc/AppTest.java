package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testSmallPuzzle1(){
        Day15 day15 = new Day15("small.txt");
        assertEquals(2028, day15.puzzle1());
    }

    @Test
    public void testPuzzle1(){
        Day15 day15 = new Day15("test.txt");
        assertEquals(10092, day15.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day15 day15 = new Day15("test.txt");
        assertEquals(9021, day15.puzzle2());
    }

    @Test
    public void testSmallPuzzle2(){
        Day15 day15 = new Day15("small2.txt");
        assertEquals(105+207+306, day15.puzzle2());
    }

}
