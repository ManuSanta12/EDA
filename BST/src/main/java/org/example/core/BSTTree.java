package org.example.core;
import java.util.LinkedList;
import java.util.Queue;
public class BSTTree<T extends Comparable<? super T>> implements BSTreeInterface<T> {
//    Node root;
//    @Override
//    public void insert(T myData) {
//        if (myData == null)
//            throw new RuntimeException("element cannot be null");
//
//        root= insert(root, myData);
//    }
//
//    private Node insert(Node currentNode, T myData) {
//        if (currentNode == null) {
//            return new Node(myData);
//        }
//
//        if (myData.compareTo(currentNode.data) <= 0)
//            currentNode.left= insert(currentNode.left, myData);
//        else
//            currentNode.right= insert(currentNode.right, myData);
//
//        return currentNode;
//    }
//
//    @Override
//    public void inOrder() {
//        inorderRec(root);
//    }
//
//    public void inorderRec(Node node) {
//        if (node == null)
//            return;
//        inorderRec(node.left);
//
//        preorderRec(node.left);
//        System.out.print(node.data);
//        preorderRec(node.right);
//    }
//
//    @Override
//    public void preOrder() {
//        preorderRec(root);
//    }
//
//    public void preorderRec(Node node) {
//        if (node == null)
//            return;
//        System.out.print(node.data);
//        preorderRec(node.left);
//        preorderRec(node.right);
//    }
//
//    @Override
//    public void postOrder() {
//        postorderRec(root);
//    }
//
//    public void postorderRec(Node node) {
//        if (node == null)
//            return;
//        preorderRec(node.left);
//        preorderRec(node.right);
//        System.out.print(node.data);
//
//    }
//
//    @Override
//    public NodeTreeInterface<T> getRoot() {
//        return null;
//    }
//
//    @Override
//    public int getHeight() {
//        if(root==null) {
//            return -1;
//        }else {
//            return getHeightRec(root, 1);
//        }
//    }
//    public int getHeightRec(Node node, int a){
//        if(node == null)
//            return a;
//        if(node.left!=null) {
//            return getHeightRec(node.left, a + 1);
//        }else {
//            return getHeightRec(node.right, a+1);
//        }
//    }
//
//    final class Node implements NodeTreeInterface<T> {
//        private T data;
//        private Node left, right;
//
//        public Node(T data){
//            this.data = data;
//            this.left = null;
//            this.right = null;
//        }
//
//        @Override
//        public T getData() {
//            return data;
//        }
//
//        @Override
//        public NodeTreeInterface<T> getLeft() {
//            return left;
//        }
//
//        @Override
//        public NodeTreeInterface<T> getRight() {
//            return right;
//        }
//    }
//
//    public static void main(String[] args) {
//
//    }
private Node root;

    @Override
    public void insert(T myData) {
        if (myData == null)
            throw new RuntimeException("element cannot be null");

        root= insert(root, myData);
    }

    private Node insert(Node currentNode, T myData) {
        if (currentNode == null) {
            return new Node(myData);
        }

        if (myData.compareTo(currentNode.data) <= 0)
            currentNode.left= insert(currentNode.left, myData);
        else
            currentNode.right= insert(currentNode.right, myData);

        return currentNode;
    }


    // version 2
    public void insert2(T myData) {
        if (myData == null)
            throw new RuntimeException("element cannot be null");

        if (root == null)
            root= new Node(myData);
        else
            root.insert(myData);
    }


    @Override
    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node currentNode) {
        if (currentNode != null) {
            System.out.print(currentNode.data + " ");
            preOrder(currentNode.left);
            preOrder(currentNode.right);
        }
    }

    @Override
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node currentNode) {
        if (currentNode != null) {
            postOrder(currentNode.left);
            postOrder(currentNode.right);
            System.out.print(currentNode.data + " ");
        }
    }

    @Override
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node currentNode) {
        if (currentNode != null) {
            inOrder(currentNode.left);
            System.out.print(currentNode.data  + " ");
            inOrder(currentNode.right);
        }
    }

    @Override
    public NodeTreeInterface<T> getRoot() {
        return root;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    // recursiva
    private int getHeight(Node aNode) {
        if (aNode== null)
            return -1;

        return 1 + Math.max( getHeight(aNode.left), getHeight(aNode.right) );
    }

    // version iterativa
    public int height2()  {
        int height = -1;


        if (root == null) {
            return height;
        }

        // create an empty queue and enqueue the root node
        Queue<NodeTreeInterface<T>> queue = new LinkedList<>();
        queue.add(root);

        NodeTreeInterface<T> currentNode;

        // hay elementos?
        while (!queue.isEmpty())
        {
            // calculate the total number of nodes at the current level
            int size = queue.size();

            while (size-- > 0)
            {
                currentNode = queue.remove();

                if (currentNode.getLeft() != null) {
                    queue.add(currentNode.getLeft());
                }

                if (currentNode.getRight() != null) {
                    queue.add(currentNode.getRight());
                }
            }

            // increment height by 1 for each level
            height++;
        }

        return height;
    }

    class Node implements NodeTreeInterface<T> {

        private T data;
        private Node left;
        private Node right;

        public Node(T myData) {
            this.data= myData;
        }


        public T getData() {
            return data;
        }
        public NodeTreeInterface<T> getLeft() {
            return left;
        }

        public NodeTreeInterface<T> getRight() {
            return right;
        }

        private void insert(T myData) {
            if (myData.compareTo(this.data) <= 0)
                if (left == null)
                    left= new Node(myData);
                else
                    left.insert( myData);

            else
            if (right == null)
                right= new Node(myData);
            else
                right.insert( myData);
        }
    }


}
