package com.ben.aoc;

import java.util.*;

public class Day17 {
    int ptr = 0;
    long a;
    long b;
    long c;
    List<Integer> input ;
    List<Integer> output = new ArrayList<>();

    public Day17(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        a = Integer.parseInt(lines.get(0).substring(12));
        b = Integer.parseInt(lines.get(1).substring(12));
        c = Integer.parseInt(lines.get(2).substring(12));
        input = new ArrayList<>();
        String[] ins = lines.get(4).substring(9).split(",");
        for (String in : ins) {
            input.add(Integer.parseInt(in));
        }
    }

    private void process(){
        switch (input.get(ptr)){
            case 0:
                a = div(input.get(ptr + 1));
                ptr += 2;
                break;
            case 1:
                b = b^input.get(ptr + 1);
                ptr += 2;
                break;
            case 2:
                b = combo(input.get(ptr + 1)) % 8;
                ptr += 2;
                break;
            case 3:
                if(a == 0){
                    ptr += 2;
                }else{
                    ptr = input.get(ptr + 1);
                }
                break;
            case 4:
                b = b^c;
                ptr += 2;
                break;
            case 5:
                output.add((int) (combo(input.get(ptr+1)) % 8));
                ptr += 2;
                break;
            case 6:
                b = div(input.get(ptr + 1));
                ptr += 2;
                break;
            case 7:
                c = div(input.get(ptr + 1));
                ptr += 2;
                break;

        }
    }

    private long combo(int value){
        return switch (value) {
            case 0, 1, 2, 3 -> value;
            case 4 -> a;
            case 5 -> b;
            case 6 -> c;
            default -> 0;
        };
    }

    private long div(int combo){
        long value = combo(combo);
        return (long) (a / (Math.pow(2,value)));
    }


    public String puzzle1(){
        StringBuilder sb = new StringBuilder();
        while(ptr + 1 < input.size()){
            process();
        }
        for(int i : output){
            sb.append(",");
            sb.append(i);
        }
        sb.deleteCharAt(0);

        return sb.toString();
    }

    public long puzzle2(){
        long result = (long) Math.pow(8, 15);
        output = new ArrayList<>();
        List<Integer> matched = new ArrayList<>();
        matched.add(input.get(input.size() - 1));
        int power = 14;

        while(!output.equals(input)){
            result += (long) Math.pow(8, power);
            output = new ArrayList<>();
            ptr = 0;
            b = 0;
            c = 0;
            a = result;
            while(ptr + 1 < input.size()){
                process();
            }

            if(output.subList(output.size() - matched.size(), output.size()).equals(matched)){
                power = Math.max(0, power - 1);
                int matchedSize = Math.min(matched.size() + 1, input.size());
                matched = input.subList(input.size() - matchedSize, input.size());
            }
        }

        return result;
    }

}
