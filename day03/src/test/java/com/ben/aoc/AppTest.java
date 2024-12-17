package com.ben.aoc;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testPuzzle1(){
        Day03 day03 = new Day03("test.txt");
        assertEquals(161, day03.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day03 day03 = new Day03("test2.txt");
        assertEquals(48, day03.puzzle2());
    }
}
