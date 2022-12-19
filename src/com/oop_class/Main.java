package com.oop_class;


import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ConstructAVLTree avl = new ConstructAVLTree();
        String ang, pol;
        int ch;
        Serialize serialize = new Serialize();
        do
        {
            System.out.println("\n########### AVL DICTIONARY ############\n");
            System.out.println("1. Insert a word");
            System.out.println("2. Delete a word");
            System.out.println("3. Search for a word");
            System.out.println("4. Get size of Dictionary");
            System.out.println("5. Display Dictionary in In order");
            System.out.println("6. Display Dictionary in Post order");
            System.out.println("7. Display Dictionary in Pre order");
            System.out.println("8. Save AVL Tree Dictionary");
            System.out.println("9. Upload AVL Tree Dictionary");
            System.out.println("0. Exit");

            ch = sc.nextInt();
            switch (ch) {
                case 1 -> {
                    System.out.println("Insert a new word.");
                    System.out.print("Ang: ");
                    ang = sc.next();
                    System.out.print("Pol: ");
                    pol = sc.next();
                    avl.insertElement(ang, pol);
                }
                case 2 -> {
                    System.out.println("\nDeleting word.");
                    System.out.print("Ang: ");
                    ang = sc.next();
                    avl.deleteElement(ang);
                }
                case 3 -> {
                    System.out.println("Enter word to search");
                    System.out.print("Ang: ");
                    ang = sc.next();
                    avl.searchElement(ang);
                }
                case 4 -> {
                    System.out.print("Size of Dictionary ");
                    System.out.println(avl.getTotalNumberOfNodes());
                }
                case 5 -> {
                    System.out.println("\nDisplay AVL Dictionary in In order");
                    avl.inorderTraversal();
                }
                case 6 -> {
                    System.out.println("\nDisplay AVL Dictionary in Post order");
                    avl.postorderTraversal();
                }
                case 7 -> {
                    System.out.println("\nDisplay AVL Dictionary in Pre order");
                    avl.preorderTraversal();
                }
                case 8 -> {
                    System.out.println("\nSaving AVL Dictionary In order");
                    serialize.write(avl.getRootNode());
                }
                case 9 -> {
                    System.out.println("\nReading AVL Dictionary In order");
                    serialize.read(avl);
                }
                default -> System.out.println("\n ");
            }
        } while (ch != 0);
        System.out.println("Exiting program...");
    }
}