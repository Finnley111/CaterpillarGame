package assignment2;

public abstract class MyLinkedList<E> implements MyList<E>{
    protected int size;

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return this.getSize() == 0;

//        if (this.getSize() == 0){
//            return true;
//        }
//        return  false;
    }
}
