package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.State;

import java.util.ArrayList;
import java.util.List;

public class Node {

    State state;
    Node parent;
    ArrayList<Node> children;
    int avgScore;

    public Node(Node parent) {
        this.parent = parent;
        state = parent.state;
        children = new ArrayList<>();
    }

    public Node(){
        state = new State();
        parent = null; // indicates that this node is not just a parent but the overall root of the tree
        children = new ArrayList<>();
    }

    public void expand(List<State> nextStates){
        for(State state: nextStates){
            Node child = new Node(this);
            child.state = state;
            this.children.add(child);
        }
    }

    public double calculateUCT(){
        if(state.numberOfVisits == 0){
            return Integer.MAX_VALUE;
        }
        return avgScore + Math.sqrt(2) * Math.sqrt(Math.log(parent.state.numberOfVisits) / state.numberOfVisits);
    }
}
