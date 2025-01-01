package com.ben.aoc;

import java.util.List;

public class Key {
    int[] pins;

    public Key(List<String> key){
        pins = new int[key.get(0).length()];
        char[][] grid = new char[key.size()][];
        for(int i = 0; i < key.size(); i++){
            grid[i] = key.get(key.size() - (i+1)).toCharArray();
        }

        for(int x = 0; x < grid[0].length; x++){
            for(int y = 0; y < grid.length; y++){
                if(grid[y][x] == '#') pins[x] = y;
            }
        }
    }
}
