import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class piskvorky{

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[]args) {
       char [][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
       {'_', '+', '_', '+', '_'}, 
       {' ', '|', ' ', '|', ' '}, 
       {'_', '+', '_', '+', '_'},
       {' ', '|', ' ', '|', ' '}};
       printgameBoard(gameBoard);
       while(true) {
        Scanner sc = new Scanner(System.in);
        System.out.println("(X)Zadej cislo policka ktere chces zabrat(1-9):");
        int playerPos = sc.nextInt();
        
        PlacePiece(gameBoard, playerPos, "player1");
        String result = checkWinner();
        if(result.length()> 0) {
            System.out.println(result);
            printgameBoard(gameBoard);
            break;
        }
        printgameBoard(gameBoard);
        System.out.println("(O)Zadej cislo policka ktere chces zabrat(1-9):");
         int cpuPos = sc.nextInt();
        PlacePiece(gameBoard, cpuPos, "player2");
        
        printgameBoard(gameBoard);

        result = checkWinner();
        if(result.length()> 0) {
            System.out.println(result);
            break;
        }
        
        
       }
}

private static void printgameBoard(char[][] gameBoard) {
    for(char[] row : gameBoard) {
        for(char c : row) {
            System.out.print(c);
        }
        System.out.println();
    }
}
public static void PlacePiece(char[][] gameBoard, int pos, String user) {
    char symbol = ' ';
    if(user.equals("player1")) {
        symbol = 'X';
        playerPositions.add(pos);
    } else if (user.equals("player2")){
        symbol = 'O';
        cpuPositions.add(pos);
    }

    switch(pos) {
        case 1:
        gameBoard[0][0] = symbol;
        break;
        case 2:
        gameBoard[0][2] = symbol;
        break;
        case 3:
        gameBoard[0][4] = symbol;
        break;
        case 4:
        gameBoard[2][0] = symbol;
        break;
        case 5:
        gameBoard[2][2] = symbol;
        break;
        case 6:
        gameBoard[2][4] = symbol;
        break;
        case 7:
        gameBoard[4][0] = symbol;
        break;
        case 8:
        gameBoard[4][2] = symbol;
        break;
        case 9:
        gameBoard[4][4] = symbol;
        break;
        default:
        System.out.println("Cislo nepatri mezi moznosti pozice");
        break;
    }
}
private static String checkWinner() {
    List topRow = Arrays.asList(1, 2, 3);
    List middleRow = Arrays.asList(4, 5, 6);
    List bottomRow = Arrays.asList(7, 8, 9);
    List leftCol = Arrays.asList(1, 4, 7);
    List middleCol = Arrays.asList(2, 5, 8);
    List rightCol = Arrays.asList(3, 6, 9);
    List cross1 = Arrays.asList(1, 5, 9);
    List cross2 = Arrays.asList(3, 5, 7);

    List<List> winning = new ArrayList<List>();
    winning.add(topRow);
    winning.add(middleRow);
    winning.add(bottomRow);
    winning.add(leftCol);
    winning.add(middleCol);
    winning.add(rightCol);
    winning.add(cross1);
    winning.add(cross2);
    
    for (List l : winning) 
        if(playerPositions.containsAll(l)) {
            return "Vyhral Player1!";
        } else if(cpuPositions.containsAll(l)) {
            return "Vyhral Player2!";
        } else if(playerPositions.size()+ cpuPositions.size() == 9){
            return "Vsechna policka jsou zabrana! Nikdo nevyhral!";
        }

    return "";
}
}
