public class Game {
    public int turns;
    public Player[] players;
    public Map map = new Map();
    public static void main(String args[]){

    }

    public boolean setNumPlayers(int n){
        if(n<2 || n>8) {
            System.out.println("Invalid number of players!");
            return false;
        }
        return true;
    }
    private void generateHTMLFiles(){

    }

}
