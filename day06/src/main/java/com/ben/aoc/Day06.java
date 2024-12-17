package com.ben.aoc;

import org.javatuples.Pair;

import java.util.*;

public class Day06 {
    private final int xMax;
    private final int yMax;
    Set<IntPoint> walls;
    IntPoint start = new IntPoint(0,0);


    public Day06(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        walls = new HashSet<>();
        yMax = lines.size();
        xMax = lines.get(0).length();
        for(int y = 0; y < yMax; y++){
            String line = lines.get(y);
            for(int x = 0; x < xMax; x++){
                if(line.charAt(x) == '#'){
                    walls.add(new IntPoint(x,y));
                }else if(line.charAt(x) == '^'){
                    start = new IntPoint(x, y);
                }
            }
        }
    }

    public boolean isInGrid(IntPoint point){
        int x = point.getX();
        int y = point.getY();
        return x >= 0 && x < xMax && y >= 0 && y < yMax;
    }

    public Set<IntPoint> getGuardVisited(){
        Set<IntPoint> visited = new HashSet<>();
        Direction dir = new Direction('u');
        IntPoint guard = new IntPoint(start.getX(), start.getY());

        while(isInGrid(guard)){
            visited.add(guard);
            IntPoint next = (IntPoint) guard.getByDirection(dir);
            if(walls.contains(next)){
                dir = dir.rotateClockwise();
            }else{
                guard = next;
            }
        }
        return visited;
    }

    public long puzzle1(){
        Set<IntPoint> visited = getGuardVisited();

        return visited.size();
    }

    public long puzzle2(){
        long result = 0;

        Set<IntPoint> defaultWalk = getGuardVisited();
        defaultWalk.remove(start);

        for(IntPoint point : defaultWalk){
            Set<IntPoint> newWalls = new HashSet<>(walls);
            newWalls.add(point);
            Set<Pair<IntPoint, Direction>> visited = new HashSet<>();

            Direction dir = new Direction('u');
            IntPoint guard = new IntPoint(start.getX(), start.getY());
            Pair<IntPoint, Direction> current = new Pair<>(guard, dir);

            while(isInGrid(guard)){
                if(visited.contains(current)){
                    result++;
                    break;
                }
                visited.add(current);
                IntPoint next = (IntPoint) guard.getByDirection(dir);
                if(newWalls.contains(next)){
                    dir = dir.rotateClockwise();
                }else{
                    guard = next;
                }
                current = new Pair<>(guard, dir);
            }
        }

        return result;
    }
}
