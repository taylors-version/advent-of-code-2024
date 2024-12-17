package com.ben.aoc;

public class App {
    public static void main(String[] args){
        Day01 day01 = new Day01("input.txt");
        System.out.println("puzzle 1: " + day01.puzzle1());
        System.out.println("puzzle 2: " + day01.puzzle2());
    }
}
