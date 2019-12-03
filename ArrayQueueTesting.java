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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ArrayQueueTesting {

    //Different types of Queue's for testing
    Queue<String> qSimple = new LinkedList<String>();
    Queue<String> q1 = new LinkedList<String>();
    Queue<String> q2 = new LinkedList<String>();
    Queue<String> qEmpty = new LinkedList<String>();
    Queue<String> qLong = new LinkedList<String>();
    ArrayQueue<Cell> qCell = new ArrayQueue<>();

    @Before
    public void before(){
        qSimple.offer("A");
        qSimple.offer("B");
        qSimple.offer("C");

        q1.offer("A");

        q2.offer("A");
        q2.offer("B");

        qLong.offer("B");
        qLong.offer("B");
        qLong.offer("C");
        qLong.offer("B");
        qLong.offer("K");
        qLong.offer("K");
        qLong.offer("H");
        qLong.offer("B");
        qLong.offer("P");
        qLong.offer("P");
        qLong.offer("P");
    }


    @Test
    public void testOfferListEnd(){
        boolean returnVal = qSimple.offer("Z");
        Assert.assertTrue(returnVal);
        Assert.assertEquals("[A, B, C, Z]", qSimple.toString());
        Assert.assertEquals("A", qSimple.peek());
        Assert.assertEquals( 4, qSimple.size());
    }

    @Test
    public void testOfferListEndSmall(){
        boolean returnVal = q1.offer("K");
        Assert.assertTrue(returnVal);
        Assert.assertEquals("[A, K]", q1.toString());
        Assert.assertEquals("A", q1.peek());
        Assert.assertEquals( 2, q1.size());

    }

    @Test (expected = NoSuchElementException.class)
    public void TestRemoveException(){
        qEmpty.remove();
    }

    @Test
    public void TestPoll(){
        q1.poll();
        Assert.assertEquals("[]", q1.toString());
        Assert.assertEquals(null, q1.peek());
        Assert.assertEquals( 0, q1.size());
    }

    @Test
    public void TestPollDouble(){
        qSimple.poll();
        qSimple.poll();
        Assert.assertEquals("[A]", q1.toString());
        Assert.assertEquals("A", q1.peek());
        Assert.assertEquals( 1, q1.size());
    }

    @Test
    public void TestPollLong(){
        qLong.poll();
        qLong.poll();
        Assert.assertEquals("[C, B, K, K, H, B, P, P, P]", qLong.toString());
        Assert.assertEquals("C", qLong.peek());
        Assert.assertEquals( 9, qLong.size());
    }

    /*
    @Test
    public void TestPoll04(){
        qEmpty.poll();
        Assert.assertEquals("[]", qEmpty.toString());
        Assert.assertEquals(null, qEmpty.peek());
        Assert.assertEquals( 0, qEmpty.size());
    }

     */


    @Test (expected = NoSuchElementException.class)
    public void TestElementException(){
        Assert.assertEquals("[A, B]", q2.toString());
        Assert.assertEquals("A", q2.element());
        q2.poll();
        Assert.assertEquals("B", q2.element());
        q2.poll();
        Assert.assertEquals("?", q2.element());
        Assert.assertEquals( 2, q2.size());

    }

    @Test
    public void TestClearEmpty(){
        qSimple.clear();
        Assert.assertEquals("[]", qSimple.toString());
        Assert.assertEquals(null, qSimple.peek());
        Assert.assertEquals( 0, qSimple.size());

    }

}
