package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day16 day16 = new Day16("test1.txt");
        assertEquals(7036, day16.puzzle1());
    }

    @Test
    public void test2Puzzle1(){
        Day16 day16 = new Day16("test2.txt");
        assertEquals(11048, day16.puzzle1());
    }

    @Test
    public void test3Puzzle2(){
        Day16 day16 = new Day16("simple.txt");
        assertEquals(11, day16.puzzle2());
    }

    @Test
    public void testPuzzle2(){
        Day16 day16 = new Day16("test1.txt");
        assertEquals(45, day16.puzzle2());
    }

    @Test
    public void test2Puzzle2(){
        Day16 day16 = new Day16("test2.txt");
        assertEquals(64, day16.puzzle2());
    }

}
