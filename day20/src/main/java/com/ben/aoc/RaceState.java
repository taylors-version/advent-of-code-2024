package com.ben.aoc;

import com.ben.aoc.dijkstra.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RaceState extends State {
    final IntPoint location;

    public RaceState(IntPoint location){
        this.location = location;
    }

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public List<State> next() {
        List<State> next = new ArrayList<>();

        for(Point<Integer> n : location.allNeighbours()){
            IntPoint point = (IntPoint) n;
            if(isSafe(point) && !Day20.walls.contains(point)){
                next.add(new RaceState(point));
            }
        }

        return next;
    }

    @Override
    public String toString() {
        return location.toString();
    }

    @Override
    public boolean isFinished() {
        return location.equals(Day20.end);
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(!(o instanceof RaceState r)){
            return false;
        }
        return location.equals(r.location);
    }

    @Override
    public int hashCode(){
        return Objects.hash(location);
    }

    private boolean isSafe(IntPoint point){
        int x = point.getX();
        int y = point.getY();
        return x >= 0 && y >= 0 && x < Day20.maxX && y < Day20.maxY;
    }
}
