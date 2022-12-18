package com.oop_class;

import java.io.Serializable;

class Node implements Serializable
{
    String ang;
    String pol;
    int h;
    Node left;
    Node right;

    public Node()
    {
        left = null;
        right = null;
        ang = "";
        pol = "";
        h = 0;
    }
    public Node(String ang)
    {
        left = null;
        right = null;
        this.ang = ang;
        h = 0;
    }
    public Node(String ang, String pol){
        left = null;
        right = null;
        this.ang = ang;
        this.pol = pol;
        h = 0;
    }
}