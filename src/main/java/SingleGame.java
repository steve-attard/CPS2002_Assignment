import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SingleGame extends Game{

    public void startGame() throws IOException {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        int minMapSize = 0;
        int maxMapSize = 50;
        int playerno = 0;

        do {
            try {
                System.out.println("Please Enter the number of players");
                playerno = sc.nextInt();
            } catch (InputMismatchException e) {

            }
            sc.nextLine(); // clears the buffer
        } while (!setNumPlayers(playerno));


        turns = playerno;

        minMapSize = setMinMapSize(playerno);

        int mapsize = 0;
        do {
            try {
                System.out.println("Please Enter the size of the map");
                mapsize = sc.nextInt();
            } catch (InputMismatchException e) {

            }
            sc.nextLine(); // clears the buffer
        } while (mapsize > maxMapSize || mapsize < minMapSize);

        map.size = mapsize;

        int choice = 0;
        do {
            try {
                System.out.println("Please Enter the type of Map you would like");
                System.out.println("1. Safe");
                System.out.println("2. Hazardous");
                choice = sc.nextInt();
            } catch (InputMismatchException e) {

            }
            sc.nextLine(); // clears the buffer
        } while (choice > 2 || choice < 1);

        map.generate(choice);
        ArrayList<Player> players = new ArrayList<Player>();

        for (int i = 0; i < playerno; i++) {
            Player p = new Player(mapsize, map, i+1);
            players.add(p);
        }

        generateHTMLFile();

        boolean treasureFound = false;
        char dir;

        Player currentPlayer = new Player(mapsize, map, 1);

        System.out.println("-------------------------------------------------------");

        while (!treasureFound) {
            for (int i = 0; i < playerno; i++) {

                currentPlayer = players.get(i);
                overwriteHTMLFile(currentPlayer);

                System.out.println(printTurnAndPosition(i+1, currentPlayer));

                System.out.println("Please input a direction to move");
                dir = sc.next().toLowerCase().charAt(0);
                while (dir != 'u' && dir != 'd' && dir != 'l' && dir != 'r') {
                    System.out.println("Invalid input");
                    dir = sc.next().toLowerCase().charAt(0);
                }
                currentPlayer.move(dir);

                //validating correct move
                if (!currentPlayer.isPositionInBounds(currentPlayer.position)) {
                    //undo previous move
                    currentPlayer.undoPreviousMove(dir);

                    i--;
                } else {

                    currentPlayer.table[currentPlayer.position.x][currentPlayer.position.y] = map.getTileType(currentPlayer.position);
                    overwriteHTMLFile(currentPlayer);

                    //if player landed on water tile, reset starting position
                    if(map.getTileType(currentPlayer.position) == 'w'){
                        System.out.println("You landed on a water tile!");
                        currentPlayer.position = currentPlayer.startingPosition;
                    }
                    else if(map.getTileType(currentPlayer.position) == 't'){
                        System.out.println("Congratulations, you found the treasure!");
                        treasureFound = true;
                    }
                    else{
                        System.out.println("You landed on a grass tile!");
                    }
                    System.out.println("Please Press enter to finish your turn");
                    String a = sc.next();
                    System.out.println("-------------------------------------------------------");
                }

            }
        }
    }
}
