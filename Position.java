package assignment2;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position(Position pos){
        this.x = pos.x;
        this.y = pos.y;
    }

    public void reset(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void reset(Position pos){
        this.x = pos.x;
        this.y = pos.y;
    }

    public static int getDistance(Position p1, Position p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void moveWest(){
        this.x -= 1;
    }

    public void moveEast(){
        this.x += 1;
    }

    public void moveNorth(){
        this.y -= 1;
    }

    public void moveSouth(){
        this.y += 1;
    }

    public boolean equals(Object obj){
        if(!(obj instanceof Position)){
            return false;
        }
        Position other = (Position) obj;
        return this.x == other.x && this.y == other.y;
    }
}