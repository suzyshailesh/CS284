package hw3;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
    	//returns true if a path is found 
    	
    	if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || maze.getColor(x,y) != NON_BACKGROUND) {
    		//if x or y are out of bound of the table or if the color is not red
        	//return false: (x, y) cannot be part of the path
    		return false;
    	}
    	else if (x == maze.getNCols()-1 && y == maze.getNRows() -1) {
    		//if x and y are the exit cell
    		//return true and change the color to path: a path has been found
    		maze.recolor(x, y, PATH);
    		return true;
    	}
    	
    	else {
    		//else, change the color to path because the tile (x,y) can continue the path
    		//check to see if the surrounding tiles lead to a path to the end
    		//return true if they do
    		//return false and change color to temporary if they don't: (x, y) is a dead end
    		maze.recolor(x, y, PATH);
    		boolean up = findMazePath(x, y+1);
    		boolean down = findMazePath(x, y-1);
    		boolean right = findMazePath(x+1, y);
    		boolean left = findMazePath(x-1, y);
    		
    		boolean test = (up || down || right || left);
    		if (test) {
    			return true;
    		}
    		else {
    			maze.recolor(x, y, TEMPORARY);
    			return false;
    		}
    	}	
    		
    }
    
    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	//returns an ArrayList of ArrayLists of PairInts of all paths to the end tile
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<ArrayList<PairInt>>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0, 0, result, trace);
    	return result;
    	
    }
    
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
    	//helper function
        if (x < 0 || y < 0 || x > maze.getNCols()-1 || y > maze.getNRows()-1 || maze.getColor(x,y) != NON_BACKGROUND) {
        	//if x or y are out of bound of the table or if the color is not red
        	//return nothing: (x, y) cannot be part of the path
        	return;
        }
        else if (x == maze.getNCols()-1 && y == maze.getNRows()-1) {
        	//if x and y are the exit cell
    		//creates ArrayList of PairInts , adds end tile to stack, adds stack to ArrayList
            ArrayList<PairInt> newAdd = new ArrayList<PairInt>();
            trace.push(new PairInt(x, y));
            newAdd.addAll(trace);
            result.add(newAdd);
            trace.pop();
            maze.recolor(x, y, NON_BACKGROUND);
        }
        else {
        	//else, change the color to path because the tile (x,y) can continue the path
    		//pushes tile to stack because it is part of a valid path
        	//recurses through adjacent tiles to see if they lead to valid paths
        	//changes color back to NON_BACKGROUND
        	//removes (x,y) pair from stack
            maze.recolor(x,y, PATH);
            trace.push(new PairInt(x,y));
            findMazePathStackBased(x+1, y, result, trace);
            findMazePathStackBased(x, y+1, result, trace);
            findMazePathStackBased(x-1, y, result, trace);
            findMazePathStackBased(x, y-1, result, trace);
            maze.recolor(x,y, NON_BACKGROUND);
            trace.pop();
        }
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	//compares length of each element in the ArrayList of possible paths to find smallest oath
    	//returns smallest path

        ArrayList<ArrayList<PairInt>> arr = findAllMazePaths(x,y);
        ArrayList<PairInt> minArr = arr.get(0);
        int minSize = minArr.size();  
        
        for (int i = 0; i < arr.size(); i++){
            if (arr.get(i).size() < minSize) {
                minArr = arr.get(i);
                minSize = arr.get(i).size();
            }
            
        }
        return minArr;
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/

