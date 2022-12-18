package com.oop_class;

import java.io.Serializable;

class Node implements Serializable
{
    String ang;
    String pol;
    int h;
    Node leftChild;
    Node rightChild;

    public Node()
    {
        leftChild = null;
        rightChild = null;
        ang = "";
        pol = "";
        h = 0;
    }
    public Node(String ang)
    {
        leftChild = null;
        rightChild = null;
        this.ang = ang;
        h = 0;
    }
    public Node(String ang, String pol){
        leftChild = null;
        rightChild = null;
        this.ang = ang;
        this.pol = pol;
        h = 0;
    }
}