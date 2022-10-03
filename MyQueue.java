import java.util.*;
import java.awt.*;
import java.io.File;


class MyQueue{
    int MaxSize;
    int S,front,rear;
    int[][] Q ;
    public MyQueue (int MaxSize){
        Q = new int[MaxSize][2];
        this.MaxSize = MaxSize;
        this.S = 0;
        this.front = 0;
        this.rear = 0;
    }
    public boolean isEmpty(){
        return S == 0;
    }
    public int size(){
        return S;
    }
    public void enqueue(int[] item){
        if(this.size() == this.MaxSize) System.out.println("Error");
        else{
            Q[rear][0] = item[0];
            Q[rear][1] = item[1];
            rear = (rear+1) % MaxSize;
            S++;
        }
    }
    public int[] dequeue() throws Exception{
        if( this.size() == 0 ) throw new Exception("Error");
        int[] temp = Q[front];
        front = (front+1) % MaxSize;
        S--;
        return temp;
    }

    public boolean check(int[] coor){
        boolean found = false;
        int cur = front;
        while(cur != rear){
            if( coor[0] == Q[cur][0] && coor[1] == Q[cur][1] ){ found = true; break; }
            cur++;
        }
        return found;
    }

}