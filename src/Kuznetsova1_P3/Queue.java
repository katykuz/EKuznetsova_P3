/*
 * Ekaterina Kuznetsova
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package Kuznetsova1_P3;

/**
 * The Queue class is a blueprint for the queue objects in this program
 * and provides the necessary methods to successfully store
 *
 * @author Ekaterina Kuznetsova
 * @version 1.0
 */
public class Queue<T> {

    /**
     * The Node class holds the functionality of storing values to a node and
     * linking nodes forwards and backwards.
     */
    private class Node {

        //holds the Node value
        T value;

        //links a node to the next one
        private Node next;

        //links a node to the previous one
        private Node prev;


    /**
     * The constructor takes in a value and assigns it null node links.
     * @param val Value to be added to a node
     * @param n Reference node to link (next node)
     * @param p Reference node to link (previous node)
     */
    public Node(T val, Node n, Node p) {

        //variable to hold the value of the node
        value = val;

        //successor pointer
        next = n;

        //precedent pointer
        prev = p;
        }
    }

    private Node head;      //head/front of the list
    private Node tail;      //tail/rear of the list


    /**
     * The copy method creates a new queue and makes a deep copy of the
     * current queue onto the new queue through the enqueue method.
     *
     * @return copyQueue    the new queue holding copied values
     */
    public Queue<T> copy(){

        //create a new queue to be the copy
        Queue<T> copyQueue = new Queue<T>();

        //create a reference to the top/head of the queue
        Node p = this.head;

        //traverse through the entire queue
        while(p != null){

            //put the value of each node onto the new queue through the
            //enqueue method
            copyQueue.enqueue(p.value);

            //advance the reference
            p = p.next;

        }
        //return the new queue
        return copyQueue;
    }


    /**
     * The empty method returns true/false whether or not the head/top of the
     * queue is null
     *
     * @return true/false   depending on head content/lack of
     */
    public Boolean empty() {

        //return true if queue is empty, return false otherwise
        return head == null;
    }


    /**
     * The method peek returns value at the head of the queue.
     *
     * @return item at head of queue.
     * @throws IllegalArgumentException When the queue is empty.
     */
    public T peek() throws IllegalArgumentException {

        //if queue is empty, throw an exception
        if (empty())
            throw new IllegalArgumentException("Error! Empty queue!");

        //otherwise return the top/head value
        else
            return head.value;
    }


    /**
     * The dequeue method removes the value at the head/top of the queue by
     * removing pointers that would go to it and returns that value to the
     * function that called it.
     *
     * @return returnVal    variable holding the removed value
     * @throws IllegalArgumentException if queue is empty
     */
    public T dequeue() throws IllegalArgumentException {

        //if the queue is empty, through an error.
        if (empty())
            throw new IllegalArgumentException("Error! Empty queue!");

        //if not empty
        else {

            //create a variable to hold the head value
            T returnVal = head.value;

            //update the head value to be head.next
            head = head.next;

            //if the list is now empty, set the tail to null
            if (head == null)
                tail = null;

            //if the list is not empty, remove the reverse pointer
            else {
                head.prev = null;
            }

            //return the value that was removed
            return returnVal;
        }
    }


    /**
     * The enqueue method takes in a RenderCommand variable and puts on at the
     * end of the queue (at the tail) and updates the pointers to include the
     * new value.
     *
     * @param num    RenderCommand value that will go into the node
     */
    public void enqueue(T num) {

        //if the queue is empty
        if (empty()) {

            //create a new node at the head of the queue
            head = new Node (num, null, null);

            //set the tail equal to the head
            tail = head;

        //if the queue is not empty
        } else {

            //add a new node to the end of the queue to create a pointer from the current
            tail.next = new Node (num, null, tail);

            //update tail.next to be the new tail
            tail = tail.next;
        }
    }

    /**
     * The append method takes in a queue and "appends" or adds it to the end
     * of the current queue through the enqueue method
     *
     * @param q     the queue to be added to the current queue
     */
    public void append(Queue<T> q) {

        //create a reference node and set it to the head of the queue
        //to be added (q)
        Node p = q.head;

        //traverse through the whole queue/'q' and add each node as a
        //new node at the tail of the current queue
        while (p != null) {

            //add to tail of current queue
            this.enqueue(p.value);

            //advance the reference node
            p = p.next;
        }
    }

    /**
     * The toString method concatenates all strings in the queue to give a
     * string representation of the contents of the queue.
     *
     * @return string representation of this queue.
     */
    public String toString() {

        //initialize variable to keep track of cards and spaces printed
        int maxPrint = 0;

        //create a string builder object
        StringBuilder sBuilder = new StringBuilder();

        //initialize reference node for traversing
        Node p = head;

        //traverse through the queue and append all values
        while (p != null) {

            //build the string and append (max 5 characters printed)
            sBuilder.append(p.value + " | ");

            //advance the reference node and print counter
            p = p.next;
            maxPrint++;

            //start a new line to not exceed 80 characters (80/5 = 16)
            if (maxPrint == 16) {

                //print new line character
                sBuilder.append("\n");
                maxPrint = 0;
            }
        }

        //return the string
        return sBuilder.toString();
    }
}

