package com.oop_class;


import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ConstructAVLTree avl = new ConstructAVLTree();
        char choice;
        String ang, pol;

        Serialize serialize = new Serialize();
        do
        {
            System.out.println("\nSelect an operation:\n");
            System.out.println("1. Insert a node");
            System.out.println("2. Delete element");
            System.out.println("3. Search a node");
            System.out.println("4. Get total number of nodes in AVL Tree");
            System.out.println("5. Display AVL Tree in Post order");
            System.out.println("6. Display AVL Tree in Pre order");
            System.out.println("7. Display AVL Tree in In order");
            System.out.println("8. Save AVL Tree In order");
            System.out.println("9. Upload AVL Tree In order");

            int ch = sc.nextInt();
            switch (ch) {
                case 1 -> {
                        System.out.println("Please enter an element to insert in AVL Tree");
                        System.out.println("Ang: ");
                        ang = sc.next();
                        System.out.println("Pol: ");
                        pol = sc.next();
                        avl.insertElement(ang, pol);
                }
                case 2 -> {
                    System.out.println("\nDeleting element");
                    System.out.print("Ang: ");
                    ang = sc.next();
                    avl.setRootNode(avl.deleteNode(avl.getRootNode(), ang));
                }
                case 3 -> {
                    System.out.println("Enter word to search");
                    System.out.print("Ang: ");
                    ang = sc.next();
                    avl.searchElement(ang);
                }
                case 4 -> System.out.println(avl.getTotalNumberOfNodes());
                case 5 -> {
                    System.out.println("\nDisplay AVL Tree in Post order");
                    avl.postorderTraversal();
                }
                case 6 -> {
                    System.out.println("\nDisplay AVL Tree in Pre order");
                    avl.preorderTraversal();
                }
                case 7 -> {
                    System.out.println("\nDisplay AVL Tree in In order");
                    avl.inorderTraversal();
                }
                case 8 -> {
                    System.out.println("\nSaving AVL Tree In order");
                    serialize.write(avl.getRootNode());
                }
                case 9 -> {
                    System.out.println("\nReading AVL Tree In order");
                    serialize.read(avl);
                }
                default -> System.out.println("\n ");
            }
            System.out.println("\nPress 'y' or 'Y' to continue \n");
            choice = sc.next().charAt(0);
        } while (choice == 'Y'|| choice == 'y');
    }
}