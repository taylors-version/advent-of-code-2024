package com.ben.aoc;

import com.ben.aoc.dijkstra.Dijkstra;
import com.ben.aoc.dijkstra.State;

import java.util.*;

public class DijkstraPlus extends Dijkstra {

    public static List<State> calculateShortestPaths(State source) {
        source.setDistance(0);
        List<State> finishedStates = new ArrayList<>();
        Map<State, Integer> settledStates = new HashMap<>();
        List<State> unsettledStates = new ArrayList<State>();

        unsettledStates.add(source);

        while(unsettledStates.size() != 0) {
            State currentState = getLowestState(unsettledStates);
            unsettledStates.remove(currentState);
            if(currentState.isFinished()) {
                finishedStates.add(currentState);
            }else {
                for (State adjacentState : currentState.next()) {

                    int edgeWeight = adjacentState.getCost();
                    CalculateMinimumDistance(adjacentState, edgeWeight, currentState);
                    if (!settledStates.containsKey(adjacentState) || settledStates.get(adjacentState) == adjacentState.getDistance()) {
                        unsettledStates.add(adjacentState);
                    }
                }
            }
            settledStates.put(currentState, currentState.getDistance());
        }
        return finishedStates;
    }

    protected static State getLowestState(List<State> unsettledStates) {
        State minimumState = null;
        int lowestDistance = Integer.MAX_VALUE;
        for(State s : unsettledStates) {
            int stateDistance = s.getDistance();
            if(stateDistance < lowestDistance) {
                lowestDistance = stateDistance;
                minimumState = s;
            }
        }
        return minimumState;
    }
}
