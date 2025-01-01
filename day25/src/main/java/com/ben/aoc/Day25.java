package com.ben.aoc;

import java.util.*;

public class Day25 {
    List<Lock> locks;
    List<Key> keys;

    public Day25(String filename){
        List<String> lines = Util.readFile(getClass(), filename);
        locks = new ArrayList<>();
        keys = new ArrayList<>();
        for(int i = 0; i < lines.size(); i+=8){
            List<String> object = lines.subList(i, i+7);
            if(lines.get(i).startsWith("#")){
                locks.add(new Lock(object));
            }else{
                keys.add(new Key(object));
            }
        }
    }

    public Long puzzle1(){
        long result = 0;

        for(Lock lock : locks){
            for(Key key : keys){
                if(lock.keyFits(key)) result++;
            }
        }

        return result;
    }

    public String puzzle2(){
       return "Merry Christmas!";
    }

}
