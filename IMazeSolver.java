import java.util.*;
import java.awt.*;
import java.io.File;

interface IMazeSolver {

    public int[][] solveBFS(File maze);

    public int[][] solveDFS(File maze);
}