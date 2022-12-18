package com.oop_class;

import java.io.*;


public class Serialize {

    public Serialize(){}

    public void write(Node rootNode){
        try {
            FileOutputStream f = new FileOutputStream("myObjects.txt");
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
            FileInputStream fi = new FileInputStream("myObjects.txt");
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            //When oi reaches end of the file it throws an IO exception
            for(;;){
                Node node = (Node) oi.readObject();
                avl.insertElement(node.ang, node.pol);
                System.out.println(node.ang + " " + node.pol);
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
        System.out.println("SAVED " + head.ang + " " + head.pol);
        writeInOrderTraversal(head.rightChild,o);
    }
}
