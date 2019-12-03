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
import com.sun.org.apache.bcel.internal.generic.NEW;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class QueueMazeSolver implements MazeSolver {

    private MazeGUI gui;

    public QueueMazeSolver() {
        gui = new MazeGUI(this);
    }

    /**
     * This method is called when the start button is
     * clicked in the MazeGUI.  This method should solve the maze.
     * This method may call MazeGUI.drawMaze(...) whenever the
     * GUI display should be updated (after each step of the solution).
     *
     * The maze is provided as the first parameter.  It is a 2D array containing
     * characters that represent the spaces in the maze.  The following
     * characters will be found in the array:
     *    '#' - This represents a wall.
     *    ' ' - This represents an open space (corridor)
     *
     * When calling MazeGUI.drawMaze(...) to update the display, the GUI
     * will recognize the '#' and ' ' characters as well as the following:
     *    '@' - Means the cell is a space that has been explored
     *    '%' - Means that the cell is part of the best path to the goal.
     *
     * @param maze the maze (see above).
     * @param startR the row of the start cell.
     * @param startC the column of the start cell.
     * @param endR the row of the end (goal) cell.
     * @param endC the column of the end (goal) cell.
     */
    @Override
    public void solve(char[][] maze, int startR, int startC, int endR, int endC) {
        gui.setStatusText("Analysing Solvability");

        int row1Flag = 0;
        int row2Flag = 0;
        int col1Flag = 0;
        int col2Flag = 0;
        int deadCount = 0;
        int numBox = 0;
        int solvFlag = 0;
        ArrayQueue<Cell> Q = new ArrayQueue<>();
        Cell cellStartPath = new Cell(startR, startC);
        Cell cellStart = new Cell(startR, startC,cellStartPath);
        Cell cellEnd = new Cell(endR, endC);
        maze[startR][startC] = '@';
        Q.offer(cellStart);


        try {
            while (!Q.isEmpty()) {

                /**Checks if the Cell is the Ending cell*/
                if (Q.element().getRow() == cellEnd.getRow() && Q.element().getColumn() == cellEnd.getColumn()) {
                    solvFlag = 1;
                    cellEnd = Q.poll();
                    break;
                }
                Cell cellTemp = Q.poll();

                /** Checks if the above cell is empty*/
                if (maze[cellTemp.getRow() + 1][cellTemp.getColumn()] == ' ') {
                    Cell newCell = new Cell(cellTemp.getRow() + 1, cellTemp.getColumn(), cellTemp);
                    maze[newCell.getRow()][newCell.getColumn()] = '@';
                    Q.offer(newCell);
                    gui.drawMaze(maze);
                }
                else
                {
                    row1Flag = 1;
                }
                /** Checks if the below cell is empty*/
                if (maze[cellTemp.getRow() - 1][cellTemp.getColumn()] == ' ') {
                    Cell NewCell2 = new Cell(cellTemp.getRow() - 1, cellTemp.getColumn(), cellTemp);
                    maze[NewCell2.getRow()][NewCell2.getColumn()] = '@';
                    Q.offer(NewCell2);
                    gui.drawMaze(maze);
                }
                else
                {
                    row2Flag = 1;
                }
                /** Checks if the right cell is empty*/
                if (maze[cellTemp.getRow()][cellTemp.getColumn() + 1] == ' ') {
                    Cell NewCell3 = new Cell(cellTemp.getRow(), cellTemp.getColumn() + 1,cellTemp);
                    maze[NewCell3.getRow()][NewCell3.getColumn()] = '@';
                    Q.offer(NewCell3);
                    gui.drawMaze(maze);
                }
                else
                {
                    col1Flag = 1;
                }
                /** Checks if the left cell is empty*/
                if (maze[cellTemp.getRow()][cellTemp.getColumn() - 1] == ' ') {
                    Cell NewCell4 = new Cell(cellTemp.getRow(), cellTemp.getColumn() - 1, cellTemp);
                    maze[NewCell4.getRow()][NewCell4.getColumn()] = '@';
                    Q.offer(NewCell4);
                    gui.drawMaze(maze);
                }
                else
                {
                    col2Flag = 1;
                }
                /** Dead End Finder */
                if(row1Flag == 1 && row2Flag == 1 && col1Flag == 1 && col2Flag == 1){
                    deadCount++;
                    maze[cellTemp.getRow()][cellTemp.getColumn()] = '%';
                    gui.drawMaze(maze);

                }
                /** Sleeper Try-Catch for solvability */
                try
                {
                     Thread.sleep(10);
                }
                catch (InterruptedException e)
                {
                     System.err.println("Thread interrupted!");
                }
                numBox++;
                row1Flag = 0;
                row2Flag = 0;
                col1Flag = 0;
                col2Flag = 0;

            }
            /** Optimal Path Finder*/
            while(cellEnd.hasPrev()){
                gui.setStatusText("Solution Found: Building Optimal Path & Gathering Data");
                maze[cellEnd.getRow()][cellEnd.getColumn()] = '&';
                cellEnd = cellEnd.getPrevCell();
                gui.drawMaze(maze);
                /**Sleeper Try-Catch for Path building*/
                try
                {
                    Thread.sleep(1);
                }
                catch (InterruptedException e)
                {
                    System.err.println("Thread interrupted!");
                }
            }
            maze[cellStart.getRow()][cellStart.getColumn()] = '&';



        }

        catch(NoSuchElementException e){
            solvFlag = -1;
        }

        if(solvFlag == 1){
            gui.setStatusText("SOLVABLE  Details: Spaces Explored: " + numBox + "  Explored Dead Ends: " + deadCount);

        }
        else{
            gui.setStatusText("NOT SOLVABLE  Details: Spaces Explored: " + numBox + " Explored Dead Ends: " + deadCount);
        }


    }
}
