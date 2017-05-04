import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CollaborativeGame extends Game {

    public void startGame() throws IOException {

        //initial variable declarations
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        int minMapSize = 0;
        int maxMapSize = 50;
        int playerno = 0;

        //get number of players, enforcing correct input
        do {
            playerno = getValidInt("Please Enter the number of players");
        } while (!setNumPlayers(playerno));

        int teamno = 0;
        //get number of teams, enforcing correct input
        do {
            teamno = getValidInt("Please Enter the number of Teams");
        } while (teamno > playerno || teamno < 2);

        turns = playerno;

        //setting minimum map size depending on number of players
        minMapSize = setMinMapSize(playerno);

        int mapsize = 0;
        //get map dimensions, enforcing correct input
        do {
            mapsize = getValidInt("Please Enter the size of the map");
        } while (mapsize > maxMapSize || mapsize < minMapSize);

        map.size = mapsize;

        int choice = 0;
        //get game mode desired, enforcing correct input
        do {
            choice = getValidInt("Please Enter the type of Map you would like\n1. Safe\n2. Hazardous");
        } while (choice > 2 || choice < 1);

        //generate map depending on game mode desired
        map.generate(choice);

        ArrayList<Player> players = new ArrayList<Player>();
        //create an arraylist of players with as much players as the user desires
        for (int i = 0; i < playerno; i++) {
            Player p = new Player(mapsize, map, 1, i+1);
            players.add(p);
        }

        //assign players to a random team
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

        //tell each player on which team they are
        for(Player p : players){
            System.out.println("Player " + p.playerNo + " is on team " + p.teamNo);
        }

        //generate HTML file containing game map
        generateHTMLFile();

        boolean treasureFound = false;
        char dir;

        Player currentPlayer = new Player(mapsize, map, 1);

        System.out.println("-------------------------------------------------------");
        //main game loop, terminates when the treasure is found
        while (!treasureFound) {
            for (int i = 0; i < playerno; i++) { //control player turns
                //get player who has to play in this turn
                currentPlayer = players.get(i);
                //update HTML file containing map depending on current player's explored positions
                overwriteHTMLFile(currentPlayer);
                //print who's turn it is and his/her position
                System.out.println(printTurnAndPosition(i+1, currentPlayer));
                //ask the player to input a direction to move
                System.out.println("Please input a direction to move");
                dir = sc.next().toLowerCase().charAt(0);
                while (dir != 'u' && dir != 'd' && dir != 'l' && dir != 'r') {
                    System.out.println("Invalid input");
                    dir = sc.next().toLowerCase().charAt(0);
                }
                //update player position
                currentPlayer.move(dir);

                //validating correct move
                if (!currentPlayer.isPositionInBounds(currentPlayer.position)) {
                    //undo previous move
                    currentPlayer.undoPreviousMove(dir);
                    //play turn once more
                    i--;
                } else {
                    //valid move, thus update team's map
                    currentPlayer.table[currentPlayer.position.x][currentPlayer.position.y] = map.getTileType(currentPlayer.position);
                    for(Player p : players){
                        if(p.teamNo == currentPlayer.teamNo){
                            p.table[currentPlayer.position.x][currentPlayer.position.y] =
                                    map.table[currentPlayer.position.x][currentPlayer.position.y];
                        }
                    }
                    overwriteHTMLFile(currentPlayer);

                    //player landed on water tile, reset starting position
                    if(map.getTileType(currentPlayer.position) == 'w'){
                        System.out.println("You landed on a water tile!");
                        currentPlayer.position = currentPlayer.startingPosition;
                    }
                    //player found the treasure, team wins
                    else if(map.getTileType(currentPlayer.position) == 't') {
                        System.out.println("Congratulations, you found the treasure!");
                        System.out.println("Team " + currentPlayer.teamNo + " Wins");
                        treasureFound = true;
                    }
                    //player landed on a grass tile
                    else {
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
