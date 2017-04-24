import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CollaborativeGame extends Game {

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

        System.out.println("Please Enter the number of Teams");
        int teamno = sc.nextInt();
        while(teamno > playerno || teamno < 2){
            System.out.println("Please Enter the number of Teams (Min 2)");
            teamno = sc.nextInt();
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
        //true for safe
        //false for hazardous
        map.generate(true);
        ArrayList<Player> players = new ArrayList<Player>();

        for (int i = 0; i < playerno; i++) {
            Player p = new Player(mapsize, map, 1);
            players.add(p);
        }
        ArrayList<Player> tempList = new ArrayList<Player>(players);
        Collections.shuffle(tempList);

        int teamnumber = 1;
        for(int i = 0; i < playerno; i++) {
            if(teamnumber > teamno){
                teamnumber = 1;
            }
            tempList.get(i).teamNo = teamnumber;
            teamnumber++;
        }

        for(Player p : players){
            System.out.println(p.teamNo);
        }

        generateHTMLFiles(players);
        boolean treasureFound = false;
        char dir;

        Player currentPlayer = new Player(mapsize, map, 1);

        while (!treasureFound) {
            for (int i = 0; i < playerno; i++) {
                generateHTMLFiles(players);
                System.out.println("player " + (i + 1) + " move");
                System.out.println("player " + (i + 1) + " position: ");

                currentPlayer = players.get(i);
                System.out.println(currentPlayer.position.x + "  " + currentPlayer.position.y);

                System.out.println("Please input a direction to move");
                dir = sc.next().toLowerCase().charAt(0);
                while (dir != 'u' && dir != 'd' && dir != 'l' && dir != 'r') {
                    System.out.println("Invalid input");
                    dir = sc.next().toLowerCase().charAt(0);
                }
                currentPlayer.move(dir);

                System.out.println("player " + (i + 1) + " position: ");
                System.out.println(currentPlayer.position.x + "  " + currentPlayer.position.y);

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
                    for(Player p : players){
                        if(p.teamNo == currentPlayer.teamNo){
                            p.table[currentPlayer.position.x][currentPlayer.position.y] =
                                    map.table[currentPlayer.position.x][currentPlayer.position.y];
                        }
                    }
                    //if player landed on water tile, reset starting position
                    if (map.table[currentPlayer.position.x][currentPlayer.position.y] == 'w') {
                        System.out.println("You landed on a water tile!");
                        currentPlayer.position = currentPlayer.startingPosition;
                    } else if (map.table[currentPlayer.position.x][currentPlayer.position.y] == 't') {
                        System.out.println("Congratulations, you found the treasure!");
                        System.out.println("Team " + currentPlayer.teamNo + " Wins");
                        treasureFound = true;
                    } else {
                        System.out.println("You landed on a grass tile!");
                    }
                    currentPlayer.printTable();
                }

            }
        }

    }

}
