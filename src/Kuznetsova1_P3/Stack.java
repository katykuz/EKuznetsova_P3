/*
 * Ekaterina Kuznetsova
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package Kuznetsova1_P3;

/**
 * This class creates a stack class to represent a stack of cards in
 * this program; it includes the basic methods a stack class would have.
 *
 * @author Ekaterina Kuznetsova
 * @version 1.0
 */
public class Stack<T>{

    /**
     The Node class is used to implement the
     linked list.
     */
    private class Node
    {
        //declare the double variable of the element
        T value;

        //declare the succeeding node
        Node next;

        /**
         * This node constructor takes in parameters for the value of the
         * element and the succeeding node.
         *
         * @param val
         * @param n
         */
        Node(T val, Node n)
        {
            //initiate the double variable
            value = val;

            //initiate the the next Node
            next = n;
        }
    }


    private Node top;  // Top of the stack

    private int count;  //counter for size of stack


    /**
     * Stack constructor sets the top node to null and initializes the count
     * integer to zero when an instance of the class is created
     */
    public void Stack() {

        //initiate the top of the stack
        top = null;

        //initiate the count variable
        count = 0;
    }


    /**
     The empty method checks for an empty stack.
     @return true if stack is empty, false otherwise.
     */
    public boolean empty() {

        //return true if the list is empty, false if it is not
        return top == null;
    }


    /**
     * The push method adds a new item to the stack and increments the count.
     *
     * @param number The item to be pushed onto the stack.
     */
    public void push(T number)
    {
        //add a new node to the top of the stack
        top = new Node(number, top);

        //increment count to reflect added node
        count++;
    }


    /**
     * The Pop method removes the value at the top of the stack and decrements
     * the count.
     *
     * @return The value at the top of the stack.
     * Exception is thrown if not enough numbers were entered
     */
    public T pop()
    {
        //if stack is empty, throw an exception
        if (empty())
            throw new IllegalArgumentException("Not enough numbers.");

        //if not empty, remove and return the top value
        else
        {
            //set the top value to a temporary variable
            T retValue = top.value;

            //update the top value to the second to last one to remove the top
            top = top.next;

            //decrement the count to reflect removed value
            count--;

            //return the removed value
            return retValue;
        }
    }


    /**
     * The peek method returns the top value on the stack.
     *
     * @return The value at the top of the stack.
     */
    public T peek()
    {
        //if the stack is empty, throw exception
        if (empty())
            throw new IllegalArgumentException("Empty stack!");

        //otherwise, return the value at the top of the stack
        else
            return top.value;
    }


    /**
     * The size method returns the number of content/'items/ currently on the
     * stack, throws an empty stack exception if there is nothing on the stack
     *
     * @return The count of items on the stack
     */
    public int size() {

        //if the stack is empty, throw exception
        if (empty())
            throw new IllegalArgumentException("Empty stack!");

        //otherwise return the count which represents the amount of content
        //on the stack
        else
            return count;
    }


    /**
     * The toString method concatenates all strings
     * in the queue to give a string representation
     * of the contents of the queue.
     *
     * @return string representation of this queue.
     */
    public String toString() {

        //create a string builder object
        StringBuilder sBuilder = new StringBuilder();

        //initialize reference node for traversing
        Node p = top;

        //traverse through the stack and append all values
        while (p != null) {

            //build the string and append
            sBuilder.append(p.value + " ");

            //advance the reference node
            p = p.next;
        }

        //return the string
        return sBuilder.toString();
    }
}
