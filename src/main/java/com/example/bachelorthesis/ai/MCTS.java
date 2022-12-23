package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.controller.Controller;
import com.example.bachelorthesis.model.State;
import javafx.application.Platform;

import java.util.List;
import java.util.Random;

public class MCTS {

    private final int END_TIME;
    private Controller controller;
    private State initial;

    public MCTS(Controller controller, int duration_of_simulation) {
        this.END_TIME = duration_of_simulation;
        this.controller = controller;
    }

    public void randomGame() {
        initial = new State();
        long time = System.currentTimeMillis();
        Random random = new Random();
        List<State> todo = initial.nextState();
        initial.printBoard();
        while (!todo.isEmpty()) {
            System.out.println("next move");
            System.out.println(initial.getCurrentStone());
            this.initial = todo.get(random.nextInt(todo.size()));
            initial.printBoard();
            initial.getColorScores().forEach((x, y) -> System.out.println(x + ": " + y));
            System.out.println("----------------------------");
            todo = initial.nextState();
            updateGui();
        }
        Controller.results.add(initial.getLowestScore());
        time = System.currentTimeMillis() - time;
        double finalTime = (double) time / 1000;
        Platform.runLater(()->{
            controller.update(finalTime);
            controller.updateExperimentResult(finalTime);
        });
    }

    private void updateGui() {
        Platform.runLater(()-> controller.update(initial.getGameBoard(), initial.getColorScores()));
    }

    public void start() {

        initial = new State();
        initial.printBoard();
        long startTime = System.currentTimeMillis();
        do {
            System.out.println("next move");
            initial = nextMove(initial);
            initial.printBoard();
            initial.getColorScores().forEach((x, y) -> System.out.println(x + ": " + y));
            System.out.println("----------------------------");
            updateGui();
        } while (!initial.getGameBoard().isFull());
        Controller.results.add(initial.getLowestScore());
        long endTime = System.currentTimeMillis();
        double runningTime = (double) (endTime - startTime) / 1000;
        Platform.runLater(()->{
            controller.update(runningTime);
            controller.updateExperimentResult(runningTime);
        });
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
        System.out.println(tree.root.getState().getCurrentStone());
        Node best = root.bestChild();
        tree.root = best;
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
