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

    public void insertElement(String ang, String pol)
    {
        rootNode = insertElement(ang, pol, rootNode);
    }


    public Node nodeWithMimumValue(Node node) {
        Node current = node;
        while (current.leftChild != null)
            current = current.leftChild;
        return current;
    }
    public Node deleteNode(Node node, String ang) {
        // Find the node to be deleted and remove it
        if (node == null)
            return null;
        if (ang.compareTo(node.ang) < 0)
            node.leftChild = deleteNode(node.leftChild, ang);
        else if (ang.compareTo(node.ang) > 0)
            node.rightChild = deleteNode(node.rightChild, ang);
        else {
            if ((node.leftChild == null) || (node.rightChild == null)) {
                Node temp = null;
                if (temp == node.leftChild)
                    temp = node.rightChild;
                else
                    temp = node.leftChild;
                if (temp == null) {
                    node = null;
                } else
                    node = temp;
            } else {
                Node temp = nodeWithMimumValue(node.rightChild);
                node.ang = temp.ang;
                node.pol = temp.pol;
                node.rightChild = deleteNode(node.rightChild, temp.ang);
            }
        }
        if (node == null)
            return node;

        // Update the balance factor of each node and balance the tree
        node.h = max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.leftChild) >= 0) {
                return rotateWithRightChild(node);
            } else {
                node.leftChild = rotateWithLeftChild(node.leftChild);
                return rotateWithRightChild(node);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.rightChild) <= 0) {
                return rotateWithLeftChild(node);
            } else {
                node.rightChild = rotateWithRightChild(node.rightChild);
                return rotateWithLeftChild(node);
            }
        }
        return node;
    }
    int getBalanceFactor(Node N) {
        if (N == null)
            return 0;
        return getHeight(N.leftChild) - getHeight(N.rightChild);
    }

    private int getHeight(Node node )
    {
        return node == null ? -1 : node.h;
    }

    //create maxNode() method to get the maximum height from left and right node
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
        return Math.max(leftNodeHeight, rightNodeHeight);
    }

    //create insertElement() method to insert data in the AVL Tree recursively
    private Node insertElement(String ang, String pol, Node node) {
        if (node == null)
            node = new Node(ang,pol);
            //insert a node in case when the given element is lesser than the element of the root node
        else if (ang.compareTo(node.ang) < 0)
        {
            node.leftChild = insertElement( ang,pol, node.leftChild );
            if( getHeight( node.leftChild ) - getHeight( node.rightChild ) == 2 )
                if( ang.compareTo(node.leftChild.ang) < 0 )
                    node = rotateWithLeftChild( node );
                else
                    node = doubleWithLeftChild( node );
        }
        else if( ang.compareTo(node.ang) > 0 )
        {
            node.rightChild = insertElement( ang, pol, node.rightChild );
            if( getHeight( node.rightChild ) - getHeight( node.leftChild ) == 2 )
                if( ang.compareTo(node.ang) > 0)
                    node = rotateWithRightChild( node );
                else
                    node = doubleWithRightChild( node );
        }
        // if the element is already present in the tree, we will do nothing

        node.h = getMaxHeight( getHeight( node.leftChild ), getHeight( node.rightChild ) ) + 1;

        return node;
    }

    // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child
    private Node rotateWithLeftChild(Node node2) {
        Node node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        node2.h = getMaxHeight( getHeight( node2.leftChild ), getHeight( node2.rightChild ) ) + 1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), node2.h ) + 1;
        return node1;
    }

    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child
    private Node rotateWithRightChild(Node node1) {
        Node node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), getHeight( node1.rightChild ) ) + 1;
        node2.h = getMaxHeight( getHeight( node2.rightChild ), node1.h ) + 1;
        return node2;
    }

    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private Node doubleWithLeftChild(Node node3) {
        node3.leftChild = rotateWithRightChild( node3.leftChild );
        return rotateWithLeftChild( node3 );
    }

    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
    private Node doubleWithRightChild(Node node1) {
        node1.rightChild = rotateWithLeftChild( node1.rightChild );
        return rotateWithRightChild( node1 );
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
            length = length + getTotalNumberOfNodes(head.leftChild);
            length = length + getTotalNumberOfNodes(head.rightChild);
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
                head = head.leftChild;
            else if (ang.compareTo(head.ang) > 0)
                head = head.rightChild;
            else
            {
                check = true;
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
            inorderTraversal(head.leftChild);
            System.out.println(head.ang + " " + head.pol);
            inorderTraversal(head.rightChild);
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
            preorderTraversal(head.leftChild);
            preorderTraversal(head.rightChild);
        }
    }

    // create postorderTraversal() method for traversing AVL Tree in post-order form
    public void postorderTraversal()
    {
        postorderTraversal(rootNode);
    }

    private void postorderTraversal(Node head) {
        if (head != null) {
            postorderTraversal(head.leftChild);
            postorderTraversal(head.rightChild);
            System.out.println(head.ang + " " + head.pol);
        }
    }
}