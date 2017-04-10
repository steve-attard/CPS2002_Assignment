public class Player {

    public char[][] table;
    public Position position = new Position();

    public void setTable(int size) {
        table = new char[size][size];
    }

    public void move(char direction){
        if(direction == 'u'){
            position.y += 1;
        }else if(direction == 'd'){
            position.y -= 1;
        }else if(direction == 'l'){
            position.x -= 1;
        }else if(direction == 'r'){
            position.x += 1;
        }

        if(!this.setPosition(position)){
            System.out.println("Invalid input");

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
