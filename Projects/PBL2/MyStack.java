package PBL2;

import java.util.EmptyStackException;

public class MyStack{
    private static class Node{
        Crash data;
        Node next;
        public Node(Crash data){this.data=data;}
    }
    private int size;
    private Node top;
    String name;
    public MyStack(String name){this.name=name;}
    public boolean isEmpty(){
        return size==0;
    }
    public void push(Crash data){
        Node n=new Node(data);
        n.next=top;
        top=n;
        size++;
    }
    public Crash pop(){
        if(isEmpty())throw new EmptyStackException();
        Node n=top;
        top=top.next;
        size--;
        return n.data;
    }
    public Crash peek(){
        if(isEmpty())throw new IllegalArgumentException();
        return top.data;
    }

    public int getSize() {
        return size;
    }
}