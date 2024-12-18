package com.ben.aoc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testParser(){
        Day09 day09 = new Day09("test.txt");
        List<String> expected = Arrays.asList("0", "0", ".", ".", ".", "1", "1", "1", ".", ".", ".", "2", ".", ".", ".", "3", "3", "3", ".", "4", "4", ".", "5", "5", "5", "5", ".", "6", "6", "6", "6", ".", "7", "7", "7", ".", "8", "8", "8", "8", "9", "9");
        assertEquals(expected, day09.getDisk());
    }

    @Test
    public void testPuzzle1(){
        Day09 day09 = new Day09("test.txt");
        assertEquals(1928, day09.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day09 day09 = new Day09("test.txt");
        assertEquals(2858, day09.puzzle2());
    }

}
