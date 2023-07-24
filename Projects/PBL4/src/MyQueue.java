public class MyQueue {
    private static class Node{
        private EarthQuake data;
        private Node next=this;
        private Node pre=this;
        public Node(EarthQuake data,Node pre,Node next){
            this.data=data;
            this.next=next;
            this.pre=pre;
        }
        public Node(EarthQuake data){
            this.data=data;
        }
    }
    private int size;
    private Node head=new Node(null);
    public int getSize(){
        return size;
    }
    public void enqueue(EarthQuake data){
        head.pre.next=new Node(data,head.pre,head);
        head.pre=head.pre.next;
        size++;
    }
    public void traverse(){
        for(Node n=head.next;n!=head;n=n.next) System.out.println(n.data);
    }
}
