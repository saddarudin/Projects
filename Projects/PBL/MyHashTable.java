
package PBL;


public class MyHashTable {
    private static class Node{
        String key;
        PakUni value;
        Node next;
    }//Node class to store key value pair
    Node[] table;
    private int size;
    public MyHashTable(){
        table=new Node[64];
    }//default constructor to make table of capacity of 64
    public MyHashTable(int capacity){
        table=new Node[capacity];
    }//to make table of capacity provided by user(>0)
    private int hash(Object key){
        return Math.abs(key.hashCode())% table.length;
    }//to make hash code of given key
    private void resize(){
        Node[] newTable=table;
        table=new Node[table.length*2];
        for(Node head:newTable){
            while (head!=null){
                Node temp=head.next;
                int index=hash(head.key);
                head.next=table[index];
                table[index]=head;
                head=temp;
            }

        }
    }//resize the table when size in more than 75%
    public void put(String  key,PakUni value){
        int index=hash(key);
        Node n=table[index];
        while (n!=null){
            if(n.key.equals(key))break;
            n=n.next;
        }
        if(n!=null){
            n.value=value;
            return;
        }
        if(size>=0.75*table.length)resize();
        Node head=new Node();
        head.value=value;
        head.key=key;
        head.next=table[index];
        table[index]=head;
        size++;
    }
    public PakUni get(String key){
        int index=hash(key);
        Node head=table[index];
        while (head!=null){
            if(head.key.equals(key))return head.value;
            head=head.next;
        }
        return null;
    }
    public PakUni remove(String  key){
        int index=hash(key);
        Node head=table[index];
        if(head==null)return null;
        if(head.key.equals(key)){
            table[index]=head.next;
            size--;
            return head.value;
        }
        Node pre=head;
        Node curr=pre.next;
        while (curr!=null){
            if(curr.key.equals(key)){
                pre.next=curr.next;
                size--;
                return curr.value;
            }
            pre=curr;
            curr=pre.next;
        }
        return null;
    }
    public int size(){return size;}
    public boolean containsKey(String  key){
        int index=hash(key);
        Node head=table[index];
        while (head!=null){
            if (head.key.equals(key))return true;
            head=head.next;
        }
        return false;
    }
}

