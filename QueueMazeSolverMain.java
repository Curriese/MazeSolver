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
public class QueueMazeSolverMain {
    public static void main (String[] args) {
        new QueueMazeSolver();
    }
}
