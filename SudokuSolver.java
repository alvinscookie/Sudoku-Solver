//SudokuSolver
//First attempt
//Assumes the board has a unique solution
//inputs a 2d array and outputs a solution
//see link below for advanced algorithms
//https://www.pennydellpuzzles.com/wp-content/uploads/2019/03/How-to-Solve-Sudoku.pdf
//only solves basic/direct solving puzzles
//Created by Alvin Chiu

import java.util.*;
import java.io.*;
import java.lang.Math;

public class SudokuSolver {

    private int[][] solvingBoard = new int[9][9];//confirmed cell int
    private int[][][] possibleBoard = new int[9][9][9];//all possible numbers on an empty cell
    private boolean[][] solvedBoard = new boolean[9][9];//confirmed cell boolean, makes it easier to confirm cell
    private int[][] inputBoard = new int[9][9];//used for initializing the board
    public SudokuSolver(int[][] initialBoard){

        inputBoard = initialBoard;
        for(int i=0; i<9;i++){
            for(int j=0;j<9;j++){
                solvingBoard[i][j] = 0;
                solvedBoard[i][j] = false;
                for(int k=0;k<9;k++){
                    //sets uncertain positions to false == unknown
                    //sets uncertain positions to all possible numbers
                    possibleBoard[i][j][k] = k+1;
                }
            }
        }

        //inital board setup
        for(int i=0; i<9;i++){
            for(int j=0;j<9;j++){
                if(solvingBoard[i][j] != inputBoard[i][j]){
                    solvingBoard[i][j] = inputBoard[i][j];
                    cellUpdate(i,j, solvingBoard[i][j]-1);
                    solvedBoard[i][j] = true;
                    for(int k=0;k<9;k++){
                        possibleBoard[i][j][k] = 0;//sets all posibilities for initial known cells to 0
                    }
                }
            }
        }
        System.out.println("Starting Board: ");
        printBoard();
    }

    public void sudokuSolver(){
        boolean updated = true;
        int realOne = 0;
        int numPossible = 0;

        //solving algorithm
        while(updated == true){
            updated = false;
            
            
            for(int i=0; i<9;i++){
                for(int j=0;j<9;j++){
                    numPossible = 0;
                    realOne = 0;
                    if(solvedBoard[i][j] == false){
                        //System.out.print("("+i+","+j+")");
                        for(int k=0;k<9;k++){
                            //System.out.print(possibleBoard[i][j][k]);
                            if(possibleBoard[i][j][k] != 0){
                                numPossible+=1;
                                realOne = possibleBoard[i][j][k];
                                //System.out.print(numPossible);
                            }
                        }//System.out.println();
                        if(numPossible == 1){
                            //System.out.print("("+i+","+j+")"+realOne+"\n");
                            solvingBoard[i][j] = realOne;
                            solvedBoard[i][j] = true;
                            cellUpdate(i,j,realOne-1);
                            updated = true;
                            //System.out.println("updated");
                        }
                    }
                }
            }
            printBoard();
        }
        System.out.println("Final Board: ");
        printBoard();
    }

    private void cellUpdate(int i, int j, int k){

        //the first three are basic direct solving
        //rows
        for(int row=0; row<9;row++){
            possibleBoard[row][j][k] = 0;
        }

        //columns
        for(int column=0;column<9;column++){
            possibleBoard[i][column][k] = 0;
        }

        //3x3 cubes
        for(int ii=0;ii<3;ii++){
            for(int jj = 0; jj<3; jj++){
                possibleBoard[((i/3)*3)+ii][((j/3)*3)+jj][k] = 0;
            }
        }
    }
   
    public void printBoard(){
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                System.out.print(solvingBoard[i][j] +" ");                 
            }
            System.out.println("");
        }
        System.out.println("");
    }


    public static void main(String[] args){
        //0 is used for empty or unknown values

        //easy board test
        int[][] testBoard ={
        {7,0,4,0,0,0,0,3,9,},
        {5,1,0,0,0,3,0,0,6,},
        {0,0,0,7,9,1,5,4,2,},
        {0,7,8,6,0,9,4,5,0,},
        {0,4,3,0,0,8,2,0,0,},
        {0,5,2,0,7,0,0,0,8,},
        {2,3,0,5,0,0,0,0,0,},
        {0,9,0,0,0,7,0,2,1,},
        {4,0,7,9,0,0,3,0,0,}};

        SudokuSolver board = new SudokuSolver(testBoard);
        board.sudokuSolver();

    }
}
 
