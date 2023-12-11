package assignment2;

import java.util.Iterator;

public class Caterpillar extends MyDoublyLinkedList<Position>{
    public Caterpillar(){
        super();
        Position start = new Position(7,7);
        this.addFirst(start);
    }

    public Position getHead(){
        return this.peekFirst();
    }

    public void eat(Position pos){
        Position head = this.getHead();
        if(head.getX() == pos.getX() && (head.getY() + 1 == pos.getY() || head.getY() - 1 == pos.getY())){
            this.addFirst(pos);
        }
        else if(head.getY() == pos.getY() && (head.getX() + 1 == pos.getX() || head.getX() - 1 == pos.getX())){
            this.addFirst(pos);
        }
        else {
            throw new IllegalArgumentException("Cannot eat this object");

        }
    }

    public void move(Position pos){
        Position head = this.getHead();
        if(head.getX() == pos.getX() && (head.getY() + 1 == pos.getY() || head.getY() - 1 == pos.getY())){
            this.addFirst(pos);
            this.removeLast();
        }
        else if(head.getY() == pos.getY() && (head.getX() + 1 == pos.getX() || head.getX() - 1 == pos.getX())){
            this.addFirst(pos);
            this.removeLast();
        }
        else{
            throw new IllegalArgumentException("Cannot move here");
        }
    }

    public boolean selfCollision(Position pos){ //test this also not sure if im allowed to use iterate
        Iterator<Position> thisIterator = this.iterator();

        while (thisIterator.hasNext()){
            Position thisPos = thisIterator.next();

            if ((thisPos.equals(pos))){
                return true;
            }
        }

        return false;
    }

    /*public static void main(String[] args) {
        Caterpillar c = new Caterpillar();
        Position p = new Position(7,8);
        Position p2 = new Position(7,9);
        Position p3 = new Position(8,9);
        Position p4 = new Position(7,8);
        c.move(p);
        c.move(p2);
        c.eat(p3);
        System.out.println(c.size);
        System.out.println(c.getHead());
        System.out.println(c.selfCollision(p2));
    }*/
}
