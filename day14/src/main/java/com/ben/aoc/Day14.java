package com.ben.aoc;

import java.util.*;

public class Day14 {
    List<String> lines;
    List<Robot> robots;

    public Day14(String filename){
        lines = Util.readFile(getClass(), filename);
    }

    private void init(){
        robots = new ArrayList<>();
        for(String line : lines){
            String[] split = line.split("[, ]");
            int x = Integer.parseInt(split[0].substring(2));
            int y = Integer.parseInt(split[1]);
            int velX = Integer.parseInt(split[2].substring(2));
            int velY = Integer.parseInt(split[3]);
            robots.add(new Robot(new IntPoint(x,y), new IntPoint(velX, velY)));
        }
    }

    public long puzzle1(){
        init();
        long tl = 0;
        long tr = 0;
        long bl = 0;
        long br = 0;

        for(Robot robot : robots){
            robot.move(100);
            IntPoint point = robot.getPoint();
            if(point.getX() < 50 && point.getY() < 51) tl++;
            if(point.getX() > 50 && point.getY() < 51) tr++;
            if(point.getX() < 50 && point.getY() > 51) bl++;
            if(point.getX() > 50 && point.getY() > 51) br++;
        }

        return tl * tr * bl * br;
    }

    public long puzzle2(){
        long result = 0;
        init();
        boolean tree = false;
        while(!tree){
            result ++;
            Set<IntPoint> robo = new HashSet<>();
            for(Robot robot : robots){
                robot.move();
                robo.add(robot.getPoint());
            }
            if(robo.size() == robots.size()) tree = true;
        }

        return result;
    }
}
