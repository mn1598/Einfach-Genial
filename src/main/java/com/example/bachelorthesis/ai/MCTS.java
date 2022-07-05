package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.GameBoard;
import com.example.bachelorthesis.model.State;
import com.example.bachelorthesis.view.Gui;

public class MCTS {

    private Gui gui;

    public MCTS(Gui gui) {
        this.gui = gui;
    }

    // todo play whole game until board is full
    //  update gui: gameboard and statistics
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
            Node todo = selected;
            if(todo.getChildren().size() > 0){
                // todo get random child node of todo
            }
            int result = simulate(todo);

            // Backpropagation
            backpropagate(todo, result);
        }

        Node best = null; // todo search tree from rootNode for Node with best score, this node is winner
        tree.root = best;
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

    public int simulate(Node node){
        // todo simulated light playout
        return 0;
    }

    public void backpropagate(Node node, int result){
        // todo backpropagate
    }

    class Tree {
        Node root;
    }

}
