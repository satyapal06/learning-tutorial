package com.invesco;

import java.io.*;

class LinkedList {
    Node head;

    public LinkedList(int item) {
        head = new Node(item);
    }

    //Your code starts here

    public void prepend(int item) {
        Node new_node = new Node(item);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    public int length() {
        Node temp = head;
        int count = 0;
        while (temp != null)
        {
            count++;
            temp = temp.next;
        }
        return count;
    }

    //Your code ends here
}

class Node {
    int value;
    Node next;

    public Node(int item) {
        value = item;
    }
}

class Main {
    public static void main (String[] args) throws java.lang.Exception
    {
        //DO NOT MODIFY
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList(10);
        list.prepend(5);
        list.prepend(22);
        list.prepend(Integer.parseInt(br.readLine()));
        System.out.println("Head: " + list.head.value + ". Length: " + list.length() + ".");
        //DO NOT MODIFY
    }
}

