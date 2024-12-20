package com.ben.aoc;

import org.javatuples.Pair;

import java.util.*;

public class Day15 {
    List<String> lines;
    String instructions = "";
    List<IntPoint> walls = new ArrayList<>();
    List<Block> wallsBlocks = new ArrayList<>();
    List<IntPoint> boxes = new ArrayList<>();
    List<Block> boxesBlocks = new ArrayList<>();
    IntPoint robot = new IntPoint(0,0);
    Block robotBlock = new Block(robot, robot);


    public Day15(String filename){
        lines = Util.readFile(getClass(), filename);
    }

    private void parseInput(){
        for(int y = 0; y < lines.size(); y++){
            String line = lines.get(y);
            if(line.startsWith("#")){
                for(int x = 0; x < line.length(); x++){
                    char c = line.charAt(x);
                    switch (c) {
                        case '#':
                            walls.add(new IntPoint(x, y));
                            break;
                        case 'O':
                            boxes.add(new IntPoint(x, y));
                            break;
                        case '@':
                            robot = new IntPoint(x, y);
                    }
                }
            }else{
                instructions += line;
            }
        }
    }

    private void parseInputWide(){
        instructions = "";
        for(int y = 0; y < lines.size(); y++){
            String line = lines.get(y);
            if(line.startsWith("#")){
                for(int x = 0; x < line.length(); x++){
                    int xVal = x*2;
                    char c = line.charAt(x);
                    IntPoint lhs = new IntPoint(xVal, y);
                    IntPoint rhs = new IntPoint(xVal + 1, y);
                    switch (c) {
                        case '#':
                            wallsBlocks.add(new Block(lhs, rhs));
                            break;
                        case 'O':
                            boxesBlocks.add(new Block(lhs, rhs));
                            break;
                        case '@':
                            robotBlock = new Block(lhs, lhs);
                    }
                }
            }else{
                instructions += line;
            }
        }
    }

    private void move(Direction d){
        List<IntPoint> boxesToMove = new ArrayList<>();
        IntPoint check = (IntPoint) robot.getByDirection(d);
        while(boxes.contains(check)){
            boxesToMove.add(check);
            check = (IntPoint) check.getByDirection(d);
        }
        if(walls.contains(check)) return;
        for(IntPoint box : boxesToMove){
            boxes.remove(box);
            boxes.add((IntPoint) box.getByDirection(d));
        }
        robot = (IntPoint) robot.getByDirection(d);
    }

    private void moveWide(Direction d){
        for(Block wall : wallsBlocks){
            if(wall.doesHit(robotBlock, d)) return;
        }

        Pair<Set<Block>, Boolean> boxesToMove = boxesToMove(robotBlock, d);
        if(!boxesToMove.getValue1()) {
            for (Block box : boxesToMove.getValue0()) {
                boxesBlocks.remove(box);
                boxesBlocks.add(box.move(d));
            }


            robotBlock = robotBlock.move(d);
        }
    }

    private Pair<Set<Block>, Boolean> boxesToMove(Block block, Direction d){
        Set<Block>boxesToMove = new HashSet<>();
        boolean hitWall = false;
        for(Block wall : wallsBlocks){
            if(wall.doesHit(block, d)) return new Pair<>(boxesToMove, true);
        }

        for(Block box : boxesBlocks){
            if(!box.equals(block) && box.doesHit(block, d)){
                boxesToMove.add(box);
                Pair<Set<Block>, Boolean> subBoxes = boxesToMove(box, d);
                boxesToMove.addAll(subBoxes.getValue0());
                if(subBoxes.getValue1()) hitWall = true;
            }
        }

        return new Pair<>(boxesToMove, hitWall);
    }

    public long puzzle1(){
        parseInput();
        for(char i : instructions.toCharArray()){
            move(new Direction(i));
        }
        long result = 0;
        for(IntPoint box : boxes){
            result += box.getX() + box.getY() * 100;
        }

        return result;
    }

    public long puzzle2(){
        parseInputWide();
        for(char i : instructions.toCharArray()){
            moveWide(new Direction(i));
        }
        long result = 0;
        for(Block box : boxesBlocks){
            result += box.lhs.getX() + box.lhs.getY() * 100;
        }
        return result;
    }

}
