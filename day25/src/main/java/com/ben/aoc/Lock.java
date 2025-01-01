package com.ben.aoc;

import java.util.List;

public class Lock {
    int[] pins;

    public Lock(List<String> lock){
        pins = new int[lock.get(0).length()];
        char[][] grid = new char[lock.size()][];
        for(int i = 0; i < lock.size(); i++){
            grid[i] = lock.get(i).toCharArray();
        }

        for(int x = 0; x < grid[0].length; x++){
            for(int y = 0; y < grid.length; y++){
                if(grid[y][x] == '#') pins[x] = y;
            }
        }
    }

    public boolean keyFits(Key key){
        for(int i = 0; i < pins.length; i++){
            if(pins[i] + key.pins[i] >= 6) return false;
        }
        return true;
    }
}
