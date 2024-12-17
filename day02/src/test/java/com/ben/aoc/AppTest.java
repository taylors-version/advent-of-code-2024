package com.ben.aoc;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testSorted(){
        Day02 day02 = new Day02("test.txt");
        List<Integer> desc = Arrays.asList(7, 6, 4, 2, 1);
        List<Integer> asc = Arrays.asList(1, 2, 7, 8, 9);
        List<Integer> not = Arrays.asList(1, 3, 2, 4, 5);

        assertTrue(day02.isSorted(desc));
        assertTrue(day02.isSorted(asc));
        assertFalse(day02.isSorted(not));
    }

    @Test
    public void testSteps(){
        Day02 day02 = new Day02("test.txt");
        List<Integer> badInc = Arrays.asList(1, 2, 7, 8, 9);
        List<Integer> badDec = Arrays.asList(9, 7, 6, 2, 1);
        List<Integer> badEq = Arrays.asList(8, 6, 4, 4, 1);
        List<Integer> safe = Arrays.asList(1, 3, 6, 7, 9);

        assertFalse(day02.isSmallSteps(badInc));
        assertFalse(day02.isSmallSteps(badDec));
        assertFalse(day02.isSmallSteps(badEq));
        assertTrue(day02.isSmallSteps(safe));
    }

    @Test
    public void testPuzzle1(){
        Day02 day02 = new Day02("test.txt");
        assertEquals(2, day02.puzzle1());
    }

    @Test
    public void testPuzzle2(){
        Day02 day02 = new Day02("test.txt");
        assertEquals(4, day02.puzzle2());
    }
}
