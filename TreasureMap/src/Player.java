import java.util.Random;

public class Player {

    public int boardSize;
    public char[][] table;
    public Position position = new Position();
    public Position startingPosition = new Position();

    Random rand = new Random();

    public Player(int size){

        this.boardSize = size;
         this.table = new char[boardSize][boardSize];
        this.startingPosition.x = rand.nextInt(boardSize);
        this.startingPosition.y = rand.nextInt(boardSize);
        this.position = this.startingPosition;
    }

    public void move(char direction){
        System.out.println(position.x + "  "  + position.y);
        if(direction == 'u'){
            position.y -= 1;
        }else if(direction == 'd'){
            position.y += 1;
        }else if(direction == 'l'){
            position.x -= 1;
        }else if(direction == 'r'){
            position.x += 1;
        }else{
            System.out.println("Invalid direction");
        }

        if(!this.setPosition(position)){
            System.out.println("Out of bounds");

            //undo previous move
            if(direction == 'u'){
                position.y -= 1;
            }else if(direction == 'd'){
                position.y += 1;
            }else if(direction == 'l'){
                position.x += 1;
            }else if(direction == 'r'){
                position.x -= 1;
            }
        }

    }

    public boolean setPosition(Position p){
        if((p.y < table.length)&&(p.y >= 0)&&(p.x < table.length)&&(p.x >= 0)) {
            return true;
        }
        return false;
    }
}
