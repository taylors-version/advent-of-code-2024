package com.ben.aoc;

public class Robot {
    private IntPoint point;
    private final IntPoint velocity;

    public Robot(IntPoint point, IntPoint velocity) {
        this.point = point;
        this.velocity = velocity;
    }

    public IntPoint getPoint(){
        return point;
    }

    public IntPoint getVelocity(){
        return velocity;
    }

    public void move(){
        int x = (point.getX() + velocity.getX());
        if(x > 100) x-=101;
        if(x < 0) x+=101;
        int y = (point.getY() + velocity.getY());
        if(y > 102) y-=103;
        if(y < 0) y+=103;
        point = new IntPoint(x,y);
    }

    public void move(int number){
        for(int i = 0; i < number; i++){
            move();
        }
    }
}
