import java.util.*;

public class Game {

    public int turns;

    public Map map = new Map();

    public void startGame(){

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        int x_pos;
        int y_pos;
        int minMapSize = 0;
        int maxMapSize = 50;

        System.out.println("Welcome to Treasure Map Game");
        System.out.println("Please Enter the number of players");
        int playerno = sc.nextInt();
        while(!setNumPlayers(playerno)){
            System.out.println("Please Enter the number of players");
            playerno = sc.nextInt();
        }

        if(playerno > 1 && playerno < 5){
            minMapSize = 5;
        }
        else{
            minMapSize = 8;
        }

        System.out.println("Please Enter the size of the map");
        int mapsize = sc.nextInt();
        while(mapsize > maxMapSize || mapsize < minMapSize){
            System.out.println("Invalid map size");
            System.out.println("Please enter a valid map size");
            mapsize = sc.nextInt();
        }

        map.size = mapsize;
        map.generate();

        ArrayList<Player> players = new ArrayList<Player>();

        for(int i = 0; i < playerno; i++){
            Player p = new Player(mapsize);
            players.add(p);
        }

        boolean treasureFound = false;
        char dir = ' ';

        Player currentPlayer = new Player(mapsize);

        while(!treasureFound){
            for(int i = 0; i < playerno; i++){
                //player position before moving needs to be stored.
                System.out.println("player "+ (i+1) +" move");
                Position currentPlayerPos = new Position();
                x_pos = currentPlayerPos.x;
                y_pos = currentPlayerPos.y;
                currentPlayer = players.get(i);
                int x = currentPlayer.position.x;
                int y = currentPlayer.position.y;
                currentPlayerPos.x = x;
                currentPlayerPos.y = y;
                System.out.println("Please input a direction to move");
                dir = sc.next().charAt(0);
                currentPlayer.move(dir);

                //validating correct move
                if(currentPlayer.position.x == x_pos && currentPlayer.position.y == y_pos){
                    i--;
                }
                else{
                    currentPlayer.table[currentPlayer.position.x][currentPlayer.position.y] =
                    map.table[currentPlayer.position.x][currentPlayer.position.y];

                    //if player landed on water tile, reset starting position
                    if(map.table[currentPlayer.position.x][currentPlayer.position.y] == 'w'){
                        currentPlayer.position = currentPlayer.startingPosition;
                    }
                    else if(map.table[currentPlayer.position.x][currentPlayer.position.y] == 't'){
                        treasureFound = true;
                    }
                }

            }
        }

    }
    public boolean setNumPlayers(int n){
        if(n<2 || n>8) {
            System.out.println("Invalid number of players!");
            return false;
        }
        return true;
    }
    public void generateHTMLFiles(){

    }

}
