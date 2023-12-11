package assignment2;

import java.util.NoSuchElementException;

public class MyStack<E>{
    private MyDoublyLinkedList<E> stack;

    public MyStack(){
        this.stack = new MyDoublyLinkedList<E>();
    }

    public void push(E ele){
        this.stack.addFirst(ele);
    }

    public E pop(){
        if (stack.getSize() == 0){
            throw new NoSuchElementException("Stack is Empty");
        }
        return this.stack.removeFirst();
    }

    public E peek(){
        if (stack.getSize() == 0){
            throw new NoSuchElementException("Stack is Empty");
        }
        return this.stack.peekFirst();
    }

    public boolean isEmpty(){
        return this.stack.isEmpty(); // not sure if this will work since it is from the grandparent class
    }

    public void clear(){
        this.stack.clear();
    }

    public int getSize(){
        return this.stack.getSize();
    }
}
