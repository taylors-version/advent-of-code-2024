package com.ben.aoc;

import java.util.*;

public class Day19 {
    Set<String> patterns;
    List<String> designs;
    Map<String, Boolean> possibleCache;
    Map<String, Long> waysCache;

    public Day19(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        patterns = new HashSet<>();
        designs = new ArrayList<>();
        patterns.addAll(Arrays.asList(lines.get(0).split(", ")));

        for(int i = 2; i < lines.size(); i++){
            designs.add(lines.get(i));
        }

    }

    private boolean isPossible(String design){
        if(possibleCache.containsKey(design)){
            return possibleCache.get(design);
        }
        if(patterns.contains(design)){
            possibleCache.put(design, true);
            return true;
        }

        for(String pattern : patterns){
            if(design.startsWith(pattern)){
                if(isPossible(design.substring(pattern.length()))) {
                    possibleCache.put(design, true);
                    return true;
                }
            }
        }
        possibleCache.put(design, false);
        return false;
    }

    private long getWays(String design){
        if(waysCache.containsKey(design)){
            return waysCache.get(design);
        }
        if(design.isEmpty()){
            waysCache.put(design, 1L);
            return 1;
        }
        long ways = 0;

        for(String pattern : patterns){
            if(design.startsWith(pattern)){
                ways += getWays(design.substring(pattern.length()));
            }
        }
        waysCache.put(design, ways);
        return ways;
    }

    public Long puzzle1(){
        long result = 0;
        possibleCache = new HashMap<>();

        for(String design : designs){
            if(isPossible(design)) result++;
        }

        return result;
    }

    public Long puzzle2(){
        long result = 0;
        waysCache = new HashMap<>();

        for(String design : designs){
            result += getWays(design);
        }

        return result;
    }

}
