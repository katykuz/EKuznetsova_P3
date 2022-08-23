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
            //initiate the double variable and the next Node
            value = val;
            next = n;
        }
    }

    private Node top = null;  // Top of the stack



    /**
     * Stack constructor sets the top node to null and initializes the count
     * integer to zero when an instance of the class is created
     */
    public void Stack() {
        //initiate the top of the stack and the count variable
        top = null;

    }

    /**
     The empty method checks for an empty stack.
     @return true if stack is empty, false otherwise.
     */
    public boolean empty()
    {
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
        top = new Node(number, top);

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
        if (empty())
            throw new IllegalArgumentException("Not enough numbers.");
        else
        {
            T retValue = top.value;
            top = top.next;
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
     * The toString method concatenates all strings
     * in the queue to give a string representation
     * of the contents of the queue.
     *
     * @return string representation of this queue.
     */

    public String toString() {
        StringBuilder sBuilder = new StringBuilder();

        // Walk down the list and append all values
        Node p = top;
        while (p != null) {
            sBuilder.append(p.value + " ");
            p = p.next;
        }
        return sBuilder.toString();
    }

}
