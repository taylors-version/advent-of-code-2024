package com.ben.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day04 {
    List<String> input;

    public Day04(String filename){
        input = Util.readFile(getClass(), filename);
    }

    public long getXMAS(int x, int y){
        long result = 0;

        if(x+3 < input.get(0).length()){
            String row = "" + input.get(y).charAt(x) + input.get(y).charAt(x+1) + input.get(y).charAt(x+2) + input.get(y).charAt(x+3);
            if(row.equals("XMAS")) result ++;
        }
        if(x-3 >= 0){
            String row = "" + input.get(y).charAt(x) + input.get(y).charAt(x-1) + input.get(y).charAt(x-2) + input.get(y).charAt(x-3);
            if(row.equals("XMAS")) result ++;
        }
        if(y+3 < input.size()){
            String column = "" + input.get(y).charAt(x) + input.get(y+1).charAt(x) + input.get(y+2).charAt(x) + input.get(y+3).charAt(x);
            if(column.equals("XMAS")) result ++;
        }
        if(y-3 >= 0){
            String column = "" + input.get(y).charAt(x) + input.get(y-1).charAt(x) + input.get(y-2).charAt(x) + input.get(y-3).charAt(x);
            if(column.equals("XMAS")) result ++;
        }
        if(x+3 < input.get(0).length() && y+3 < input.size()){
            String row = "" + input.get(y).charAt(x) + input.get(y+1).charAt(x+1) + input.get(y+2).charAt(x+2) + input.get(y+3).charAt(x+3);
            if(row.equals("XMAS")) result ++;
        }
        if(x+3 < input.get(0).length() && y-3 >= 0){
            String row = "" + input.get(y).charAt(x) + input.get(y-1).charAt(x+1) + input.get(y-2).charAt(x+2) + input.get(y-3).charAt(x+3);
            if(row.equals("XMAS")) result ++;
        }
        if(x-3 >= 0 && y+3 < input.size()){
            String row = "" + input.get(y).charAt(x) + input.get(y+1).charAt(x-1) + input.get(y+2).charAt(x-2) + input.get(y+3).charAt(x-3);
            if(row.equals("XMAS")) result ++;
        }
        if(x-3 >= 0 && y-3 >= 0){
            String row = "" + input.get(y).charAt(x) + input.get(y-1).charAt(x-1) + input.get(y-2).charAt(x-2) + input.get(y-3).charAt(x-3);
            if(row.equals("XMAS")) result ++;
        }

        return result;
    }

    public long puzzle1(){
        long result = 0;

        for(int y = 0; y < input.size(); y++){
            String yString = input.get(y);
            for(int x = 0; x < yString.length(); x++){
                if(yString.charAt(x) == 'X'){
                    result += getXMAS(x, y);
                }
            }
        }

        return result;
    }

    public long puzzle2(){
        long result = 0;
        List<String> allowed = Arrays.asList("MMSS", "SSMM", "MSSM", "SMMS");

        for(int y = 1; y < input.size()-1; y++){
            String yString = input.get(y);
            for(int x = 1; x < yString.length()-1; x++){
                if(yString.charAt(x) == 'A'){
                    String test = "" + input.get(y-1).charAt(x-1) + input.get(y-1).charAt(x+1) + input.get(y+1).charAt(x+1) + input.get(y+1).charAt(x-1);
                    if(allowed.contains(test)) result++;
                }
            }
        }

        return result;
    }
}
