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
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements PureQueue<E> {

    /** Index of the front of the queue. */
    private int front;
    /** Index of the rear of the queue. */
    private int rear;
    /** size of the queue. */
    private int size;
    /**Current capacity of the queue. */
    private int capacity;
    /**Default capacity of the queue.*/
    private static int DEFAULT_CAPACITY = 10;
    /** Array to hold the data. */
    private E[] theData;


    /**
     * Default constructor for the Queue
     */
    public ArrayQueue(){
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    /**
     * Constructor for the Queue with a passed in Capacity
     */
    public ArrayQueue(int initCapacity){
        capacity = initCapacity;
        theData = (E[]) new Object[capacity];
        front = 0;
        rear = capacity - 1;
        size = 0;
    }
    /**
     * Determines the number of elements in this PureQueue object.
     *
     * @return the number of elements in this PureQueue object.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Determines if this PureQueue object has no elements.
     *
     * @return true - if this PureQueue object has no elements; otherwise,
     * return false.
     */
    @Override
    public boolean isEmpty() {
        if(theData[front] == null)
        {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Inserts the specified element into this queue.
     *
     * @param item the element to insert.
     * @return <tt>true</tt> if it was possible to add the element to
     * this queue, else <tt>false</tt>
     */
    @Override
    public boolean offer(E item) {
        if(size == capacity){
            reallocate();
        }
        size++;
        rear = (rear + 1) % capacity;
        theData[rear] = item;
        return true;
    }

    /**
     * Retrieves and removes the head of this queue, or <tt>null</tt>
     * if this queue is empty.
     *
     * @return the head of this queue, or <tt>null</tt> if this
     * queue is empty.
     */
    @Override
    public E poll() {
        if(size == 0){
            return null;
        }
        E result = theData[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }

    /**
     * Retrieves and removes the head of this queue.  This method
     * differs from the <tt>poll</tt> method in that it throws an
     * exception if this queue is empty.
     *
     * @return the head of this queue.
     * @throws NoSuchElementException if this queue is empty.
     */
    @Override
    public E remove() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("The queue is empty");
        }
        E result = theData[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * returning <tt>null</tt> if this queue is empty.
     * @return the head of this queue, or <tt>null</tt> if this queue
     * is empty.
     */
    @Override
    public E peek() {
        if(size == 0){
            return null;
        }
        else
            return theData[front];
    }

    /**
     * Retrieves, but does not remove, the head of this queue.	This method
     * differs from the <tt>peek</tt> method only in that it throws an
     * exception if this queue is empty.
     *
     * @return the head of this queue.
     * @throws NoSuchElementException if this queue is empty.
     */
    @Override
    public E element() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("The queue is empty");
        }
        else
            return theData[front];
    }

    public void clear(){
        front = 0;
        rear = capacity - 1;
        size = 0;
    }

    private void reallocate() {
        int newCapacity = 2 * capacity;
        E[] newData = (E[]) new Object[newCapacity];
        int j = front;
        for(int i = 0; i < size; i++){
            newData[i] = theData[j];
            j = (j + 1) % capacity;
        }
        front = 0;
        rear = size -1;
        capacity = newCapacity;
        theData = newData;
    }
    public String toString(){
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            s.append(theData[i] + ", ");
        }
        if (size > 0)
            s.append(theData[size - 1]);
        s.append("]");
        return s.toString();
    }



}
