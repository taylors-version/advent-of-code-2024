package com.ben.aoc;


import com.ben.aoc.dijkstra.Dijkstra;
import com.ben.aoc.dijkstra.State;
import org.javatuples.Pair;

import java.util.*;

public class Day16 {
    public static char[][] grid;
    private IntPoint start = new IntPoint(0,0);


    public Day16(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        grid = new char[lines.size()][];
        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            grid[i] = line.toCharArray();
            int index = line.indexOf('S');
            if(index != -1){
                start = new IntPoint(index, i);
            }
        }
    }

    private boolean isLegal(IntPoint point){
        int x = point.getX();
        int y = point.getY();
        if(x >= 0 && x < grid[0].length && y >= 0 && y < grid.length){
            return grid[y][x]!='#';
        }
        return false;
    }

    private Set<IntPoint> bfs(IntPoint location, int cost, Direction dir, Set<IntPoint> pastRoute, Set<Pair<IntPoint, Direction>> visited, int max){
        Set<IntPoint> route = new HashSet<>();
        if(cost > max || visited.contains(new Pair<>(location, dir))){
            return new HashSet<>();
        }
        if(grid[location.getY()][location.getX()] == 'E'){
            pastRoute.add(location);
            return pastRoute;
        }
        Set<Pair<IntPoint, Direction>> newVisited = new HashSet<>(visited);
        newVisited.add(new Pair<>(location, dir));
        IntPoint next = (IntPoint) location.getByDirection(dir);
        Set<IntPoint> straight = new HashSet<>();
        if(isLegal(next) ){
            Set<IntPoint> pr = new HashSet<>(pastRoute);
            pr.add(location);
            straight = bfs(next, cost + 1, dir, pr, newVisited, max);
        }
        Set<IntPoint> cw = new HashSet<>();
            cw = bfs(location, cost + 1000, dir.rotateClockwise(), pastRoute, newVisited, max);

        Set<IntPoint> acw = new HashSet<>();
            acw = bfs(location, cost + 1000, dir.rotateAntiClockwise(), pastRoute, newVisited, max);

        route.addAll(straight);
        route.addAll(cw);
        route.addAll(acw);
        return route;
    }


    public long puzzle1(){

        State endState = Dijkstra.calculateShortestPath(new ReindeerState(start, 0, new Direction('R')));
        return endState.getDistance();
    }

    public long puzzle2(){
        int best = Dijkstra.calculateShortestPath(new ReindeerState(start, 0, new Direction('R'))).getDistance();
        List<State> ends = DijkstraPlus.calculateShortestPaths(new ReindeerState(start, 0, new Direction('R')));

        Set<IntPoint> seats = new HashSet<>();
        for(State end : ends){
            if(end.getDistance() == best) {
                for (State path : end.getShortestPath()) {
                    ReindeerState r = (ReindeerState) path;
                    seats.add(r.location);
                }
            }
        }

        return seats.size()+1;
    }

}
