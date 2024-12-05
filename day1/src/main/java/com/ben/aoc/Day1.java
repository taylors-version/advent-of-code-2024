package com.ben.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Day1 {
	char[][] wordSearch;
	
	public Day1(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		wordSearch = new char[lines.size()][];
		for(int i = 0; i < lines.size(); i++){
			wordSearch[i] = lines.get(i).toCharArray();
		}
	}

	public long puzzle1(){
		long result = 0;
		for(int i = 0; i < wordSearch.length; i++){
			for(int j = 0; j < wordSearch[i].length; j++){
				List<String> words = neighbours(i, j);
				for(String word : words){
					if(word.equals("XMAS")) result++;
				}
			}
		}
		return result;
	}

	public List<String> neighbours(int i, int j){
		List<String> neighbours = new ArrayList<>();
		if(j+3 < wordSearch[i].length)
			neighbours.add("" + wordSearch[i][j] + wordSearch[i][j+1] + wordSearch[i][j+2] + wordSearch[i][j+3]);
		if(j-3 >= 0)
			neighbours.add("" + wordSearch[i][j] + wordSearch[i][j-1] + wordSearch[i][j-2] + wordSearch[i][j-3]);
		if(i+3 < wordSearch.length)
			neighbours.add("" + wordSearch[i][j] + wordSearch[i+1][j] + wordSearch[i+2][j] + wordSearch[i+3][j]);
		if(i-3 >= 0)
			neighbours.add("" + wordSearch[i][j] + wordSearch[i-1][j] + wordSearch[i-2][j] + wordSearch[i-3][j]);
		if(j+3 < wordSearch[i].length && i+3 < wordSearch.length)
			neighbours.add("" + wordSearch[i][j] + wordSearch[i+1][j+1] + wordSearch[i+2][j+2] + wordSearch[i+3][j+3]);
		if(j-3 >= 0 && i+3 < wordSearch.length)
			neighbours.add("" + wordSearch[i][j] + wordSearch[i+1][j-1] + wordSearch[i+2][j-2] + wordSearch[i+3][j-3]);
		if(j+3 < wordSearch[i].length && i-3 >= 0)
			neighbours.add("" + wordSearch[i][j] + wordSearch[i-1][j+1] + wordSearch[i-2][j+2] + wordSearch[i-3][j+3]);
		if(j-3 >= 0 && i-3 >= 0)
			neighbours.add("" + wordSearch[i][j] + wordSearch[i-1][j-1] + wordSearch[i-2][j-2] + wordSearch[i-3][j-3]);
		return neighbours;
	}



	
	public long puzzle2() {
		long result = 0;
		List<String> valid = Arrays.asList("MMSS", "SSMM", "MSSM", "SMMS");

		for(int i = 1; i < wordSearch.length - 1; i++){
			for(int j = 1; j < wordSearch.length - 1; j++){
				if(wordSearch[i][j] == 'A') {
					String corners = "" + wordSearch[i-1][j-1] + wordSearch[i-1][j+1] + wordSearch[i+1][j+1] + wordSearch[i+1][j-1];
					if (valid.contains(corners)) result ++;
				}
			}
		}

		return result;
	}
	
	
}
