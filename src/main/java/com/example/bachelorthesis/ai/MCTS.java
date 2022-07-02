package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.ColorEnum;
import com.example.bachelorthesis.model.Game;
import com.example.bachelorthesis.model.State;
import com.example.bachelorthesis.view.Gui;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class MCTS {

    Gui gui;
    Node root;
    Random random = new Random();
    Game game;
    State initial;

    /*
     * create a new MCTS Object linked to gui for displaying information
     */
    public MCTS(Gui gui) {
        this.gui = gui;
        root = new Node();
        game = new Game();
        initial = new State();
        //randomGame(); // starts a game with random moves
    }

    private void randomGame() {
        initial.printBoard();
        List<State> todo = initial.nextState();
        while (!todo.isEmpty()) {
            State next = todo.get(random.nextInt(todo.size()));
            this.initial = next;
            initial.printBoard();
            todo = initial.nextState();
            gui.getPane().updateBoard(initial.gameBoard);
            gui.getSidePane().setLabelText(0, 0, 0, initial.colorScores);
        }
        System.out.println("Game finished");
    }

    // gets called by controller
    public void start() {
        int run = 1;
        long endTime = 25000;
        Node node = root;
        List<State> nextStates = node.state.nextState();
        while (nextStates.size() > 0) {
            long startTime = System.currentTimeMillis();

            while (System.currentTimeMillis() - startTime < endTime) {
                System.out.println("Start " + run + ". Run!");
                // Selection
                Node best = selection(node);
                System.out.println("Selection done!");

                // Expansion
                best.expand(nextStates);
                System.out.println("Expansion done!");

                // simulation
                // todo mit welchem knoten fuer ich die simulation aus, best hat jetzt schliesslich 444 kinder!!
                Node simulatedNode = best;
                int size = best.children.size();
                if(size > 0){
                    simulatedNode = best.children.get(random.nextInt(size));
                }
                State nextMove = simulate(simulatedNode);
                System.out.println("Simulation done!");

                // Backpropagation
                backpropagation(nextMove.avgPoints, best);
                System.out.println("Backpropagation done!");
                System.out.println(run++ + ". Run complete!");
                best.state.printBoard();
                gui.getPane().updateBoard(best.state.gameBoard);

                // todo play the chosen move
            }
        }
    }

    public Node selection(Node root) {
        Node next = root;
        while (next.children.size() != 0) {
            Node current = next.children.get(0);
            for (Node child : next.children) {
                double uct = child.calculateUCT();
                if (uct > current.calculateUCT()) {
                    current = child;
                }
            }
            next = current;
        }
        return next;
    }

    /*
     * plays random games to the end and returns first move
     * todo iterate 100-1000 times to find best outcome
     * todo mehr als den ersten zug ausfuehren
     * todo numberOfSimulations muss noch inkrementiert werden (evtl. erledigt!!!!!)
     */
    public State simulate(Node node) {
        int maxPoints = Integer.MIN_VALUE;
        State currentState = node.state;
        State next = node.children.get(0).state;
        List<State> nextStates = next.nextState();
        // ein zufaelliges spiel wird gespielt
        while (!nextStates.isEmpty()) {
            next = nextStates.get(random.nextInt(nextStates.size()));
            currentState = next;
            //currentState.printBoard();
            // todo was wenn nextState() leere Menge zurueck gibt?
            nextStates = currentState.nextState();
        }

        // punkte vergleich aller simulationen
        int points = currentState.colorScores.get(ColorEnum.RED);

        for (ColorEnum color : currentState.colorScores.keySet()) {
            if (currentState.colorScores.get(color) < points) {
                points = currentState.colorScores.get(color);
            }
        }

//        for (int i = 0; i < 1000; i++) {
//            // int currentPoi
//            // if playout(state);
//            // state von node aus fuer punktestand, get kleinste punktzahl
//            // spiele dann den zug mit den meitsen Punkten
//        }
        return next;
    }

    public void backpropagation(double avgScore, Node node) {
        while (node.parent != null) {
            node.state.numberOfVisits++;
            // todo durchschnittliche Punktzahl vom Knoten aus berechnen
            int lowestScore = Integer.MAX_VALUE;
            for (ColorEnum color : node.state.colorScores.keySet()) {
                int current = node.state.colorScores.get(color);
                if (current < lowestScore) {
                    lowestScore = current;
                }
            }

            node.avgScore = lowestScore / node.state.numberOfVisits;
            node = node.parent;
        }
    }
}
