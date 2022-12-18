package com.oop_class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ConstructAVLTree avl = new ConstructAVLTree();
        char choice;
        //avlect for serialize
        Serialize serialize = new Serialize();
        Node rootNode = avl.getRootNode();
        do
        {
            System.out.println("\nSelect an operation:\n");
            System.out.println("1. Insert a node");
            System.out.println("2. Search a node");
            System.out.println("3. Get total number of nodes in AVL Tree");
            System.out.println("4. Is AVL Tree empty?");
            System.out.println("5. Remove all nodes from AVL Tree");
            System.out.println("6. Display AVL Tree in Post order");
            System.out.println("7. Display AVL Tree in Pre order");
            System.out.println("8. Display AVL Tree in In order");
            System.out.println("9. Save AVL Tree In order");
            System.out.println("10. Upload AVL Tree In order");

            int ch = sc.nextInt();
            switch (ch)
            {
                case 1 :
                    System.out.println("Please enter an element to insert in AVL Tree");
                    avl.insertElement( sc.nextInt() );
                    break;
                case 2 :
                    System.out.println("Enter integer element to search");
                    System.out.println(avl.searchElement( sc.nextInt() ));
                    break;
                case 3 :
                    System.out.println(avl.getTotalNumberOfNodes());
                    break;
                case 4 :
                    System.out.println(avl.checkEmpty());
                    break;
                case 5 :
                    avl.removeAll();
                    System.out.println("\nTree Cleared successfully");
                    break;
                case 6 :
                    System.out.println("\nDisplay AVL Tree in Post order");
                    avl.postorderTraversal();
                    break;
                case 7 :
                    System.out.println("\nDisplay AVL Tree in Pre order");
                    avl.preorderTraversal();
                    break;
                case 8 :
                    System.out.println("\nDisplay AVL Tree in In order");
                    avl.inorderTraversal();
                    break;
                case 9 :
                    System.out.println("\nSaving AVL Tree In order");
                    serialize.write(avl.getRootNode());
                    break;
                case 10 :
                    System.out.println("\nReading AVL Tree In order");
                    serialize.read(avl);
                    break;
                default :
                    System.out.println("\n ");
                    break;
            }
            System.out.println("\nPress 'y' or 'Y' to continue \n");
            choice = sc.next().charAt(0);
        } while (choice == 'Y'|| choice == 'y');
    }
}