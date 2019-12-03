/*
Name: Spenser Currier
Assignment: Lab 07
Course/Semester: CS 270 - Spring 2018
Class Section: Section 1
Instructor:Dr. Renzhi Cao
Sources consulted: None
Known Bugs:The Dead End tracker does not correctly
calculate Dead Ends if the maze wraps around on it self
Example:(maze-17x17.txt)
Creative feature(s): Tracks the amount of spaces explored, amount of dead ends(and colors them),
also Builds an optimal path through if the maze is solvable.
*/
public class Cell {

    //what row the cell is in
    private int row;
    //What col the row is in
    private int column;
    //Pointer to the cell that created this
    private Cell prevCell;

    /**
     * Default constructor for the Cell class
     */
    public Cell(){
        row = 0;
        column =0;
        prevCell = null;
    }

    /**
     * Cell constructor with the row and col number passed in
     * @param r the row number
     * @param c the column number
     */
    public Cell(int r, int c){
        row = r;
        column = c;
        prevCell = null;
    }

    /**
     * Cell constructor with all variable being able to be passed in
     * @param r the row number
     * @param c the column number
     * @param x the cell that created this cell
     */
    public Cell(int r, int c, Cell x){
        row = r;
        column = c;
        prevCell = x;
    }

    /**
     * Determine if this cell's prevCell is null
     * @return False if null; true otherwise.
     */
    public boolean hasPrev(){
        if(prevCell == null){
            return false;
        }
        else
            return true;
    }

    /**
     * Setter for this object's row
     * @param r the row number
     */
    public void setRow(int r){
        row = r;
    }

    /**
     * Setter for this object's column
     * @param c
     */
    public void setColumn(int c){
        column = c;
    }

    /**
     * Getter for this object's row
     * @return the row number
     */
    public int getRow(){
        return row;
    }

    /**
     * Getter for the object's column
     * @return the column number
     */
    public int getColumn(){
        return column;
    }

    /**
     * Getter for the this object's preCell
     * @return
     */
    public Cell getPrevCell(){
        return prevCell;
    }

    /**
     * toString to return the row and column  EX: (1,2)
     * @return
     */
    public String toString(){
        return "(" + row + "," + column + ")";
    }
}
