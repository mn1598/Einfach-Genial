package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.State;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private State state;
    private Node parent;
    private List<Node> children;

    public Node() {
        this.state = new State();
        this.parent = null;
        children = new ArrayList<>();
    }

    public Node(Node node){
        this.state = new State(state);
        this.parent = node.parent;
        this.children = new ArrayList<>();
        node.children.forEach(x -> this.children.add(x));
    }

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }
}
