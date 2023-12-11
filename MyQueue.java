package assignment2;

import java.util.NoSuchElementException;

public class MyQueue<E>{
    private MyDoublyLinkedList<E> queue;

    public MyQueue(){
        this.queue = new MyDoublyLinkedList<E>();
    }

    public void enqueue(E ele){
        this.queue.addLast(ele);
    }

    public E dequeue(){
        if (queue.getSize() == 0){
            throw new NoSuchElementException("Queue is Empty");
        }
        return this.queue.removeFirst();
    }

    public boolean isEmpty(){
        return this.queue.isEmpty(); // not sure if this will work since it is from the grandparent class
    }

    public void clear(){
        this.queue.clear();
    }

    public boolean equals(Object obj){ // double check this one
        if (!(obj instanceof MyQueue<?>)){
            return false;
        }
        MyQueue<?> other = (MyQueue<?>) obj;

        return this.queue.equals(other.queue);

        //return this.queue.equals(other.queue);
    }
}
