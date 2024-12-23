package com.ben.aoc;

import com.ben.aoc.dijkstra.Dijkstra;

import java.util.*;

public class Day18 {
    int fall = 1024;
    static int maxSide = 70;
    List<IntPoint> bytes;
    static Set<IntPoint> fallen;

    public Day18(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        bytes = new ArrayList<>();

        for(String line : lines){
            String[] numbers = line.split(",");
            bytes.add(new IntPoint(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
        }
    }


    public Long puzzle1(){
        fallen = new HashSet<>();
        for(int i = 0; i < fall; i++){
            fallen.add(bytes.get(i));
        }

        BytesState end = (BytesState) Dijkstra.calculateShortestPath(new BytesState(new IntPoint(0,0)));

        return (long) end.getDistance();
    }

    public String puzzle2(){
        int min = 1024;
        int max = bytes.size();

        while (max - min > 1){
            int mid = (min + max) / 2;

            fallen = new HashSet<>();
            for(int i = 0; i < mid; i++){
                fallen.add(bytes.get(i));
            }

            BytesState end = (BytesState) Dijkstra.calculateShortestPath(new BytesState(new IntPoint(0,0)));

            if(end == null){
                max = mid;
            }else{
                min = mid;
            }

        }

        return bytes.get(max-1).toString();
    }

}
