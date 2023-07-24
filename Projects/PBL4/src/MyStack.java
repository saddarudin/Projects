public class MyStack {
    private String name;
    public MyStack(String name){
        this.name=name;
    }
    public class Node{
        private EarthQuake data;
        private Node next;
        public Node(EarthQuake data, Node next){
            this.next=next;
            this.data=data;
        }
        public Node(){}
        public Node getNext(){
            return next;
        }
        public EarthQuake getData(){
            return data;
        }
    }
    private Node top;
    private int size;
    public int getSize(){
        return size;
    }
    public void push(EarthQuake data){
        top = new Node(data,top);
        size++;
    }
    public Node getTop(){
        return top;
    }
    public String getName(){
        return name;
    }
    public void traverse(){
        for(Node n=top;n!=null;n=n.next) System.out.println(n.data);
        System.out.println("-------------------------------------------------------\n");
    }
}
