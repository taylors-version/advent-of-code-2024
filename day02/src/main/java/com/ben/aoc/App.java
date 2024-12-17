package com.ben.aoc;

public class App {
    public static void main(String[] args){
        Day02 day02 = new Day02("input.txt");
        System.out.println("puzzle 1: " + day02.puzzle1());
        System.out.println("puzzle 2: " + day02.puzzle2());
    }
}
