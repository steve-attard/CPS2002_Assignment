import java.util.*;

public class Map {

    public int size;
    char[][] table;

    Random rand = new Random();

    public boolean setMapSize(int x, int y){
        if(x > 50 || x < 5){
            return false;
        }
        else{
            return true;
        }

    }

    public void generate(int type){

        int random_x = rand.nextInt(size);
        int random_y = rand.nextInt(size);
        table = new char[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                table[i][j] = 'g';
            }
        }

        table[random_x][random_y] = 't';

        int watertiles = 0;
        if(type == 1) {
            watertiles = rand.nextInt((int)(size*size*0.1)) + 1;
        }
        else if(type == 2){
            watertiles = rand.nextInt((int)((size*size*0.35)-(size*size*0.25))) + (int)(size*size*0.25);
        }
        else{
            System.out.println("Feature not yet added!");
        }
        System.out.println("watertiles = "+ watertiles);
        for(int i = 0; i < watertiles; i++){
            while(table[random_x][random_y]  == 't' || table[random_x][random_y] == 'w'){
                random_x = rand.nextInt(size);
                random_y = rand.nextInt(size);
            }
            table[random_x][random_y] = 'w';
        }
    }

    public void showTurnPlayer(char[][] playTable){

    }

    public char getTileType(Position p) {
        return this.table[p.x][p.y];
    }
}