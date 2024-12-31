package com.ben.aoc;

import org.javatuples.Quartet;

import java.util.*;

public class Day22 {
    private final long mod = 16777216;
    List<Long> inputs;

    public Day22(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        inputs = new ArrayList<>();
        for(String line : lines){
            inputs.add(Long.parseLong(line));
        }
    }

    private Long generate(Long secret){
        secret = mixPrune(secret, secret * 64);
        secret = mixPrune(secret,secret / 32);
        secret = mixPrune(secret, secret * 2048);

        return secret;
    }

    private long mixPrune(Long secret, Long input){
        return (input ^ secret) % mod;
    }

    public Long puzzle1(){
        long result = 0;

        for(Long input : inputs){
            for(int i = 0; i < 2000; i++){
                input = generate(input);
            }
            result += input;
        }

        return result;
    }

    public Long puzzle2(){
        List<Map<Quartet<Integer, Integer, Integer, Integer>, Integer>> deltaPatterns = new ArrayList<>();
        Set<Quartet<Integer, Integer, Integer, Integer>> patternsSet = new HashSet<>();
        for(Long input : inputs){
            List<Integer> buyerCosts = new ArrayList<>((int) (input % 10));
            Map<Quartet<Integer, Integer, Integer, Integer>, Integer> deltaPattern = new HashMap<>();
            for(int i = 0; i < 2000; i++){
                input = generate(input);
                int cost = (int) (input % 10);
                buyerCosts.add(cost);
                if(i > 3){
                    int[] pastCosts = {buyerCosts.get(i-4), buyerCosts.get(i-3), buyerCosts.get(i-2), buyerCosts.get(i-1), buyerCosts.get(i)};
                    Quartet<Integer, Integer, Integer, Integer> pattern = new Quartet<>(pastCosts[1]-pastCosts[0], pastCosts[2]-pastCosts[1], pastCosts[3]-pastCosts[2], pastCosts[4]-pastCosts[3]);
                    deltaPattern.putIfAbsent(pattern, pastCosts[4]);
                    patternsSet.add(pattern);
                }
            }
            deltaPatterns.add(deltaPattern);
        }

        long result = 0;

        for(Quartet<Integer, Integer, Integer, Integer> pattern : patternsSet){
            int value = 0;
            for(Map<Quartet<Integer, Integer, Integer, Integer>, Integer> list : deltaPatterns){
                value += list.getOrDefault(pattern, 0);
            }
            result = Math.max(result, value);
        }


        return result;
    }

}
