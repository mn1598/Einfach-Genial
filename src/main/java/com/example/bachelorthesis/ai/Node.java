package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.State;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Node {

    State state;
    Node parent;
    ArrayList<Node> children;

    public Node(Node parent) {
        this.parent = parent;
        state = new State(parent.state);
        children = new ArrayList<>();
    }

    public Node(State state){
        this.state = state;
        parent = null; // indicates that this node is not just a parent but the overall root of the tree
        children = new ArrayList<>();
    }

    public void expand(){
        List<State> nextStates = state.nextState();
        System.out.println(nextStates.size());
        for(State state: nextStates){
            Node child = new Node(this);
            child.state = state;
            this.children.add(child);
        }
//        if(state.terminal){
//            return;
//        }
//        if(children.size() < state.numberOfNext){
//            children.add(new Node(this));
//        }
    }

    public double calculateUCT(){
        if(state.numberOfVisits == 0){
            return Integer.MAX_VALUE;
        }
        return state.avgScore + Math.sqrt(2) * Math.sqrt(Math.log(parent.state.numberOfVisits) / state.numberOfVisits);
    }

    public Node deepCopy(){
        Node node = new Node(new State(this.state));
        node.parent = this.parent;
        for(Node child: this.children){
            node.children.add(child);
        }
        return node;
    }
}
