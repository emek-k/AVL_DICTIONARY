package com.oop_class;

import static java.lang.Math.max;

class AVLTree
{
    private Node root;
    //domyslnie korzen drzewa jest pusty
    public AVLTree(){
        this.root = null;
    }

    public Node getRoot(){
        return this.root;
    }

    private int getHeight(Node node){
        return node == null ? -1 : node.h;
    }

    private int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public int getNumberOfNodes(Node node){
        if(node == null)
            return 0;
        else {
            int length = 1;
            length += getNumberOfNodes(node.left);
            length += getNumberOfNodes(node.right);
            return length;
        }
    }

    public void searchElement(String ang){
        Node temp = searchElement(ang, this.root);
        if(temp != null){
            System.out.println(temp.ang + " " + temp.pol);
        }else
            System.out.println("Nie znaleziono " + ang + " w slowniku.");
    }

    private Node searchElement(String ang, Node root){
        if(root != null){
            if(ang.compareTo(root.ang) < 0)
                return searchElement(ang, root.left);
            else if(ang.compareTo(root.ang) > 0)
                return searchElement(ang, root.right);
        }
        return root;
    }


    public void insertElement(String ang, String pol){
        this.root = insertElement(ang, pol, this.root);
    }
    private Node insertElement(String ang, String pol, Node node){
        //drzewo jest puste
        if(node == null)
            node = new Node(ang, pol);
            //wstawiamy nowy element do lewego poddrzewa
        else if(ang.compareTo(node.ang) < 0){
            node.left = insertElement(ang, pol, node.left);
            //sprawdzamy czy potrzebne sa rotacje
            if(getBalanceFactor(node) == 2)
                if(ang.compareTo(node.left.ang) < 0)
                    node = rotateLeft(node);
                else {
                    node.left = rotateRight(node.left);
                    node = rotateLeft(node);
                }
        }
        //wstawiamy nowy element do prawego poddrzewa
        else if(ang.compareTo(node.ang) > 0){
            node.right = insertElement(ang, pol, node.right);
            //sprawdzamy czy potrzebne sa rotacje
            if(getBalanceFactor(node) == -2)
                if(ang.compareTo(node.ang) > 0)
                    node = rotateRight(node);
                else {
                    node.right = rotateLeft(node.right);
                    node = rotateRight(node);
                }
        }
        //aktualizujemy wyokosc drzewa
        node.h = max(getHeight(node.left), getHeight(node.right)) + 1;
        return node;
    }

    public void deleteElement(String ang){
        this.root = deleteElement(ang, this.root);
    }
    private Node deleteElement(String ang, Node node){
        //brak elementu
        if(node == null)
            return null;
        //usuwamy element z lewego poddrzewa
        if(ang.compareTo(node.ang) < 0)
            node.left = deleteElement(ang, node.left);
            //usuwamy z prawego poddrzewa
        else if(ang.compareTo(node.ang) > 0)
            node.right = deleteElement(ang, node.right);
        else{
            //czy node ma dzieci
            if((node.left == null) || (node.right == null)){
                Node temp = null;
                if(temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;
                //brak dzieci
                if(temp == null)
                    node = null;
                else //jedno dziecko
                    node = temp;
            }
            //node ma oboje dzieci
            else {
                //szukamy node z najmniejsza wartoscia po prawej stronie
                Node temp = nodeWithMimumValue(node.right);
                node.ang = temp.ang;
                node.pol = temp.pol;
                node.right = deleteElement(temp.ang, node.right);
            }
        }
        if(node == null)
            return node;

        //aktualizujemy BalanceFactor dla kazdego node i balansujemy drzewo
        node.h = max(getHeight(node.left), getHeight(node.right)) + 1;
        int BF = getBalanceFactor(node);

        /*
            BF(N) < -1 & BF(L) <= 0 - rotacja w prawo wokol node
            BF(N) < -1 & BF(L) > 0 - rotacja w lewo wokol node.left i rotacja w prawo node
            BF(N) > 1 & BF(R) >= 0 - rotacja w lewo wokol node
            BF(N) > 1 & BF(R) < 0 - rotacja w prawo wokol node.right i rotracja w lewo node
        */
        if(BF > 1){
            //jezeli BF node.left jest >= 0 to musimy wykonac jedna rotacje w prawo woklo node
            if(getBalanceFactor(node.left) >= 0){
                return rotateRight(node);
            } else {
                //w innym przypadku musimy wykonac rotacje w lewgo wokol node.left, a potem w prawo wokol node
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        if(BF < -1){
            //jezeli BF node.right jest <= 0 to musimy wykonac jedna rotacje w lewo woklo node
            if(getBalanceFactor(node.right) <= 0){
                return rotateLeft(node);
            } else {
                //w innym przypadku musimy wykonac rotacje w prawo wokol node.right, a potem w lewo wokol node
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }
        return node;
    }

    private Node nodeWithMimumValue(Node node){
        Node current = node;
        while(current.left != null)
            current = current.left;
        return current;
    }

    private Node rotateLeft(Node node){
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        node.h = max(getHeight(node.left), getHeight(node.right)) + 1;
        temp.h = max(getHeight(temp.left), node.h) + 1;
        return temp;
    }

    private Node rotateRight(Node node){
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        node.h = max(getHeight(node.left), getHeight(node.right)) + 1;
        temp.h = max(getHeight(temp.right), node.h) + 1;
        return temp;
    }

    public void orderLKP(Node root) {
        if (root != null)
        {
            orderLPK(root.left);
            System.out.println(root.ang + " " + root.pol);
            orderLPK(root.right);
        }
    }

    public void orderKLP(Node root) {
        if (root != null)
        {
            System.out.println(root.ang + " " + root.pol);
            orderKLP(root.left);
            orderKLP(root.right);
        }
    }

    public void orderLPK(Node root) {
        if (root != null) {
            orderLPK(root.left);
            orderLPK(root.right);
            System.out.println(root.ang + " " + root.pol);
        }
    }
}