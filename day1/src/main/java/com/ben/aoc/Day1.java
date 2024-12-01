package com.ben.aoc;

import java.util.List;
import java.util.ArrayList;

public class Day1 {
	
	List<String> lines;
	
	public Day1(String fileName) {
		lines = Util.readFile(getClass(), fileName);
	}

	
	public long puzzle2() {
		long result = 0;
		List<Long> lhs = new ArrayList<Long>();
		List<Long> rhs = new ArrayList<Long>();
		
		for(String l : lines) {
			String[] split = l.split("   ");
			lhs.add(Long.parseLong(split[0]));
            rhs.add(Long.parseLong(split[1]));
		}

        for(Long l : lhs){
            int count = 0;
            for(Long r : rhs){
                if(r.equals(l)){
                    count++;
                }
            }
            result+=count*l;
        }
		
		return result;
	}
	
	
}
