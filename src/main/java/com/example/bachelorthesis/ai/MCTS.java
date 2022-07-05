package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.GameBoard;
import com.example.bachelorthesis.model.State;
import com.example.bachelorthesis.view.Gui;

// todo mcts implementieren
public class MCTS {

    private Gui gui;

    public MCTS(Gui gui) {
        this.gui = gui;
    }

    public void start() {
    }

    public State nextMove(State state) {
        long startTime = System.currentTimeMillis();
        long endTime = 2500;

        Tree tree = new Tree();
        Node root = tree.root;
        root.getState().setGameBoard(state.getGameBoard());

        while (System.currentTimeMillis() - startTime < endTime) {

            // Selection
            Node selected = selection(root);

            // Expansion
            if(selected.getState().getNumberOfNext() != 0){
                expand(selected);
            }

            // Simulation
            ;

            // Backpropagation
            ;
        }

        Node best = null; // todo search child array for best score
        return best.getState();
    }

    public Node selection(Node root){
        Node node = root;
        // todo select node with uct
        return node;
    }

    public void expand(Node node){
        // todo expand node and let nextStates generate
    }

    class Tree {
        Node root;
    }

}
