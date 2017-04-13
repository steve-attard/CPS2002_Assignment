import java.util.Random;

public class Player {

    public int boardSize;
    public char[][] table;
    public Position position = new Position();
    public Position startingPosition = new Position();

    Random rand = new Random();

    public Player(int size, Map map){

        this.boardSize = size;
        this.table = new char[boardSize][boardSize];
        this.startingPosition.x = rand.nextInt(boardSize);
        this.startingPosition.y = rand.nextInt(boardSize);
        while(map.table[this.startingPosition.x][this.startingPosition.y] != 'g'){
            this.startingPosition.x = rand.nextInt(boardSize);
            this.startingPosition.y = rand.nextInt(boardSize);
        }
        this.position.x = this.startingPosition.x;
        this.position.y = this.startingPosition.y;
    }

    public void move(char direction){

        if(direction == 'u'){
            this.position.y -= 1;
        }else if(direction == 'd'){
            this.position.y += 1;
        }else if(direction == 'l'){
            this.position.x -= 1;
        }else if(direction == 'r'){
            this.position.x += 1;
        }

        if(!this.isPositionInBounds(position)){
            System.out.println("Out of bounds");
        }

    }

    public boolean isPositionInBounds(Position p){
        if((p.y < table.length)&&(p.y >= 0)&&(p.x < table.length)&&(p.x >= 0)) {
            return true;
        }
        return false;
    }

    public void undoPreviousMove(char dir) {
        if(dir == 'u'){
            this.position.y += 1;
        }else if(dir == 'd'){
            this.position.y -= 1;
        }else if(dir == 'l'){
            this.position.x += 1;
        }else if(dir == 'r'){
            this.position.x -= 1;
        }
    }

    public void printTable() {
        for(int i=0; i<table.length; i++) {
            for(int j=0; j<table.length; j++) {
                if(table[j][i] != 'g' && table[i][j] != 'w' && table[j][i] != 't'){
                    System.out.print("| |");
                }
                else {
                    System.out.print("|"+table[j][i]+"|");
                }
            }
            System.out.println();
        }
    }
}
