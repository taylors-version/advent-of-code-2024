package com.ben.aoc;

import java.util.*;

public class Day12 {
    char[][] grid;

    public Day12(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        grid = new char[lines.size()][];
        for(int i = 0; i < lines.size(); i++){
            grid[i] = lines.get(i).toCharArray();
        }
    }

    public boolean isSafe(IntPoint point){
        int x = point.getX();
        int y = point.getY();
        return x >= 0 && x < grid[0].length && y >= 0 && y < grid.length;
    }

    private Long sides(Set<IntPoint> points){
        long result = 0;
        for(IntPoint point : points){
            int x = point.getX();
            int y = point.getY();
            //Check UL
            if(!points.contains((IntPoint) point.above()) && !points.contains((IntPoint) point.left())){
                result++;
            }
            if(points.contains((IntPoint) point.above()) &&
                    points.contains((IntPoint) point.left()) &&
                    !points.contains(new IntPoint(x-1, y-1))){
                result++;
            }

            //Check UR
            if(!points.contains((IntPoint) point.above()) && !points.contains((IntPoint) point.right())){
                result++;
            }
            if(points.contains((IntPoint) point.above()) &&
                    points.contains((IntPoint) point.right()) &&
                    !points.contains(new IntPoint(x+1, y-1))){
                result++;
            }

            //Check DR
            if(!points.contains((IntPoint) point.below()) && !points.contains((IntPoint) point.right())){
                result++;
            }
            if(points.contains((IntPoint) point.below()) &&
                    points.contains((IntPoint) point.right()) &&
                    !points.contains(new IntPoint(x+1, y+1))){
                result++;
            }

            //Check LR
            if(!points.contains((IntPoint) point.below()) && !points.contains((IntPoint) point.left())){
                result++;
            }
            if(points.contains((IntPoint) point.below()) &&
                    points.contains((IntPoint) point.left()) &&
                    !points.contains(new IntPoint(x-1, y+1))){
                result++;
            }
        }
        return result;
    }

    public long puzzle1(){
        long result = 0;
        Set<IntPoint> visited = new HashSet<>();
        for(int y = 0; y < grid.length; y ++){
            for(int x = 0; x < grid[0].length; x++){
                IntPoint point = new IntPoint(x,y);
                if(!visited.contains(point)){
                    long area = 0;
                    long perimeter = 0;
                    Queue<IntPoint> todo = new ArrayDeque<>();
                    char val = grid[point.getY()][point.getX()];
                    todo.add(point);
                    while(!todo.isEmpty()){
                        IntPoint p = todo.remove();
                        if(!visited.contains(p)) {
                            visited.add(p);
                            area++;
                            List<Point<Integer>> allNeighbours = p.allNeighbours();
                            List<IntPoint> neighbours = new ArrayList<>();
                            for (Point<Integer> neighbour : allNeighbours) {
                                if (isSafe((IntPoint) neighbour) && grid[neighbour.getY()][neighbour.getX()] == val) {
                                    neighbours.add((IntPoint) neighbour);
                                }
                            }
                            perimeter += (4 - neighbours.size());
                            todo.addAll(neighbours);
                        }
                    }
                    result += area * perimeter;
                }
            }
        }

        return result;
    }

    public long puzzle2(){
        long result = 0;
        Set<IntPoint> visited = new HashSet<>();
        for(int y = 0; y < grid.length; y ++){
            for(int x = 0; x < grid[0].length; x++){
                IntPoint point = new IntPoint(x,y);
                if(!visited.contains(point)){
                    long area = 0;
                    Set<IntPoint> points = new HashSet<>();
                    points.add(point);
                    Queue<IntPoint> todo = new ArrayDeque<>();
                    char val = grid[point.getY()][point.getX()];
                    todo.add(point);
                    while(!todo.isEmpty()){
                        IntPoint p = todo.remove();
                        if(!visited.contains(p)) {
                            visited.add(p);
                            area++;
                            List<Point<Integer>> allNeighbours = p.allNeighbours();
                            List<IntPoint> neighbours = new ArrayList<>();
                            for (Point<Integer> neighbour : allNeighbours) {
                                if (isSafe((IntPoint) neighbour) && grid[neighbour.getY()][neighbour.getX()] == val) {
                                    neighbours.add((IntPoint) neighbour);
                                }
                            }
                            todo.addAll(neighbours);
                            points.addAll(neighbours);
                        }
                    }
                    result += area * sides(points);
                }
            }
        }

        return result;
    }
}
