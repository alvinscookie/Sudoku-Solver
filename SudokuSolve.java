//3rd attempt at making a sudoku solver

public class SudokuSolve {

    public boolean solve(int[][] board){
        if(solvingAlgorithm(board, 0, 0)){
            System.out.println("Possible solution: ");
            printBoard(board);
            return true;
        }else{
            System.out.println("No solution.");
            return false;
        }

    }

    public boolean solvingAlgorithm(int[][] board, int row, int col){
        //base case: board is solved
        if(row == 8 && col==9){
            col=8;
            printBoard(board);
            return true;
        }

        if(col==9){//moves forward next row
            col=0;
            row++;
        }
        if(board[row][col] != 0){
            return solvingAlgorithm(board, row, col+1);
        }

        for(int x = 1; x<10; x++){
            if(bounds(board, row, col, x)){
                board[row][col] = x;
                if(solvingAlgorithm(board, row, col+1)){
                    return true;
                }
            }
            board[row][col] = 0;
        }
        return false;
    }


    public boolean bounds (int[][] board, int row, int col, int x){
        boolean boundCheck = true;
        
        //checks row
        for(int i=0;i<9;i++){
            if(board[i][col]== x &&  i != row){
                boundCheck = false;
            }
        }
        //checks col
        for(int j=0;j<9;j++){
            if(board[row][j] == x && j !=col){
                boundCheck = false;
            }
        }

        //checks 3x3 square
        for(int ii=0;ii<3;ii++){
            for(int jj = 0; jj<3; jj++){
                if(board[((row/3)*3)+ii][((col/3)*3)+jj] == x  && row !=((row/3)*3)+ii&&col!=((col/3)*3)+jj){
                    boundCheck = false;
                }
            }
        }
        return boundCheck;//note: does not fail check if other cells are 0 (unknown/unchecked);
    }
    public void printBoard(int[][] board){
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                System.out.print(board[i][j] +" ");                 
            }
            System.out.println("");
        }
        System.out.println("");
    }
    public static void main (String[] args){
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


            SudokuSolve testSolve = new SudokuSolve();
            testSolve.solve(testBoard);
            
    }

}
