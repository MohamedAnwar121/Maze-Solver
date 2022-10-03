import java.util.*;
import java.awt.*;
import java.io.File;

class MyStack{
    class Node {
        private int x;
        private int y;
        private Node next;
        public int[] getItem() { return new int[]{x , y}; }
        public void setItem(int[] point) { x = point[0]; y = point[1]; }
        public Node getNext() { return next; }
        public void setNext(Node next) { this.next = next; }
    }
    private Node top = null;
    private int size = 0;
    //[1]
    public int[] pop(){
        if(top == null){ System.out.println("Error pop"); return null; }
        else{
            int[] val = top.getItem();
            if(size != 1)top = top.getNext();
            else top = null;
            size--;
            return val;
        }
    }

    //[2]
    public void push(int[] element){
        Node newnode = new Node();
        newnode.setItem(element);
        newnode.setNext(top);
        top = newnode;
        size++;
    }

    //[3]
    public int[] peek(){
        if(top == null) { System.out.println("error peek"); return null; }
        else { return top.getItem(); }
    }

    //[4]
    public int size(){ return size; }
    public boolean isEmpty(){ return (top == null); }

    public boolean check(int[] coor){
        Node cur = top;
        boolean found = false;
        int[] arr = new int[2];
        while( cur != null){
            arr[0] = cur.x;
            arr[1] = cur.y;
            if(arr[0] == coor[0] && arr[1] == coor[1]){ found = true; break; }
            cur = cur.next;
        }
        return found;
    }
}