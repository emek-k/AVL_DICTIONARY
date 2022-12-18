package com.oop_class;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Serialize {

    public Serialize(){}

    public void write(Node rootNode){
        try {
            FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            writeInOrderTraversal(rootNode, o);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
        System.out.println("Writing ended successfully.");
    }

    public void read(ConstructAVLTree avl){
        try {
            FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            //When oi reaches end of the file it throws an IO exception
            for(;;){
                Node node = (Node) oi.readObject();
                avl.insertElement(node.element);
                System.out.println(node.element);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            //System.out.println("Error initializing stream");
            System.out.println("Reading ended successfully.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void writeInOrderTraversal(Node head, ObjectOutputStream o) throws IOException {
        if(head == null)
            return;

        writeInOrderTraversal(head.leftChild,o);
        o.writeObject(head);
        System.out.println("SAVED " + head.element);
        writeInOrderTraversal(head.rightChild,o);
    }

    /*
    public static void main(String[] args) {
        System.out.println("TEST");

        ConstructAVLTree avl = new ConstructAVLTree();

        avl.insertElement(1);
        avl.insertElement(2);
        avl.insertElement(3);

        avl.inorderTraversal();
        Node root = avl.getRootNode();
        write(root);

        read(avl);
    }
     */
}
