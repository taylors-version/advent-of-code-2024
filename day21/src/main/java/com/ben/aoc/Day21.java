package com.ben.aoc;


import org.javatuples.Pair;

import java.util.*;

import static java.util.Map.entry;

public class Day21 {
    final Map<Character, IntPoint> numPadPositions = Map.ofEntries(
        entry('7', new IntPoint(0,0)),
        entry('8', new IntPoint(1, 0)),
        entry('9', new IntPoint(2, 0)),
        entry('4', new IntPoint(0, 1)),
        entry('5', new IntPoint(1, 1)),
        entry('6', new IntPoint(2, 1)),
        entry('1', new IntPoint(0, 2)),
        entry('2', new IntPoint(1, 2)),
        entry('3', new IntPoint(2, 2)),
        entry(' ', new IntPoint(0, 3)),
        entry('0', new IntPoint(1, 3)),
        entry('A', new IntPoint(2, 3))
    );
    final Map<Character, IntPoint> dirPadPositions = Map.ofEntries(
        entry(' ', new IntPoint(0, 0)),
        entry('^', new IntPoint(1, 0)),
        entry('A', new IntPoint(2, 0)),
        entry('<', new IntPoint(0, 1)),
        entry('v', new IntPoint(1, 1)),
        entry('>', new IntPoint(2, 1))
    );
    Map<Pair<Character, Character>, String> numPadMoves;
    Map<Pair<Character, Character>, String> dirPadMoves;
    List<String> codes;
    Map<Pair<String, Integer>, Long> cache = new HashMap<>();

    public Day21(String filename){
        codes = Util.readFile(getClass(), filename);
        numPadMoves = new HashMap<>();
        for(Map.Entry<Character, IntPoint> start : numPadPositions.entrySet()){
            for(Map.Entry<Character, IntPoint> end : numPadPositions.entrySet()){
                if(start.getKey() != ' ' && end.getKey() != ' '){
                    numPadMoves.put(new Pair<>(start.getKey(), end.getKey()), numPadMove(start.getValue(), end.getValue()));
                }
            }
        }
        dirPadMoves = new HashMap<>();
        for(Map.Entry<Character, IntPoint> start : dirPadPositions.entrySet()){
            for(Map.Entry<Character, IntPoint> end : dirPadPositions.entrySet()){
                if(start.getKey() != ' ' && end.getKey() != ' '){
                    dirPadMoves.put(new Pair<>(start.getKey(), end.getKey()), dirPadMove(start.getValue(), end.getValue()));
                }
            }
        }
    }

    private String numPadMove(IntPoint start, IntPoint end){
        StringBuilder sb = new StringBuilder();
        int dX = end.getX() - start.getX();
        int dY = end.getY() - start.getY();
        if(start.getY() == 3 && end.getX() == 0){
            sb.append(vertical(dY));
            sb.append(horizontal(dX));
            return sb.toString();
        }
        if(start.getX() == 0 && end.getY() == 3){
            sb.append(horizontal(dX));
            sb.append(vertical(dY));
            return sb.toString();
        }
        if(dY < 0 && dX < 0){
            sb.append(horizontal(dX));
            sb.append(vertical(dY));
            return sb.toString();
        }
        if(dY > 0 && dX < 0){
            sb.append(horizontal(dX));
            sb.append(vertical(dY));
            return sb.toString();
        }
        if(dY > 0 && dX > 0){
            sb.append(vertical(dY));
            sb.append(horizontal(dX));
            return sb.toString();
        }
        if(dY < 0 && dX > 0){
            sb.append(vertical(dY));
            sb.append(horizontal(dX));
            return sb.toString();
        }
        sb.append(horizontal(dX));
        sb.append(vertical(dY));
        return sb.toString();
    }

    private String dirPadMove(IntPoint start, IntPoint end){
        StringBuilder sb = new StringBuilder();
        int dX = end.getX() - start.getX();
        int dY = end.getY() - start.getY();
        if(start.getX() == 0){
            sb.append(horizontal(dX));
            sb.append(vertical(dY));
            return sb.toString();
        }
        if(end.getX() == 0){
            sb.append(vertical(dY));
            sb.append(horizontal(dX));
            return sb.toString();
        }
        if(dY < 0 && dX < 0){
            sb.append(horizontal(dX));
            sb.append(vertical(dY));
            return sb.toString();
        }
        if(dY > 0 && dX < 0){
            sb.append(horizontal(dX));
            sb.append(vertical(dY));
            return sb.toString();
        }
        if(dY > 0 && dX > 0){
            sb.append(vertical(dY));
            sb.append(horizontal(dX));
            return sb.toString();
        }
        if(dY < 0 && dX > 0){
            sb.append(vertical(dY));
            sb.append(horizontal(dX));
            return sb.toString();
        }
        sb.append(horizontal(dX));
        sb.append(vertical(dY));
        return sb.toString();
    }

    private String vertical(int dY){
        if(dY > 0){
            return "v".repeat(dY);
        }
        return "^".repeat(dY * -1);
    }

    private String horizontal(int dX){
        if(dX > 0){
            return ">".repeat(dX);
        }
        return "<".repeat(dX * -1);
    }

    private long getCommandLength(String code,  int depth){
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            Pair<Character, Character> move = new Pair<>(i == 0 ? 'A' : code.charAt(i - 1), code.charAt(i));
            sb.append(numPadMoves.get(move));
            sb.append("A");
        }
        final String start = sb.toString();

        return countChars(start, depth);
    }

    private long countChars(String code, int depth){
        if (depth == 0) {
            return code.length();
        }
        if (code.equals("A")) {
            return 1;
        }
        Pair<String, Integer> key = new Pair<>(code, depth);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        long total = 0;
        for (String move : code.split("A")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= move.length(); i++) {
                Pair<Character, Character> m = new Pair(i == 0 ? 'A' : move.charAt(i - 1), i == move.length() ? 'A' : move.charAt(i));
                sb.append(dirPadMoves.get(m));
                sb.append("A");
            }
            total += countChars(sb.toString(), depth - 1);
        }
        cache.put(key, total);
        return total;
    }

    public Long complexity(String code, long commandLength){
        long val = Long.parseLong(code.substring(0, code.length() - 1));
        return val * commandLength;
    }

    public Long puzzle1(){
        long result = 0;
        cache = new HashMap<>();
        for (final String code : codes) {
            long commandLength = getCommandLength(code, 2);
            result += complexity(code, commandLength);
        }

        return result;
    }

    public Long puzzle2(){
        long result = 0;
        cache = new HashMap<>();
        for (final String code : codes) {
            long commandLength = getCommandLength(code, 25);
            result += complexity(code, commandLength);
        }

        return result;
    }

}
