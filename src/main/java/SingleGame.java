import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SingleGame extends Game{

    public void startGame() throws IOException {

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        int minMapSize = 0;
        int maxMapSize = 50;

        System.out.println("Please Enter the number of players");
        int playerno = sc.nextInt();
        while (!setNumPlayers(playerno)) {
            System.out.println("Please Enter the number of players");
            playerno = sc.nextInt();
        }

        turns = playerno;

        if (playerno > 1 && playerno < 5) {
            minMapSize = 5;
        } else {
            minMapSize = 8;
        }

        System.out.println("Please Enter the size of the map");
        int mapsize = sc.nextInt();
        while (mapsize > maxMapSize || mapsize < minMapSize) {
            System.out.println("Invalid map size");
            System.out.println("Please enter a valid map size");
            mapsize = sc.nextInt();
        }

        map.size = mapsize;
        System.out.println("Please Enter the type of Map you would like");
        System.out.println("1. Safe");
        System.out.println("2. Hazardous");
        int choice = sc.nextInt();
        while(choice > 2 || choice < 1){
            System.out.println("Please Enter the type of Map you would like");
            System.out.println("1. Safe");
            System.out.println("2. Hazardous");
            choice = sc.nextInt();
        }
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
                System.out.println("Player " + (i + 1) + " turn");
                System.out.println("Player " + (i + 1) + " position: ");

                currentPlayer = players.get(i);
                overwriteHTMLFile(currentPlayer);
                System.out.println("x coordinate: " + (currentPlayer.position.x+1) + ", y coordinate: " + (currentPlayer.position.y+1));

                System.out.println("Please input a direction to move");
                dir = sc.next().toLowerCase().charAt(0);
                while (dir != 'u' && dir != 'd' && dir != 'l' && dir != 'r') {
                    System.out.println("Invalid input");
                    dir = sc.next().toLowerCase().charAt(0);
                }
                currentPlayer.move(dir);

                System.out.println("player " + (i + 1) + " position: ");
                System.out.println("x coordinate: " + (currentPlayer.position.x+1) + ", y coordinate: " + (currentPlayer.position.y+1));

                //validating correct move
                if (!currentPlayer.isPositionInBounds(currentPlayer.position)) {
                    System.out.println("Out of Bounds!");
                    //undo previous move
                    if (dir == 'u') {
                        currentPlayer.position.y += 1;
                    } else if (dir == 'd') {
                        currentPlayer.position.y -= 1;
                    } else if (dir == 'l') {
                        currentPlayer.position.x += 1;
                    } else if (dir == 'r') {
                        currentPlayer.position.x -= 1;
                    }
                    i--;
                } else {
                    currentPlayer.table[currentPlayer.position.x][currentPlayer.position.y] =
                            map.table[currentPlayer.position.x][currentPlayer.position.y];
                    overwriteHTMLFile(currentPlayer);

                    //if player landed on water tile, reset starting position
                    if (map.table[currentPlayer.position.x][currentPlayer.position.y] == 'w') {
                        System.out.println("You landed on a water tile!");
                        currentPlayer.position = currentPlayer.startingPosition;
                    } else if (map.table[currentPlayer.position.x][currentPlayer.position.y] == 't') {
                        System.out.println("Congratulations, you found the treasure!");
                        treasureFound = true;
                    } else {
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
