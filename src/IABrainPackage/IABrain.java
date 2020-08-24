package src.IABrainPackage;

public class IABrain{
    final static char EMPTY_SQUARE = '*';
    final static char PLAYER_SQUARE = 'X';
    final static char IA_SQUARE = 'O';

    static char[][] board = {{'*', '*', '*'},
                             {'*', 'O', '*'},
                             {'*', '*', '*'}};
    /*public static void main(String args[]){
        int pos = IAMove(board);
        System.out.println(pos);
    }*/
    public static int IAMove(char[][] board){
        int move = moveToCompleteLine(board, IA_SQUARE);
        if (move!=-1)
            return move;
        move = moveToCompleteLine(board, PLAYER_SQUARE);
        if (move!=-1)
            return move;
        move = otherMoves(board);
        return move;
    }
    public static int otherMoves(char[][] board){
        int[] priorityArray = {11, 31, 13, 33, 22, 12, 21, 23, 32};
        int row, col;
        for(int i=0; i< priorityArray.length; i++){
            row = (priorityArray[i]/10) - 1;
            col =priorityArray[i] - 10*(row + 1) - 1;

            if (board[row][col]==EMPTY_SQUARE)
                return priorityArray[i];
        }
        return -1;
    }
    public static int moveToCompleteLine(char[][] board, char mark){
        int row;
        int col;
        int count=0;

        for(row=0; row<3;row++){ //Complete horizontal line
            count = 0;
            for(col=0; col<3;col++){
                if(board[row][col]==mark)
                    count++;
            }
            if (count==2){
                for(col=0; col<3;col++){
                    if(board[row][col]==EMPTY_SQUARE)
                       return 10*(row+1) + (col+1); 
                }
            }

        }
        for(col=0; col<3;col++){ //Complete vertical line
            count = 0;
            for(row=0; row<3;row++){
                if(board[row][col]==mark)
                    count++;
            }
            if (count==2){
                for(row=0; row<3;row++){
                    if(board[row][col]==EMPTY_SQUARE)
                       return 10*(row+1) + (col+1); 
                }
            }

        }
        count=0;
        for(row=0; row<3; row++){ //Complete primary diagonal
            if(board[row][row]==mark){
                count++;
            }
        }
        if(count==2){
            for(row=0; row<3; row++){
                if(board[row][row]==EMPTY_SQUARE){
                    return 11*(row + 1);
                }
            }
        }
        count=0;
        for(row=0; row<3; row++){ //Complete secondary diagonal
            if(board[row][2-row]==mark){
                count++;
            }
        }
        if(count==2){
            for(row=0; row<3; row++){
                if(board[row][2-row]==EMPTY_SQUARE){
                    return 10*(row + 1) + ((2 - row) + 1);
                }
            }
        }

        return -1;
    }
}