package com.ben.aoc;

import org.javatuples.Pair;

import java.util.*;

public class Day08 {
    Map<Character, Set<IntPoint>> antennas;
    int xMax;
    int yMax;

    public Day08(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        antennas = new HashMap<>();
        yMax = lines.size();
        xMax = lines.get(0).length();
        for(int y = 0; y < yMax; y++){
            String line = lines.get(y);
            for(int x = 0; x < xMax; x++){
                char c = line.charAt(x);
                if(c != '.') {
                    Set<IntPoint> points = antennas.getOrDefault(c, new HashSet<>());
                    points.add(new IntPoint(x, y));
                    antennas.put(c, points);
                }
            }
        }
    }

    public Set<Pair<IntPoint, IntPoint>> getPairs(Set<IntPoint> points){
        List<IntPoint> list = points.stream().toList();
        Set<Pair<IntPoint, IntPoint>> pairs = new HashSet<>();

        for(int i = 0; i < list.size() - 1; i++){
            IntPoint a = list.get(i);
            for(int j = i+1; j < list.size(); j++){
                pairs.add(new Pair<>(a, list.get(j)));
            }
        }

        return pairs;
    }

    public boolean isInGrid(IntPoint point){
        int x = point.getX();
        int y = point.getY();
        return x >= 0 && x < xMax && y >= 0 && y<yMax;
    }

    public long puzzle1(){
        Set<IntPoint> antinodes = new HashSet<>();
        for(Set<IntPoint> vals : antennas.values()){
            for(Pair<IntPoint, IntPoint> p : getPairs(vals).stream().toList()){
                IntPoint a = p.getValue0();
                IntPoint b = p.getValue1();

                int xDiff = a.getX() - b.getX();
                int yDiff = a.getY() - b.getY();

                IntPoint ab = new IntPoint(a.getX() + xDiff, a.getY() + yDiff);
                IntPoint ba = new IntPoint(b.getX() - xDiff, b.getY() - yDiff);

                if(isInGrid(ab)) antinodes.add(ab);
                if(isInGrid(ba)) antinodes.add(ba);
            }
        }

        return antinodes.size();
    }

    public long puzzle2(){
        Set<IntPoint> antinodes = new HashSet<>();
        for(Set<IntPoint> vals : antennas.values()){
            for(Pair<IntPoint, IntPoint> p : getPairs(vals).stream().toList()){
                IntPoint a = p.getValue0();
                IntPoint b = p.getValue1();

                int xDiff = a.getX() - b.getX();
                int yDiff = a.getY() - b.getY();

                int x = a.getX();
                int y = a.getY();
                while(x>=0 && y>=0 && x<xMax && y<yMax){
                    IntPoint point = new IntPoint(x, y);
                    antinodes.add(point);
                    x = x - xDiff;
                    y = y - yDiff;
                }
                x = a.getX();
                y = a.getY();
                while (x>=0 && y>=0 && x<xMax && y<yMax){
                    IntPoint point = new IntPoint(x, y);
                    antinodes.add(point);
                    x = x + xDiff;
                    y = y + yDiff;
                }
            }
        }

        return antinodes.size();
    }
}
