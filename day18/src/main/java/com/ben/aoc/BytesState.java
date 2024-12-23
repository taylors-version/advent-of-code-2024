package com.ben.aoc;

import com.ben.aoc.dijkstra.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BytesState extends State {
    final IntPoint location;

    public BytesState(IntPoint location){
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
            if(isSafe(point) && !Day18.fallen.contains(point)){
                next.add(new BytesState(point));
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
        return location.equals(new IntPoint(Day18.maxSide, Day18.maxSide));
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(!(o instanceof BytesState b)){
            return false;
        }
        return location.equals(b.location);
    }

    @Override
    public int hashCode(){
        return Objects.hash(location);
    }

    private boolean isSafe(IntPoint point){
        int x = point.getX();
        int y = point.getY();
        boolean result =  x >= 0 && y >= 0 && x <= Day18.maxSide && y <= Day18.maxSide;
        return result;
    }
}
