package com.mengyingfan.android.minesweep;

/**
 * Created by mengyingfan on 9/24/17.
 */

public class Cell {
    private int mValue;
    private boolean mVisited;
    private boolean mMine;
    private boolean mMarked;
    private int mRow;
    private int mCol;

    public Cell(int value, boolean visited, boolean mine, boolean marked, int row, int col) {
        mValue = value;
        mVisited = visited;
        mMine = mine;
        mMarked = marked;
        mRow = row;
        mCol = col;
    }

    public void setmValue(int value) {
        mValue = value;
    }

    public void setmVisited(boolean visited) {
        mVisited = visited;
    }

    public void setmMine(boolean mine) {
        mMine = mine;
    }

    public int getValue(){
        return mValue;
    }

    public boolean getVisited() {
        return mVisited;
    }

    public boolean getMine() {
        return mMine;
    }

    public int getmRow() {
        return mRow;
    }

    public int getmCol() {
        return mCol;
    }

}
