package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.State;

public class Node {

    State state;
    Node parent;
    Node leftChild;
    Node rightChild;
    int avgScore;
    int numberOfSimulations;

    public Node(Node parent) {
        this.parent = parent;
        state = parent.state;
    }

    public Node(){
        state = new State();
        parent = null; // indicates that this node is not just a parent but the overall root of the tree
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
