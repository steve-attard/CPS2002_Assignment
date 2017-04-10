import java.util.*;

public class Map {

    public int size;
    char[][] table = new char[size][size];

    Random rand = new Random();

    int random_x = rand.nextInt(size);
    int random_y = rand.nextInt(size);

    public boolean setMapSize(int x, int y){
        if(x > 50 && x < 5){
            return false;
        }
        else{
            return true;
        }

    }
    public void generate(){

        table[random_x][random_y] = 't';
        for(int i = 0; i < 2; i++){
            while(table[random_x][random_y]  != ' '){
                random_x = rand.nextInt(size);
                random_y = rand.nextInt(size);
            }
            table[random_x][random_y] = 'w';
        }

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(table[i][j] != 't' && table[i][j] != 'w'){
                    table[i][j] = 'g';
                }
            }
        }

    }
    public char getTileType(int x, int y){
        return table[x][y];
    }
}
