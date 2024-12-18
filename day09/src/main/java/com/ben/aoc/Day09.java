package com.ben.aoc;

import java.util.*;

public class Day09 {
    String input;

    public Day09(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        input = lines.get(0);
    }

    public List<String> getDisk(){
        List<String> disk = new ArrayList<>();
        for (int i = 0; i < input.length(); i++){
            int value = input.charAt(i) - 48;
            if(i%2==0){
                for(int j = 0; j < value; j++){
                    disk.add("" + (i/2));
                }
            }else{
                for(int j = 0; j < value; j++){
                    disk.add(".");
                }
            }
        }
        return disk;
    }

    public List<String> defrag(List<String> disk){
        List<String> defragged = new ArrayList<>();
        for(int i = 0; i < disk.size(); i++){
            String value = disk.get(i);
            if(!value.equals(".")){
                defragged.add(value);
            }else{
                int j = lastDigit(disk);
                if (j < i){
                    break;
                }else{
                    value = disk.get(j);
                    defragged.add(value);
                    disk.remove(j);
                }
            }
        }

        return defragged;
    }

    private int lastDigit(List<String> disk){
        int lastDigit = 0;
        for(int i = disk.size() - 1; i >= 0; i--){
            if(!disk.get(i).equals(".")){
                lastDigit = i;
                break;
            }
        }
        return lastDigit;
    }

    private List<String> blockDefrag(List<String> disk){
        Set<String> sorted = new HashSet<>();

        for(int i = disk.size() - 1; i >= 0; i--){
            String value = disk.get(i);
            if(!value.equals(".") && !sorted.contains(value)) {
                sorted.add(value);
                int sizeOfBlock = getSizeOfBlock(i, disk);
                int firstFreeBlock = getIndexOfEmptyBlock(sizeOfBlock, disk);
                if (firstFreeBlock < i) {
                    for (int j = 0; j < sizeOfBlock; j++) {
                        disk.set(i - j, ".");
                        disk.set(firstFreeBlock + j, value);
                    }
                }
            }
        }


        return disk;
    }

    private int getSizeOfBlock(int index, List<String> disk){
        int size = 1;
        String val = disk.get(index);
        for(int i = index-1; i>=0; i--){
            if(!disk.get(i).equals(val)){
                break;
            }
            size++;
        }
        return size;
    }

    private int getIndexOfEmptyBlock(int blockSize, List<String> disk){
        int index = disk.size();
        for(int i =0; i<disk.size(); i++){
            boolean free = true;
            for(int j= 0; j<blockSize; j++){
                if (i + j >= disk.size() || !disk.get(i + j).equals(".")) {
                    free = false;
                    break;
                }
            }
            if (free) return i;
        }
        return index;
    }

    public long puzzle1(){
        List<String> defragged = defrag(getDisk());
        long result = 0;
        for(int i = 0; i < defragged.size(); i++){
            String string = defragged.get(i);
            if(string.equals(".")){
                break;
            }
            int value = Integer.parseInt(string);
            result += (long) value * i;
        }

        return result;
    }

    public long puzzle2(){
        List<String> defragged = blockDefrag(getDisk());

        long result = 0;
        for(int i = 0; i < defragged.size(); i++){
            String string = defragged.get(i);
            if(!string.equals(".")){
                int value = Integer.parseInt(string);
                result += (long) value * i;
            }
        }

        return result;
    }
}
