package com.ben.aoc;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
    Pattern dontDo = Pattern.compile("(don't\\(\\))(.|\\n)+?((do\\(\\))|$)");
    String input;

    public Day03(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        input = String.join("", lines);
    }

    public long puzzle1(){
        return findMultipliers(input);
    }

    public long puzzle2(){
        Matcher dontDoMatcher = dontDo.matcher(input);

        return findMultipliers(dontDoMatcher.replaceAll(""));
    }

    public long findMultipliers(String in){
        long result = 0;
        Matcher matcher = pattern.matcher(in);
        while(matcher.find()){
            String[] split = matcher.group().split(",");
            int lhs = Integer.parseInt(split[0].substring(4));
            int rhs = Integer.parseInt(split[1].substring(0, split[1].length()-1));
            result += (long) lhs *rhs;
        }
        return result;
    }
}
