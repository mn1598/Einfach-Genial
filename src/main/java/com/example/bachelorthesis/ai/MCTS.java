package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.GameBoard;
import com.example.bachelorthesis.model.State;
import com.example.bachelorthesis.view.Gui;

import java.util.List;

public class MCTS {

    private Gui gui;

    public MCTS(Gui gui) {
        this.gui = gui;
    }

    // todo play whole game until board is full
    //  update gui: gameboard and statistics
    public void start() {

        State initial = new State();

        do{
            initial.printBoard();
            initial = nextMove(initial);
        } while(initial.getNumberOfNext() > 0);

    }

    public State nextMove(State state) {
        long startTime = System.currentTimeMillis();
        long endTime = 2500;

        Tree tree = new Tree(state);
        Node root = tree.root;
        root.getState().setGameBoard(state.getGameBoard());

        while (System.currentTimeMillis() - startTime < endTime) {

            // Selection
            Node selected = selection(root);

            // Expansion
            if(!selected.getState().getGameBoard().isFull()){
                expand(selected);
            }
            Node todo = selected;
            if(todo.getChildren().size() > 0){
                todo = selected.randomChild();
            }

            // Simulation
            int result = simulate(todo);

            // Backpropagation
            backpropagate(todo, result);
        }

        Node best = root.bestChild();
        tree.root = best;
        return best.getState();
    }

    public Node selection(Node root){
        Node node = root;
        while(!node.getChildren().isEmpty()){
            node = node.bestUCTChild();
        }
        return node;
    }

    public void expand(Node node){
        List<State> nextStates = node.getState().nextState();
        for(State state: nextStates){
            Node n1 = new Node(state);
            n1.setParent(node);
            node.getChildren().add(n1);
        }
    }

    public int simulate(Node node){
        Node nodeCopy = new Node(node);
        State stateCopy = new State(node.getState());
        // todo boardStatus?

        while(stateCopy.getNumberOfNext() > 0){
            stateCopy.randomMove(); // light playout
        }
        return stateCopy.getLowestScore();
    }

    public void backpropagate(Node node, int result){
        Node nodeCopy = node;
        while(nodeCopy != null){
            nodeCopy.getState().addVisit();
            nodeCopy.getState().setAvgScore((double) result / (double) nodeCopy.getState().getNumberOfVisits());
            nodeCopy = nodeCopy.getParent();
        }
    }

    class Tree {
        Node root;

        Tree(State state){
            root = new Node(state);
        }
    }

}
