package com.ben.aoc;

import org.javatuples.Pair;

import java.util.*;

public class Day13 {
    List<LinearEqs> linearEqs;

    public Day13(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        linearEqs = new ArrayList<>();
        for(int i = 0; i<lines.size(); i+=4){
            String aLine = lines.get(i);
            int firstPlus = aLine.indexOf('+');
            int firstComma = aLine.indexOf(',');
            int ax = Integer.parseInt(aLine.substring(firstPlus + 1, firstComma));
            int secondPlus = aLine.substring(firstComma).indexOf('+') + firstComma;
            int ay = Integer.parseInt(aLine.substring(secondPlus + 1));
            String bLine = lines.get(i+1);
            firstPlus = bLine.indexOf('+');
            firstComma = bLine.indexOf(',');
            int bx = Integer.parseInt(bLine.substring(firstPlus + 1, firstComma));
            secondPlus = bLine.substring(firstComma).indexOf('+') + firstComma;
            int by = Integer.parseInt(bLine.substring(secondPlus + 1));
            String pLine = lines.get(i+2);
            int firstEqual = pLine.indexOf('=');
            firstComma = pLine.indexOf(',');
            int px = Integer.parseInt(pLine.substring(firstEqual + 1, firstComma));
            int secondEqual = pLine.substring(firstComma).indexOf('=') + firstComma;
            int py = Integer.parseInt(pLine.substring(secondEqual + 1));
            LinearEqs linearEq = new LinearEqs(ax, ay, bx, by, px, py);
            linearEqs.add(linearEq);
        }
    }


    public long puzzle1(){
        long result = 0;
        for(LinearEqs linearEq : linearEqs){
            double solution = linearEq.solution();
            if(solution % 1 == 0){
                result += (long) solution;
            }
        }

        return result;
    }

    public long puzzle2(){
        long result = 0;
        for(LinearEqs linearEq : linearEqs){
            double solution = linearEq.solutionFar();
            if(solution % 1 == 0){
                result += (long) solution;
            }
        }
        return result; //73842584327185 too high
    }
}
