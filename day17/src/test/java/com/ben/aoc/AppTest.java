package com.ben.aoc;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testPuzzle1(){
        Day17 day17 = new Day17("test.txt");
        assertEquals("4,6,3,5,6,3,5,2,1,0", day17.puzzle1());
    }


}
