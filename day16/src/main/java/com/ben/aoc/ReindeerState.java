package com.ben.aoc;

import com.ben.aoc.dijkstra.State;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ReindeerState extends State {
    final IntPoint location;
    final int cost;
    final Direction direction;

    public ReindeerState(IntPoint location, int cost, Direction dir){
        this.location = location;
        this.cost = cost;
        this.direction = dir;
    }
    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public List<State> next() {
        LinkedList<State> nextList = new LinkedList<>();

        IntPoint next = (IntPoint) location.getByDirection(direction);
        if(Day16.grid[next.getY()][next.getX()] != '#'){
            nextList.add(new ReindeerState(next, 1, direction));
        }
        Direction cw = direction.rotateClockwise();
        IntPoint cwPoint = (IntPoint) location.getByDirection(cw);
        if(Day16.grid[cwPoint.getY()][cwPoint.getX()] != '#'){
            nextList.add(new ReindeerState(location, 1000, cw));
        }
        Direction acw = direction.rotateAntiClockwise();
        IntPoint acwPoint = (IntPoint) location.getByDirection(acw);
        if(Day16.grid[acwPoint.getY()][acwPoint.getX()] != '#'){
            nextList.add(new ReindeerState(location, 1000, acw));
        }


        return nextList;
    }

    @Override
    public String toString() {
        return location.toString() + " " + direction.toString();
    }

    @Override
    public boolean isFinished() {
        return Day16.grid[location.getY()][location.getX()] == 'E';
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(!(o instanceof ReindeerState r)){
            return false;
        }
        return location.equals(r.location) && direction.equals(r.direction);
    }

    @Override
    public int hashCode(){
        return Objects.hash(location, direction);
    }
}
