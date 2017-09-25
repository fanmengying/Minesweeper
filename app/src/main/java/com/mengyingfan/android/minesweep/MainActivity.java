package com.mengyingfan.android.minesweep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout boardlayout = (GridLayout) findViewById(R.id.gridlayout);
        int row = 9;
        int col = 9;
        boardlayout.setColumnCount(col);
        boardlayout.setRowCount(row);
        //initialize board
        int numCell = row * col;
        int numMine = 10;
        int unvisited = numCell;
        Cell[][] board = new Cell[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = new Cell(0, false, false, false, i, j);
            }
        }
        updateUI(board);
        //random, simulate user choice, pick one cell
        Random r = new Random();
        int clickedRow = r.nextInt(9);
        int clickedCol = r.nextInt(9);
        board[clickedRow][clickedCol].setmVisited(true);
        unvisited -= 1;
        updateUI(board);
        //set up a board based on user's first click and update mine counter on board
        initBoard(board, clickedRow, clickedCol, numMine, row, col);
        //initialize mine and update cell value
        do {
            VisitBoard(board, clickedRow, clickedCol, row, col, unvisited);
            updateUI(board);
            // user click and update clickedRow and clickedCol;
        } while (unvisited > numMine && board[clickedRow][clickedCol].getMine() == false);
        if (unvisited == numMine) {
            TextView result = (TextView) findViewById(R.id.result);
            result.setText("Congratulations");
        } else {
            TextView result = (TextView) findViewById(R.id.result);
            result.setText("Sorry");
        }
    }

    void updateUI(Cell[][] board) {

    }
    void initBoard(Cell[][] board, int randomRow, int randomCol, int numMine, int rowlimit, int collimit) {
        Random r = new Random();
        int i = 0;
        while (i < numMine) {
            int row = r.nextInt(rowlimit);
            int col = r.nextInt(collimit);
            if (row == randomRow && col == randomCol) {
                continue;
            } else if (board[row][col].getMine() == true) {
                continue;
            }else {
                board[row][col].setmMine(true);
                i += 1;
            }
        }
        for (int j = 0; j < rowlimit; j++) {
            for (int k = 0; k < collimit; k++) {
                int mineCount = 0;
                if (j - 1 >= 0) {
                    if (k - 1 >= 0 && board[j-1][k-1].getMine() == true) {
                        mineCount += 1;
                    }
                    if (k + 1 < collimit && board[j-1][k+1].getMine() == true) {
                        mineCount += 1;
                    }
                    if (board[j-1][k].getMine() == true) {
                        mineCount += 1;
                    }
                }
                if (j + 1 < rowlimit) {
                    if (k - 1 >= 0 && board[j+1][k-1].getMine() == true) {
                        mineCount += 1;
                    }
                    if (k + 1 < collimit && board[j+1][k+1].getMine() == true) {
                        mineCount += 1;
                    }
                    if (board[j+1][k].getMine() == true) {
                        mineCount += 1;
                    }
                }
                if (k - 1 >= 0 && board[j][k-1].getMine() == true) {
                    mineCount += 1;
                }
                if (k + 1 < collimit && board[j][k+1].getMine() == true) {
                    mineCount += 1;
                }
                board[j][k].setmValue(mineCount);
            }
        }

    }

    void VisitBoard(Cell[][] board, int row, int col, int rowLimit, int colLimit, int unvisited) {
        Stack<Cell> stack = new Stack();
        stack.push(board[row][col]);
        while (!stack.empty()) {
            Cell cur = stack.pop();
            int curRow = cur.getmRow();
            int curCol = cur.getmCol();
            if (curRow - 1 >= 0 ) {
                if (board[curRow - 1][curCol].getVisited() == false && board[curRow - 1][curCol].getMine() != true) {
                    stack.push(board[curRow - 1][curCol]);
                    board[curRow - 1][curCol].setmVisited(true);
                    unvisited -= 1;
                }
            }

            if (curRow + 1 < rowLimit) {
                if (board[curRow + 1][curCol].getVisited() == false && board[curRow + 1][curCol].getMine() != true) {
                    stack.push(board[curRow + 1][curCol]);
                    board[curRow + 1][curCol].setmVisited(true);
                    unvisited -= 1;
                }
            }

            if (curCol + 1 < colLimit) {
                if (board[curRow][curCol + 1].getVisited() == false && board[curRow ][curCol + 1].getMine() != true) {
                    stack.push(board[curRow][curCol + 1]);
                    board[curRow][curCol + 1].setmVisited(true);
                    unvisited -= 1;
                }
            }
            if (curCol - 1 >= 0) {
                if (board[curRow][curCol - 1].getVisited() == false && board[curRow ][curCol - 1].getMine() != true) {
                    stack.push(board[curRow][curCol - 1]);
                    board[curRow][curCol - 1].setmVisited(true);
                    unvisited -= 1;
                }
            }
        }

    }
}
