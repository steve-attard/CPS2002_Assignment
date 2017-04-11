import java.util.*;

public class Game {

    public int turns;

    public Map map = new Map();

    public void startGame(){

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

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
        char dir;

        Player currentPlayer = new Player(mapsize);

        while(!treasureFound){
            for(int i = 0; i < playerno; i++){
                //player position before moving needs to be stored.
                System.out.println("player "+ (i+1) +" move");
                System.out.println("player "+ (i+1) +" position: ");

                currentPlayer = players.get(i);
                System.out.println(currentPlayer.position.x + "  "  + currentPlayer.position.y);

                System.out.println("Please input a direction to move");
                dir = sc.next().toLowerCase().charAt(0);
                while(dir!='u' && dir!='d' && dir!='l' && dir!='r'){
                    System.out.println("Invalid input");
                    dir = sc.next().toLowerCase().charAt(0);
                }
                currentPlayer.move(dir);

                System.out.println("player "+ (i+1) +" position: ");
                System.out.println(currentPlayer.position.x + "  "  + currentPlayer.position.y);

                //validating correct move
                if(!currentPlayer.isPositionInBounds(currentPlayer.position)){
                    System.out.println("Out of Bounds!");
                    //undo previous move
                    if(dir == 'u'){
                        currentPlayer.position.y += 1;
                    }else if(dir == 'd'){
                        currentPlayer.position.y -= 1;
                    }else if(dir == 'l'){
                        currentPlayer.position.x += 1;
                    }else if(dir == 'r'){
                        currentPlayer.position.x -= 1;
                    }
                    i--;
                }
                else{
                    currentPlayer.table[currentPlayer.position.x][currentPlayer.position.y] =
                    map.table[currentPlayer.position.x][currentPlayer.position.y];

                    //if player landed on water tile, reset starting position
                    if(map.table[currentPlayer.position.x][currentPlayer.position.y] == 'w'){
                        System.out.println("You landed on a water tile!");
                        currentPlayer.position = currentPlayer.startingPosition;
                    }
                    else if(map.table[currentPlayer.position.x][currentPlayer.position.y] == 't'){
                        System.out.println("Congratulations, you found the treasure!");
                        treasureFound = true;
                    }
                    else{
                        System.out.println("You landed on a grass tile!");
                    }
                    currentPlayer.printTable();
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
