package com.ben.aoc;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day04 day04 = new Day04("test.txt");
        assertEquals(18, day04.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day04 day04 = new Day04("test.txt");
        assertEquals(9, day04.puzzle2());
    }

}
