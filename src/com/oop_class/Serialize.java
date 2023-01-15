/*
Wczytywanie odczytuje obiekt z pliku i dodaje go do przekazanego drzewa.
Zapis zapisuje w kolejnosci LKP do pliku wszystkie obiekty drzewa.
 */
package com.oop_class;

import java.io.*;


public class Serialize {

    private String fileName = "myObjects.bin";

    public Serialize(){}

    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public void write(Node rootNode){
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Zapis drzewo do pliku
            orderLKP(rootNode, o);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("Pliku "+ fileName+" nie znaleziono.");
        } catch (IOException e) {
            System.out.println("Blad inicjalizacji strumienia.");
        }
        System.out.println("Zapisywanie zakonczone pomyslnie.");
    }

    public void read(AVLTree avl, String fileName){
        try {
            FileInputStream fi = new FileInputStream(fileName);
            ObjectInputStream oi = new ObjectInputStream(fi);

            //Wczytaj obiekty
            //Kiedy oi osiagnie koniec pliku zostanie wyrzucony blad IO exception
            for(;;){
                Node node = (Node) oi.readObject();
                avl.insertElement(node.ang, node.pol);
                System.out.println("LOADED " + node.ang + " " + node.pol);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Pliku "+fileName+" nie znaleziono.");
        } catch (IOException e) {
            System.out.println("Wczytywanie zakonczone pomyslnie.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void orderLKP(Node head, ObjectOutputStream o) throws IOException {
        if(head == null)
            return;

        orderLKP(head.left,o);
        o.writeObject(head);
        System.out.println("SAVED " + head.ang + " " + head.pol);
        orderLKP(head.right,o);
    }
}
