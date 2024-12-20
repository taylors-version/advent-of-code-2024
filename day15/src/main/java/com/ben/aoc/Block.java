package com.ben.aoc;

public class Block {
    protected IntPoint lhs;
    protected IntPoint rhs;

    public Block(IntPoint lhs, IntPoint rhs){
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public boolean doesHit(Block block, Direction dir){
        IntPoint blockLhs = (IntPoint) block.lhs.getByDirection(dir);
        IntPoint blockRhs = (IntPoint) block.rhs.getByDirection(dir);

        return blockLhs.equals(lhs) || blockLhs.equals(rhs) || blockRhs.equals(rhs) || blockRhs.equals(lhs);
    }

    public Block move (Direction d){
        return new Block((IntPoint) lhs.getByDirection(d), (IntPoint) rhs.getByDirection(d));
    }

}
