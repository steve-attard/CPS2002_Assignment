import java.io.IOException;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Welcome to Treasure Map Game");
        System.out.println("-------------------------------");
        System.out.println("Please Choose the Game Type");
        System.out.println("1. Single Player Mode");
        System.out.println("2. Collaborative Mode");
        int gameType = sc.nextInt();
        if(gameType == 1){
            SingleGame game = new SingleGame();
            game.startGame();
        }
        else if (gameType == 2){
            CollaborativeGame game = new CollaborativeGame();
            game.startGame();
        }
        else{
            System.out.println("Invalid Input");
        }

    }
}
