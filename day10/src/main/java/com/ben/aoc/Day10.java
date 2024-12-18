package com.ben.aoc;

import java.util.*;

public class Day10 {
    Integer[][] grid;
    Set<IntPoint> starts;

    public Day10(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        grid = new Integer[lines.size()][];
        starts = new HashSet<>();
        for(int i = 0; i<lines.size(); i++){
            String line = lines.get(i);
            grid[i] = new Integer[line.length()];
            for(int j = 0; j<line.length(); j++){
                int value = line.charAt(j)-48;
                grid[i][j] = value;
                if(value == 0){
                    starts.add(new IntPoint(j,i));
                }
            }
        }
    }

    public boolean isSafe(IntPoint point){
        int x = point.getX();
        int y = point.getY();
        return x >= 0 && x < grid[0].length && y >= 0 && y < grid.length;
    }

    public List<IntPoint> next(IntPoint point){
        List<IntPoint> next = new ArrayList<>();
        int value = grid[point.getY()][point.getX()];
        List<Point<Integer>> neighbours = point.allNeighbours();
        for(Point<Integer> neighbour : neighbours){
            if(isSafe((IntPoint) neighbour)) {
                int neighbourValue = grid[neighbour.getY()][neighbour.getX()];
                if (neighbourValue == value + 1) {
                    next.add((IntPoint) neighbour);
                }
            }
        }
        return next;
    }

    public long puzzle1(){
        long result = 0;

        for(IntPoint start : starts){
            Set<IntPoint> ends = new HashSet<>();
            List<IntPoint> next = next(start);
            while(!next.isEmpty()){
                IntPoint point = next.remove(0);
                if (grid[point.getY()][point.getX()] == 9){
                    ends.add(point);
                }else{
                    next.addAll(next(point));
                }
            }
            result += ends.size();
        }

        return result;
    }

    public long puzzle2(){        long result = 0;

        for(IntPoint start : starts){
            List<IntPoint> next = next(start);
            while(!next.isEmpty()){
                IntPoint point = next.remove(0);
                if (grid[point.getY()][point.getX()] == 9){
                    result ++;
                }else{
                    next.addAll(next(point));
                }
            }
        }

        return result;
    }
}
