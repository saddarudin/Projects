package PBL3;


import java.util.EmptyStackException;

public class MyLinkedStack{
    private final String stackName;
    public MyLinkedStack(String stackName){
        this.stackName=stackName;
    }

    public String getStackName() {
        return stackName;
    }

    private static class Node{
        EarthQuake data;
        Node next;
        Node(EarthQuake data){
            this.data=data;
        }
    }
    private Node head=null;
    private int size=0;
    public boolean isEmpty(){
        return head==null;
    }
    public int getSize(){
        return size;
    }
    public int search(EarthQuake target){
        int count=1;
        for(Node n=head;n!=null;n=n.next){
            if(n.data.equals(target))return count;
            count++;
        }
        return -1;
    }
    public void push(EarthQuake t){
        Node node= new Node(t);
        node.next=head;
        head=node;
        size++;
    }
    public EarthQuake peek(){
        if(isEmpty())throw new EmptyStackException();
        return head.data;}
    public EarthQuake pop(){
        EarthQuake t=head.data;
        head=head.next;
        --size;
        return t;
    }
    public MyLinkedStack copy(){
        MyLinkedStack stack=new MyLinkedStack(this.getStackName());
        EarthQuake[] a=new EarthQuake[this.size];
        int count=0;
        for(Node n=head;n!=null;n=n.next)a[count++]=n.data;
        for(int i=a.length-1;i>=0;i--)stack.push(a[i]);
        return stack;
    }
    public void display(){
        for(Node n=head;n!=null;n=n.next) System.out.println(n.data);
    }
}
