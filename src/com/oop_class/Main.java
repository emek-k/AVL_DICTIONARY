package com.oop_class;


import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        AVLTree avl = new AVLTree();
        String ang, pol, fileName;
        int ch;
        Serialize serialize = new Serialize();
        do
        {
            System.out.println("\n########### AVL DICTIONARY ############\n");
            System.out.println("1. Dodaj slowo");
            System.out.println("2. Usun slowo");
            System.out.println("3. Wyszukaj slowo");
            System.out.println("4. Rozmiar slownika");
            System.out.println("5. Wyswietl w kolejnosci LKP");
            System.out.println("6. Wyswietl w kolejnosci LPK");
            System.out.println("7. Display w kolejnosci KLP");
            System.out.println("8. Zapisz slownik do pliku");
            System.out.println("9. Wczytaj slownik z pliku");
            System.out.println("0. Wyjscie");

            ch = sc.nextInt();
            switch (ch) {
                case 1 -> {
                    System.out.println("Wstaw nowe slowo.");
                    System.out.print("Ang: ");
                    ang = sc.next();
                    System.out.print("Pol: ");
                    pol = sc.next();
                    avl.insertElement(ang, pol);
                }
                case 2 -> {
                    System.out.println("\nUsuwanie slowa.");
                    System.out.print("Ang: ");
                    ang = sc.next();
                    avl.deleteElement(ang);
                }
                case 3 -> {
                    System.out.println("Wyszukiwanie slowa po angielsku.");
                    System.out.print("Ang: ");
                    ang = sc.next();
                    avl.searchElement(ang);
                }
                case 4 -> {
                    System.out.print("Rozmiar slownika ");
                    System.out.println(avl.getNumberOfNodes(avl.getRoot()));
                }
                case 5 -> {
                    System.out.println("\nSlownik w kolejnosci LKP");
                    avl.orderLKP(avl.getRoot());
                }
                case 6 -> {
                    System.out.println("\nSlownik w kolejnosci LPK");
                    avl.orderLPK(avl.getRoot());
                }
                case 7 -> {
                    System.out.println("\nSlownik w kolejnosci KLP");
                    avl.orderKLP(avl.getRoot());
                }
                case 8 -> {
                    System.out.println("\nZapisywanie slownika w kolejnosci LKP.");
                    System.out.println("Podaj nazwe pliku (.bin)");
                    fileName = sc.next();
                    serialize.setFileName(fileName);
                    serialize.write(avl.getRoot());
                }
                case 9 -> {
                    System.out.println("\nWczytywanie slownika");
                    System.out.println("Podaj nazwe pliku z ktorego zostana wczytane dane (.bin).");
                    System.out.println("Przyklad: myObjects.bin");
                    fileName = sc.next();
                    serialize.read(avl, fileName);
                }
                default -> System.out.println("\n ");
            }
        } while (ch != 0);
        System.out.println("Wychodzenie z programu...");
    }
}