package com.ben.aoc;

import com.ben.aoc.dijkstra.Dijkstra;
import com.ben.aoc.dijkstra.State;

import java.util.*;

public class Day20 {
    static int maxX;
    static int maxY;
    static IntPoint end = new IntPoint(0, 0);
    static Set<IntPoint> walls;
    List<IntPoint> route;

    public Day20(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        maxY = lines.size();
        maxX = lines.get(0).length();
        walls = new HashSet<>();
        IntPoint start = new IntPoint(0, 0);
        for(int y = 0; y < lines.size(); y++){
            String line = lines.get(y);
            for(int x = 0; x < line.length(); x++){
                char c = line.charAt(x);
                switch (c){
                    case 'S':
                        start = new IntPoint(x,y);
                        break;
                    case 'E':
                        end = new IntPoint(x,y);
                        break;
                    case '#':
                        walls.add(new IntPoint(x,y));
                }
            }
        }
        RaceState standard = (RaceState) Dijkstra.calculateShortestPath(new RaceState(start));
        List<State> sp = standard.getShortestPath();
        route = new ArrayList<>(sp.size() + 1);
        for (State state : sp) {
            RaceState r = (RaceState) state;
            route.add(r.location);
        }
        route.add(standard.location);

    }

    private boolean isTrack(IntPoint point){
        int x = point.getX();
        int y = point.getY();
        if(x >= 0 && y >= 0 && x < Day20.maxX && y < Day20.maxY){
            return !walls.contains(point);
        }
        return false;
    }

    private int cheatSave (IntPoint start, IntPoint end){
        return Math.abs(route.indexOf(start) - route.indexOf(end)) - start.manhattanDistance(end);
    }

    public Long puzzle1(){
        long result = 0;

        for(IntPoint wall : walls){
            IntPoint right = (IntPoint) wall.getByDirection(new Direction('R'));
            IntPoint left = (IntPoint) wall.getByDirection(new Direction('L'));
            IntPoint up = (IntPoint) wall.getByDirection(new Direction('U'));
            IntPoint down = (IntPoint) wall.getByDirection(new Direction('D'));

            if(isTrack(left) && isTrack(right)){
                if(cheatSave(left, right) >= 100) result ++;
            }

            if(isTrack(up) && isTrack(down)){
                if(cheatSave(up, down) >= 100) result ++;
            }
        }

        return result;
    }

    public Long puzzle2(){
        long result = 0;

        for(int i = 0; i < route.size() - 1; i++){
            for(int j = i+1; j < route.size(); j++){
                IntPoint start = route.get(i);
                IntPoint end = route.get(j);

                if(start.manhattanDistance(end) <= 20){
                    if(cheatSave(start, end) >= 100) result ++;
                }
            }
        }


        return result;
    }

}
