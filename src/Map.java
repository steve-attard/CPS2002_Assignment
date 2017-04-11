public class Map {
    public int size;
    public char[][] table = new char[size][size];
    public boolean setMapSize(int x, int y){
        return true;
    }
    public void generate(){}
    public char getTileType(int x, int y){
        return 'a';
    }
}
