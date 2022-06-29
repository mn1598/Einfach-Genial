package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.State;

public class Node {

    State state;
    Node root;
    Node leftChild;
    Node rightChild;
    int avgScore;
    int numberOfSimulations;

    public Node(Node root) {
        this.root = root;
        state = root.state;
    }

    public Node(){
        state = new State();
        root = null; // indicates that this node is not just a parent but the overall root of the tree
    }

    public void expand(){
        if(leftChild == null){
            leftChild = new Node(this);
        } else if(rightChild == null) {
            rightChild = new Node(this);
        }
    }

    public double calculateUCT(){
        //classic approach
        if(leftChild != null && rightChild == null){
            return Double.POSITIVE_INFINITY;
        }
        double uct = avgScore + Math.sqrt(2) * Math.sqrt(Math.log(root.numberOfSimulations) / numberOfSimulations);
        return uct;
    }
}
