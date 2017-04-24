import java.io.*;
import java.util.ArrayList;

public class Game {

    public int turns;

    public Map map = new Map();

    public boolean setNumPlayers(int n){
        if(n<2 || n>8) {
            System.out.println("Invalid number of players!");
            return false;
        }
        return true;
    }

    public void overwriteHTMLFile(Player currentPlayer) throws IOException{

        File f1 = new File("map.htm");
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(f1));
        bw1.write("<html>");
        bw1.write("<head>");
        bw1.write("<title>PLAYER " +(currentPlayer.playerNo)+ " MAP</title>");
        bw1.write("</head>");
        bw1.write("<body>");
        bw1.write("<center>");
        bw1.write("<h1>Player "+(currentPlayer.playerNo)+" Map</h1>");
        bw1.write("</center>");
        bw1.write("<table border = 5 width = 500 height = 500 align = center>");

        for(int i=0; i<map.table.length; i++) {
            bw1.write("<tr>");
            for(int j=0; j<map.table.length; j++) {
            //grass to be marked as green
                if(currentPlayer.table[j][i] == 'g'){
                    bw1.write("<td bgcolor=\"green\">");
                }
                //water to be marked as blue
                else if(currentPlayer.table[j][i] == 'w') {
                    bw1.write("<td bgcolor=\"blue\">");
                }
                //treasure to be marked as yellow
                else if(currentPlayer.table[j][i] == 't') {
                    bw1.write("<td bgcolor=\"yellow\">");
                    //http://findicons.com/files/icons/1307/jolly_roger/128/pirate_treasure.png
                }
                //starting position to be marked as white
                else if(currentPlayer.startingPosition.x == j && currentPlayer.startingPosition.y == i){
                    bw1.write("<td bgcolor=\"white\"s>");
                }
                //not yet visited to be marked as black
                else {
                    bw1.write("<td bgcolor=\"black\">");
                }
            }
            bw1.write("</tr>");
        }

        bw1.write("</table>");
        bw1.write("</body>");
        bw1.write("</html>");
        bw1.close();

    }

    public void generateHTMLFile() throws IOException{

        File f1 = new File("map.htm");
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(f1));
        bw1.write("<html>");
        bw1.write("<head>");
        bw1.write("<title>MAIN MAP</title>");
        bw1.write("</head>");
        bw1.write("<body>");
        bw1.write("<center>");
        bw1.write("<h1>Main Map</h1>");
        bw1.write("</center>");
        bw1.write("<table border = 5 width = 500 height = 500 align = center>");

        for(int i=0; i<map.table.length; i++) {
            bw1.write("<tr>");
            for(int j=0; j<map.table.length; j++) {
                if(map.table[j][i] == 'g'){
                    bw1.write("<td bgcolor=\"green\">");
                }
                else if(map.table[j][i] == 'w') {
                    bw1.write("<td bgcolor=\"blue\">");
                }
                else if(map.table[j][i] == 't') {
                    bw1.write("<td bgcolor=\"yellow\">");
                }
            }
            bw1.write("</tr>");
        }


        bw1.write("</table>");
        bw1.write("</body>");
        bw1.write("</html>");
        bw1.close();

    }
}