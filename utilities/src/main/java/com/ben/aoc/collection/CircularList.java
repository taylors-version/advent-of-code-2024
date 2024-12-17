package com.ben.aoc.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class CircularList<E> extends ArrayList<E> {

    public CircularList(){
        super();
    }

    public CircularList(List<E> list){
        super();
        this.addAll(0, list);
    }

    private int getIndex(int index){
        if (index == 0 || index < this.size()) {
            return index;
        }
        return index % this.size();
    }

    public E getOpposite(int index, boolean roundLeft){
        int offset = (roundLeft ? (this.size() / 2) : (int) Math.ceil((double) this.size() / 2));
        int getIndex = index + offset;

        return this.get(getIndex);
    }

    @Override
    public void add(int index, E element){
        super.add(getIndex(index), element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c){
        return super.addAll(getIndex(index), c);
    }

    @Override
    public E get(int index){
        return super.get(index % this.size());
    }

    @Override
    public ListIterator<E> listIterator(int index){
        return super.listIterator(index % this.size());
    }

    @Override
    public E remove(int index){
        return super.remove(index % this.size());
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex){
        int from = fromIndex % this.size();
        int to = toIndex % this.size();
        super.removeRange(Math.min(from, to), Math.max(from, to));
    }

    @Override
    public E set(int index, E element){
        return super.set(index & this.size(), element);
    }

    @Override
    public CircularList<E> subList(int fromIndex, int toIndex){
        int from = fromIndex % this.size();
        int to = toIndex % this.size();

        if (from <= to) {
            return new CircularList<>(super.subList(from, to));
        }else{
            List<E> result = new CircularList<E>();
            for(int i = from; i<this.size(); i++){
                result.add(get(i));
            }
            for(int i = 0; i<to; i++){
                result.add(get(i));
            }
            return new CircularList<>(result);
        }
    }

}
