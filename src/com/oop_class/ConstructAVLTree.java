package com.oop_class;

import static java.lang.Math.max;

class ConstructAVLTree
{
    private Node rootNode;

    //Constructor to set null value to the rootNode
    public ConstructAVLTree()
    {
        rootNode = null;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node root){
        this.rootNode = root;
    }

    private int getHeight(Node node )
    {
        return node == null ? -1 : node.h;
    }

    //create maxNode() method to get the maximum height from left and right node
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
        return Math.max(leftNodeHeight, rightNodeHeight);
    }

    int getBalanceFactor(Node N) {
        if (N == null)
            return 0;
        return getHeight(N.left) - getHeight(N.right);
    }

    public void insertElement(String ang, String pol)
    {
        rootNode = insertElement(ang, pol, rootNode);
    }

    public Node nodeWithMimumValue(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }
    public Node deleteNode(Node node, String ang) {
        // Find the node to be deleted and remove it
        if (node == null)
            return null;
        if (ang.compareTo(node.ang) < 0)
            node.left = deleteNode(node.left, ang);
        else if (ang.compareTo(node.ang) > 0)
            node.right = deleteNode(node.right, ang);
        else {
            if ((node.left == null) || (node.right == null)) {
                Node temp = null;
                if (temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;
                if (temp == null) {
                    node = null;
                } else
                    node = temp;
            } else {
                Node temp = nodeWithMimumValue(node.right);
                node.ang = temp.ang;
                node.pol = temp.pol;
                node.right = deleteNode(node.right, temp.ang);
            }
        }
        if (node == null)
            return node;

        // Update the balance factor of each node and balance the tree
        node.h = max(getHeight(node.left), getHeight(node.right)) + 1;
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) >= 0) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) <= 0) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }
        return node;
    }


    //create insertElement() method to insert data in the AVL Tree recursively
    private Node insertElement(String ang, String pol, Node node) {
        if (node == null)
            node = new Node(ang,pol);
            //insert a node in case when the given element is lesser than the element of the root node
        else if (ang.compareTo(node.ang) < 0)
        {
            node.left = insertElement( ang,pol, node.left );
            if( getHeight( node.left ) - getHeight( node.right ) == 2 )
                if( ang.compareTo(node.left.ang) < 0 )
                    node = rotateLeft( node );
                else
                    node = doubleLeft( node );
        }
        else if( ang.compareTo(node.ang) > 0 )
        {
            node.right = insertElement( ang, pol, node.right );
            if( getHeight( node.right ) - getHeight( node.left ) == 2 )
                if( ang.compareTo(node.ang) > 0)
                    node = rotateRight( node );
                else
                    node = doubleRight( node );
        }
        // if the element is already present in the tree, we will do nothing

        node.h = getMaxHeight( getHeight( node.left ), getHeight( node.right ) ) + 1;

        return node;
    }

    // creating rotateLeft() method to perform rotation of binary tree node with left child
    private Node rotateLeft(Node node2) {
        Node node1 = node2.left;
        node2.left = node1.right;
        node1.right = node2;
        node2.h = getMaxHeight( getHeight( node2.left ), getHeight( node2.right ) ) + 1;
        node1.h = getMaxHeight( getHeight( node1.left ), node2.h ) + 1;
        return node1;
    }

    // creating rotateRight() method to perform rotation of binary tree node with right child
    private Node rotateRight(Node node1) {
        Node node2 = node1.right;
        node1.right = node2.left;
        node2.left = node1;
        node1.h = getMaxHeight( getHeight( node1.left ), getHeight( node1.right ) ) + 1;
        node2.h = getMaxHeight( getHeight( node2.right ), node1.h ) + 1;
        return node2;
    }

    //create doubleLeft() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private Node doubleLeft(Node node3) {
        node3.left = rotateRight( node3.left );
        return rotateLeft( node3 );
    }

    //create doubleRight() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
    private Node doubleRight(Node node1) {
        node1.right = rotateLeft( node1.right );
        return rotateRight( node1 );
    }

    public int getTotalNumberOfNodes()
    {
        return getTotalNumberOfNodes(rootNode);
    }

    private int getTotalNumberOfNodes(Node head) {
        if (head == null)
            return 0;
        else
        {
            int length = 1;
            length = length + getTotalNumberOfNodes(head.left);
            length = length + getTotalNumberOfNodes(head.right);
            return length;
        }
    }

    public void searchElement(String ang)
    {
        searchElement(rootNode, ang);
    }

    private boolean searchElement(Node head, String ang) {
        boolean check = false;
        while ((head != null) && !check)
        {
            if (ang.compareTo(head.ang) < 0)
                head = head.left;
            else if (ang.compareTo(head.ang) > 0)
                head = head.right;
            else
            {
                check = true;
                if(head.pol.equals(""))
                    System.out.println(head.ang + " " + "brak tlumaczen");
                else
                    System.out.println(head.ang + " " + head.pol);
                break;
            }
            check = searchElement(head, ang);
        }
        return check;
    }
    // create inorderTraversal() method for traversing AVL Tree in in-order form
    public void inorderTraversal()
    {
        inorderTraversal(rootNode);
    }
    private void inorderTraversal(Node head) {
        if (head != null)
        {
            inorderTraversal(head.left);
            System.out.println(head.ang + " " + head.pol);
            inorderTraversal(head.right);
        }
    }

    // create preorderTraversal() method for traversing AVL Tree in pre-order form
    public void preorderTraversal()
    {
        preorderTraversal(rootNode);
    }
    private void preorderTraversal(Node head) {
        if (head != null)
        {
            System.out.println(head.ang + " " + head.pol);
            preorderTraversal(head.left);
            preorderTraversal(head.right);
        }
    }

    // create postorderTraversal() method for traversing AVL Tree in post-order form
    public void postorderTraversal()
    {
        postorderTraversal(rootNode);
    }

    private void postorderTraversal(Node head) {
        if (head != null) {
            postorderTraversal(head.left);
            postorderTraversal(head.right);
            System.out.println(head.ang + " " + head.pol);
        }
    }
}