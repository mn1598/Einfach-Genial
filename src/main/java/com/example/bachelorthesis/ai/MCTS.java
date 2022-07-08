package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.controller.Controller;
import com.example.bachelorthesis.model.State;
import com.example.bachelorthesis.view.Gui;

import java.util.List;
import java.util.Random;

// todo implement experiment
public class MCTS {

    private final int END_TIME = 3000;
    private Gui gui;
    private State initial;

    public MCTS(Gui gui) {
        this.gui = gui;
    }

    public void randomGame() {
        initial = new State();
        double time = System.currentTimeMillis();
        Random random = new Random();
        initial.printBoard();
        List<State> todo = initial.nextState();
        System.out.println(todo.size());
        while (!todo.isEmpty()) {
            this.initial = todo.get(random.nextInt(todo.size()));
            initial.printBoard();
            todo = initial.nextState();
            System.out.println(todo.size());
        }
        System.out.println("Game finished");
        time = System.currentTimeMillis() - time;
        gui.getPane().updateBoard(initial.getGameBoard());
        gui.getSidePane().setLabelScore(initial.getColorScores());
        gui.getSidePane().updateTime(time);
    }

    // todo update gui: gameboard and statistics
    public void start() {

        initial = new State();
        long startTime = System.currentTimeMillis();
        do {
            System.out.println("next move");
            initial.printBoard();
            initial = nextMove(initial);
            gui.getPane().updateBoard(initial.getGameBoard());
            gui.getSidePane().scores = initial.getColorScores();

        } while (!initial.getGameBoard().isFull());
        long endTime = System.currentTimeMillis();
        double runningTime = (double) (endTime - startTime) / 1000;
        gui.getSidePane().updateTime(runningTime);

    }

    public int experiment(){
        return 0;
    }

    public State nextMove(State state) {
        long startTime = System.currentTimeMillis();
        long endTime = END_TIME;

        Tree tree = new Tree(state);
        Node root = tree.root;
        root.getState().setGameBoard(state.getGameBoard());

        while (System.currentTimeMillis() - startTime < endTime) {

            // Selection
            Node selected = selection(root);

            // Expansion
            if (!selected.getState().getGameBoard().isFull()) {
                expand(selected);
            }
            Node todo = selected;
            if (todo.getChildren().size() > 0) {
                todo = selected.randomChild();
            }

            // Simulation
            int result = simulate(todo);

            // Backpropagation
            backpropagate(todo, result);
        }
        Node best = root.bestChild();
        tree.root = best;
        best.getState().getColorScores().forEach((x, y) -> System.out.println(x + ": " + y));
        return best.getState();
    }

    public Node selection(Node root) {
        Node node = root;
        while (!node.getChildren().isEmpty()) {
            node = node.bestUCTChild();
        }
        return node;
    }

    public void expand(Node node) {
        List<State> nextStates = node.getState().nextState();
        for (State state : nextStates) {
            Node n1 = new Node(state);
            n1.setParent(node);
            node.getChildren().add(n1);
        }
    }

    public int simulate(Node node) {
        Node nodeCopy = new Node(node);
        State stateCopy = new State(node.getState());

        while (!stateCopy.getGameBoard().isFull() || stateCopy.getNumberOfNext() > 0) {
            stateCopy.randomMove(); // light playout
        }
        return stateCopy.getLowestScore();
    }

    public void backpropagate(Node node, int result) {
        Node nodeCopy = node;
        while (nodeCopy != null) {
            nodeCopy.getState().addVisit();
            double visits = nodeCopy.getState().getNumberOfVisits();
            nodeCopy.getState().setAvgScore((nodeCopy.getState().getAvgScore() * (visits - 1) + (double) result) / visits);
//            nodeCopy.getState().setAvgScore(result / nodeCopy.getState().getNumberOfVisits());
            nodeCopy = nodeCopy.getParent();
        }
    }

    class Tree {
        Node root;

        Tree(State state) {
            root = new Node(state);
        }
    }

}
