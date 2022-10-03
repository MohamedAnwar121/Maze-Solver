import com.company.IMazeSolver;
import com.company.MyQueue;
import com.company.MyStack;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class MazeSolver implements IMazeSolver {


    /**
     * Read the maze file, and solve it using Breadth First Search
     * @param maze maze file
     * @return the coordinates of the found path from point ’S’
     *
    to point ’E’ inclusive, or null if no path is found.
     */
    public int[][] solveDFS(File maze){

        try{
            Scanner in = new Scanner(maze);

            int rows = in.nextInt();
            int cols = in.nextInt();
            in.nextLine();
            char[][] forest = new char[rows][cols];
            for(int i = 0 ; i < rows ; i++){
                forest[i] = in.nextLine().toCharArray();
            }
            MyStack st = new MyStack();
            int[] coor = SearchStart(forest);
            boolean[][] visited = new boolean[forest.length][forest[0].length];
            st.push(coor);
            visited[coor[0]][coor[1]] = true;
            boolean R_goal = false;
            int[] temp = new int[2];
            Point[][] parents = new Point[forest.length][forest[0].length];

            while(!st.isEmpty()){
                coor = st.pop();
                visited[coor[0]][coor[1]] = true;
                if(forest[coor[0]][coor[1]] == 'E'){
                    R_goal = true;
                    break;
                }


                temp[1] = coor[1];temp[0] = coor[0] - 1;
                if(ValidUnvisit(temp, forest, visited, st)){st.push(temp); parents[temp[0]][temp[1]] = new Point(coor[0],coor[1]);}
                temp[1] = coor[1] - 1;temp[0] = coor[0];
                if(ValidUnvisit(temp, forest, visited, st)){st.push(temp); parents[temp[0]][temp[1]] = new Point(coor[0],coor[1]);}
                temp[1] = coor[1];temp[0] = coor[0] + 1;
                if(ValidUnvisit(temp, forest, visited, st)){st.push(temp); parents[temp[0]][temp[1]] = new Point(coor[0],coor[1]);}
                temp[1] = coor[1] + 1;temp[0] = coor[0];
                if(ValidUnvisit(temp, forest, visited, st)){st.push(temp); parents[temp[0]][temp[1]] = new Point(coor[0],coor[1]);}
            }
            if(R_goal){
                temp = SearchStart(forest);
                MyStack outst = new MyStack();
                int a = 0, b = 0;
                outst.push(new int[]{coor[0], coor[1]});

                //get parents
                while(parents[coor[0]][coor[1]].x != temp[0] || parents[coor[0]][coor[1]].y != temp[1]){
                    outst.push(new int[]{ parents[coor[0]][coor[1]].x , parents[coor[0]][coor[1]].y });
                    a = parents[coor[0]][coor[1]].x;
                    b = parents[coor[0]][coor[1]].y;
                    coor[0] = a;
                    coor[1] = b;
                }
                outst.push(new int[]{temp[0] , temp[1] });
                int[][] result = new int[outst.size()][2];
                for(int i = 0 ; i < result.length ; i++){
                    result[i] = outst.pop();
                }
                return result;
            }
        }catch(Exception ignored){  }
        return null;


    }


    public int[][] solveBFS(File maze){

        /**
         * Read the maze file, and solve it using Depth First Search
         * @param maze maze file
         * @return the coordinates of the found path from point ’S’
         *
        to point ’E’ inclusive, or null if no path is found.
         */


        try{
            Scanner in = new Scanner(maze);

            int rows = in.nextInt();
            int cols = in.nextInt();
            in.nextLine();
            char[][] forest = new char[rows][cols];
            for(int i = 0 ; i < rows ; i++){
                forest[i] = in.nextLine().toCharArray();
            }


            MyQueue que = new MyQueue(100);
            int[] coor = SearchStart(forest);
            boolean[][] visited = new boolean[forest.length][forest[0].length];
            que.enqueue(coor);
            visited[coor[0]][coor[1]] = true;
            boolean R_goal = false;
            int[] temp = new int[2];
            Point[][] parents = new Point[forest.length][forest[0].length];


            while(!que.isEmpty()){
                try{  coor = que.dequeue();  }catch(Exception e){};
                visited[coor[0]][coor[1]] = true;
                if(forest[coor[0]][coor[1]] == 'E'){
                    R_goal = true;
                    break;
                }


                temp[1] = coor[1] + 1;temp[0] = coor[0];
                if(ValidUnvisit(temp, forest, visited, que)){que.enqueue(temp); parents[temp[0]][temp[1]] = new Point(coor[0],coor[1]);}

                temp[1] = coor[1];temp[0] = coor[0] + 1;
                if(ValidUnvisit(temp, forest, visited, que)){que.enqueue(temp); parents[temp[0]][temp[1]] = new Point(coor[0],coor[1]);}

                temp[1] = coor[1] - 1;temp[0] = coor[0];
                if(ValidUnvisit(temp, forest, visited, que)){que.enqueue(temp); parents[temp[0]][temp[1]] = new Point(coor[0],coor[1]);}

                temp[1] = coor[1];temp[0] = coor[0] - 1;
                if(ValidUnvisit(temp, forest, visited, que)){que.enqueue(temp); parents[temp[0]][temp[1]] = new Point(coor[0],coor[1]);}


            }
            if(R_goal){

                temp = SearchStart(forest);
                MyStack outst = new MyStack();
                int a = 0, b = 0;
                outst.push(new int[]{coor[0], coor[1]});

                //get parents
                while(parents[coor[0]][coor[1]].x != temp[0] || parents[coor[0]][coor[1]].y != temp[1]){
                    outst.push(new int[]{ parents[coor[0]][coor[1]].x , parents[coor[0]][coor[1]].y });
                    a = parents[coor[0]][coor[1]].x;
                    b = parents[coor[0]][coor[1]].y;
                    coor[0] = a;
                    coor[1] = b;
                }

                outst.push(new int[]{temp[0] , temp[1] });
                int[][] result = new int[outst.size()][2];

                for(int i = 0 ; i < result.length ; i++){
                    result[i] = outst.pop();
                }
                return result;
            }

        }catch(Exception e){ System.out.println("Error"); }
        return null;

    }


    public static int[] SearchStart(char[][] arr){
        int i = 0 , j = 0;
        label:
        for( i = 0 ; i < arr.length ; i++){
            for( j = 0 ; j < arr[0].length ; j++){
                if(arr[i][j] == 'S'){
                    break label;
                }
            }
        }
        int[] point = {i , j};
        return point;
    }
    public static boolean VCoor(int[] coor , int rows , int cols){
        return ( 0 <= coor[0] && coor[0] < rows && 0 <= coor[1] && coor[1] < cols );
    }

    public static boolean ValidUnvisit(int[] coor, char[][] forest , boolean[][] visited , MyStack st ){
        if(VCoor(coor, forest.length, forest[0].length) && forest[coor[0]][coor[1]] != '#' && visited[coor[0]][coor[1]] == false ){
            return true;
        }
        else return false;
    }

    public static boolean ValidUnvisit(int[] coor, char[][] forest , boolean[][] visited , MyQueue que ){
        if(VCoor(coor, forest.length, forest[0].length) && forest[coor[0]][coor[1]] != '#' && visited[coor[0]][coor[1]] == false && !que.check(coor)){
            return true;
        }
        else return false;
    }



    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        System.out.print("please Enter the path of file that has input: ");
        String input = in.nextLine();
        File maze = new File(input);
        com.company.MazeSolver obj = new com.company.MazeSolver();
        int[][] BFSpath =  obj.solveBFS(maze);
        int[][] DFSpath =  obj.solveDFS(maze);


        System.out.println( "BFS path:" + Arrays.deepToString( obj.solveBFS(maze) ) );
        System.out.println( "DFS path:" + Arrays.deepToString( obj.solveDFS(maze) ) );
    }
}
