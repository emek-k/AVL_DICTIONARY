package com.oop_class;
import java.util.*;
/*
class Node{
    int val;
    Node left;
    Node right;

    //Node class constructor
    Node(int val){
        this.val=val;
        left=null;
        right=null;
    }
}
 */

public class ReadWrite {

    //method to serialize a binary Node using preorder traversal
    public static void PreorderSerialize(Node root, ArrayList<Integer> series){
        if(root == null){ //base case
            series.add(Integer.MIN_VALUE);
            return;
        }

        //push the current value
        series.add(root.element);
        //traverse to the left child
        PreorderSerialize(root.leftChild, series);
        //traverse to the right child
        PreorderSerialize(root.rightChild, series);
    }
    static int index;
    //method to deserialize a binary tree using preorder traversal series
    public static Node PreorderDeserialize(ArrayList<Integer> series){
        if(series.size() == index){ //base case
            return null;
        }

        //adds null at relevant positions
        if(series.get(index) == Integer.MIN_VALUE){
            index++;
            return null;
        }

        //add the nodes in a preorder manner current, left, right
        Node root = new Node(series.get(index));
        index++;
        root.leftChild = PreorderDeserialize(series);
        root.rightChild = PreorderDeserialize(series);

        return root;
    }

    public static void main(String[] args) {
        //create a new binary Node
        ConstructAVLTree AVL = new ConstructAVLTree();
        AVL.insertElement(1);
        AVL.insertElement(2);
        AVL.insertElement(3);
        AVL.insertElement(4);
        AVL.insertElement(5);
        AVL.insertElement(6);
        AVL.insertElement(7);

        Node root = AVL.getRootNode();

        AVL.inorderTraversal();


        Main m = new Main();
        ArrayList<Integer> Preorderseries = new ArrayList<>();

        PreorderSerialize(root, Preorderseries);

        System.out.println("Preorderseries follows as");
        for(int i=0;i<Preorderseries.size();i++){
            System.out.print(Preorderseries.get(i)+" ");
        }

        System.out.println("DESERIALIZE");

        //read preorder
        PreorderDeserialize(Preorderseries);
        System.out.println(Arrays.toString(Preorderseries.toArray()));

    }
}
