package com.ben.aoc;

import org.javatuples.Pair;

import java.util.*;

public class Day11 {
    List<Long> stones = new ArrayList<>();
    Map<Pair<Long, Integer>, Long> cache = new HashMap<>();

    public Day11(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        String[] numbers = lines.get(0).split(" ");
        for(String num : numbers){
            stones.add(Long.parseLong(num));
        }
    }
    
    public Long blink(Long stone, Integer steps){
        if(steps == 0){
            return 1L;
        }
        Pair<Long, Integer> key = new Pair<>(stone, steps);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        Long stones;
        
        Integer newSteps = steps - 1;
        if(stone == 0L){
            stones = blink(1L, newSteps);
        }else if(isEvenDigits(stone)){
            Pair<Long, Long> newStones = split(stone);
            stones = blink(newStones.getValue0(), newSteps) + blink(newStones.getValue1(), newSteps);
        }else{
            stones = blink(stone * 2024, newSteps);
        }
        cache.put(key, stones);
        return stones;
    }

    private boolean isEvenDigits(Long stone) {
        String str = stone.toString();
        return str.length() % 2 == 0;
    }

    private Pair<Long, Long>split(Long stone){
        String str = stone.toString();
        int splitAfter = str.length()/2;
        Long a = Long.parseLong(str.substring(0, splitAfter));
        Long b = Long.parseLong(str.substring(splitAfter));
        return new Pair<>(a,b);
    }

    public long puzzle1(){
        long result = 0;
        for(Long stone : stones){
            result += blink(stone, 25);
        }
        return result;
    }

    public long puzzle2(){
        long result = 0;
        for(Long stone : stones){
            result += blink(stone, 75);
        }
        return result;
    }
}
