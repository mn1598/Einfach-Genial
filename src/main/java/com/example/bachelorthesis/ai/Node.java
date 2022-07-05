package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {

    private State state;
    private Node parent;
    private List<Node> children;

    public Node(State state) {
        this.state = state;
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

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node randomChild(){
        // todo return a random child
        Random random = new Random();
        return children.get(random.nextInt(children.size()));
    }

    public Node bestChild(){
        Node best = this;
        if(children.size() > 0){
            best = children.get(0);
            for(Node child: children){
                if(best.state.getLowestScore() < child.state.getLowestScore()){
                    best = child;
                }
            }
        }
        return best;
    }

    public Node bestUCTChild(){
        if(children.isEmpty()){
            return this;
        }
        Node bestChild = null;
        double bestUCT = Integer.MIN_VALUE;
        for(Node child: children){
            if(child.calculateUCT() > bestUCT){
                bestUCT = child.calculateUCT();
                bestChild = child;
            }
        }
        return bestChild;
    }

    public double calculateUCT(){
        if(state.getNumberOfVisits() == 0){
            return Integer.MAX_VALUE;
        }
        return state.getAvgScore() + Math.sqrt(2) * Math.sqrt(Math.log(parent.state.getNumberOfVisits()) / state.getNumberOfVisits());
    }
}
