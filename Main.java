import java.util.Scanner;
import java.util.Arrays;
//import org.apache.commons.lang3.ArrayUtils;

import src.IABrainPackage.IABrain;

public class Main{
    final static char EMPTY_SQUARE = '*';
    final static char PLAYER_SQUARE = 'X';
    final static char IA_SQUARE = 'O';

    static int[] validPositions = {11, 12, 13, 21, 22, 23, 31, 32, 33};
    static char[][] board = {{'*', '*', '*'},
                             {'*', '*', '*'},
                             {'*', '*', '*'}};
    public static void main(final String[] args) {
        System.out.println("En este juego las posiciones se indican en formato fila columna.");
        System.out.println("Las combinaciones son 11, 12, 13, 21, 22, 23, 31, 32, 33");
        final Scanner scan = new Scanner(System.in);
        printBoard(board);
        while (true) {
            playerMove(scan, board);
            printBoard(board);
            if (checkWinner(board, PLAYER_SQUARE)) {
                System.out.println("Ganaste!!!");
                break;
            }
            if(checkFullBoard(board)){
                System.out.println("Esto es un empate :p");
                break;
            }

            IAMove(board);
            printBoard(board);
            if (checkWinner(board, IA_SQUARE)) {
                System.out.println("Perdiste :(");
                break;
            }
            if(checkFullBoard(board)){
                System.out.println("Esto es un empate :p");
                break;
            }

        }
        scan.close();
    }

    public static void printBoard(final char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + "   ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static boolean checkFullBoard(final char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '*')
                    return false;
            }
        }
        return true;
    }

    public static void playerMove(final Scanner scan, final char[][] board) {
        int position;
        int row;
        int col;
        while (true) {
            System.out.print("En que posicion quiere marcar la X: ");
            try {
                position = scan.nextInt();
                final int temp = position;
                if (Arrays.stream(validPositions).anyMatch(i -> i == temp)) { // contains
                    row = ((position - 11) / 10);
                    col = (position - 11) - 10 * row;
                    if (board[row][col] == EMPTY_SQUARE)
                        break;
                    else
                        System.out.println("La casilla está ocupada, prueba otra posición!");
                } else
                    System.out.println("Las posiciones posibles son 11, 12, 13, 21, 22, 23, 31, 32, 33");
            } catch (final Exception e) {
                System.out.println("Las posiciones posibles son 11, 12, 13, 21, 22, 23, 31, 32, 33");
                scan.next();
            }
        }
        board[row][col] = PLAYER_SQUARE;
    }
    
    public static void IAMove(final char[][] board) {
        int move = IABrain.IAMove(board);
        int row = (move/10) - 1;
        int col = move - 10*(row + 1) - 1;
        board[row][col] = IA_SQUARE;
    }

    public static boolean checkWinner(final char[][] board, final char mark){
        int row;
        int col;

        for(row=0; row<3;row++){
            for(col=0; col<3;col++){
                if(board[row][col]!=mark)
                    break;
                if (col==2)
                    return true;
            }    
        }

        for(col=0; col<3;col++){
            for(row=0; row<3;row++){
                if(board[row][col]!=mark)
                    break;
                if (row==2)
                    return true;
            }    
        }


        for(row=0; row<3; row++){
            if (board[row][row]!=mark)
                break;
            if (row==2)
                return true;
        }
        for(row=0; row<3; row++){
            if (board[row][2-row]!=mark)
                break;
            if (row==2)
                return true;
        }

        return false;
    } 
    public static boolean checkTie(final char[][] board){ //Unused
        int count = 0;
        for(int row=0; row<3; row++){
            for(int col=0; col<3; col++){
                if (board[row][col]!=EMPTY_SQUARE)
                    count++;
            }
        }
        if(count==9) return true;
        else return false;
    }
}