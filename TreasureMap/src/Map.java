import java.util.*;

public class Map {

    public int size;
    char[][] table;

    Random rand = new Random();



    public boolean setMapSize(int x, int y){
        if(x > 50 && x < 5){
            return false;
        }
        else{
            return true;
        }

    }
    public void generate(){

        int random_x = rand.nextInt(size);
        int random_y = rand.nextInt(size);
        table = new char[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                table[i][j] = 'g';
            }
        }

        table[random_x][random_y] = 't';
        for(int i = 0; i < 2; i++){
            while(table[random_x][random_y]  == 't' || table[random_x][random_y] == 'w'){
                random_x = rand.nextInt(size);
                random_y = rand.nextInt(size);
            }
            table[random_x][random_y] = 'w';
        }
    }
    public char getTileType(int x, int y){
        return table[x][y];
    }
}
