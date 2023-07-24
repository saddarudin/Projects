package PBL3;
import javax.management.openmbean.InvalidOpenTypeException;

public class MyLinkedQueue {
    private static class Node{
        EarthQuake data;
        private Node next=this;
        private Node pre=this;
        public Node(EarthQuake data){this.data=data;}
        public Node(EarthQuake data, Node pre, Node next){
            this.data=data;
            this.pre=pre;
            this.next=next;
        }
    }
    private int size=0;
    private final Node head=new Node(null);
    public void add(EarthQuake data) {
        head.pre.next=new Node(data,head.pre,head);
        head.pre=head.pre.next;
        size++;
    }

    public EarthQuake remove() {
        if(isEmpty())throw new InvalidOpenTypeException("Queue is empty!");
        EarthQuake temp=head.next.data;
        head.next=head.next.next;
        size--;
        return temp;
    }
    public EarthQuake first() {
        if(isEmpty())throw new InvalidOpenTypeException("Queue is empty!");
        return head.next.data;
    }

    public int size() {
        return size;
    }
    public void print(){
        for(Node n=head.next;n!=head;n=n.next) System.out.println(n.data);
    }
    public MyLinkedQueue copy(){
        MyLinkedQueue queue=new MyLinkedQueue();
        for(Node n=head.next;n!=head;n=n.next)queue.add(n.data);
        return queue;
    }
    public boolean isEmpty(){
        return size==0;
    }
}