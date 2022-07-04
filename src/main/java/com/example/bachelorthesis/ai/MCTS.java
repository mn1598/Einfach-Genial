package com.example.bachelorthesis.ai;

import com.example.bachelorthesis.model.ColorEnum;
import com.example.bachelorthesis.model.Game;
import com.example.bachelorthesis.model.GameBoard;
import com.example.bachelorthesis.model.State;
import com.example.bachelorthesis.view.Gui;

import java.util.ArrayList;
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
        initial = new State();
        game = initial.game;
        root = new Node(initial);
        //randomGame(); // starts a game with random moves
        // initial.playout();
    }

    private void randomGame() {
        initial.printBoard();
        List<State> todo = initial.nextState();
        System.out.println(todo.size());
        while (!todo.isEmpty()) {
            this.initial = todo.get(random.nextInt(todo.size()));
            initial.printBoard();
            todo = initial.nextState();
            System.out.println(todo.size());
            gui.getPane().updateBoard(initial.gameBoard);
            gui.getSidePane().setLabelText(0, 0, 0, initial.colorScores);
        }
        System.out.println("Game finished");
    }

    // gets called by controller
    public void start() {

        // todo Schleife bis board voll ist
        GameBoard winnerBoard = nextMove(initial.gameBoard);
        gui.getPane().updateBoard(winnerBoard);
        System.out.println(root.children.size());

    }

    public GameBoard nextMove(GameBoard gameBoard){
        long endTime = 1000;
        long startTime = System.currentTimeMillis();
        Node node = root;

        Node winner = root;
        int run = 1;
        while (System.currentTimeMillis() - startTime < endTime) {
            System.out.println("Start " + run + ". Run!");
            // Selection
            Node best = selection(node);
            System.out.println("Selection done!");

            // todo check points (if all colors full points game ends!)
            //  also check if stones are left or no next states available!!

            // Expansion
            best.expand();
            System.out.println("Expansion done!");

            // Simulation
            Node simulatedNode = best;
            int size = best.children.size();
            if(size > 0){
                simulatedNode = best.children.get(random.nextInt(size));
            }
            int result = simulate(simulatedNode);
            //nextMove.printBoard();
            System.out.println("Simulation done!");

            // Backpropagation
            backpropagation(simulatedNode, result);
            System.out.println("Backpropagation done!");
            System.out.println(run++ + ". Run complete!");

            simulatedNode.state.printBoard();
            simulatedNode.state.colorScores.forEach((x, y) -> System.out.println(x + " " + y));
        }
        // todo play the chosen move
        //  root.getChildwithmaxscore
        //  root = winner
        //  updateBoard
        while(winner.children.size() != 0){
            double max = Integer.MIN_VALUE;
            for(Node child: winner.children){
                if(max < child.state.avgScore){
                    winner = child;
                    max = winner.state.avgScore;
                    //System.out.println("hier");

                }
            }
        }
        root = winner;
        winner.state.printBoard();
        winner.state.colorScores.forEach((x, a)-> System.out.println(x + " " + a));
        return winner.state.gameBoard;
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
     * todo achtung nicht die komplett fertig gespielte Simulation zurueckgeben, nur den ersten move!!!!!!!
     * todo numberOfSimulations muss noch inkrementiert werden (evtl. erledigt!!!!!)
     */
    public int simulate(Node node) {
        Node copyNode = node.deepCopy();
        State copyState = copyNode.state;
        State retState = null;
        // todo int boardStatus?

        // random playout (light playout)
        while(!copyState.terminal){
//            List<State> nextStates = new ArrayList<>();
//            for (Node child: copyNode.children){
//                nextStates.add(child.state);
//            }
//            copyState = nextStates.get(random.nextInt(nextStates.size()));
//            if(retState == null){
//                retState = copyState;
//            }
            copyState.playout();
        }

        int points = copyState.colorScores.get(ColorEnum.RED);

        for (ColorEnum color : copyState.colorScores.keySet()) {
            if (copyState.colorScores.get(color) < points) {
                points = copyState.colorScores.get(color);
            }
        }

        return points; // return the points achieved in this simulation


//        int maxPoints = Integer.MIN_VALUE;
//        Node copy = null;
//        Node currentState = node;
//        ArrayList<Node> nextStates = node.children;
//        System.out.println(nextStates.size());
//        // ein zufaelliges spiel wird gespielt
//        while (!nextStates.isEmpty()) {
//            currentState = nextStates.get(random.nextInt(nextStates.size()));
//            System.out.println(currentState);
//            if(copy == null){
//                copy = currentState;
//            }
            //currentState.printBoard();
            // todo was wenn nextState() leere Menge zurueck gibt?
//            nextStates = currentState.nextState();
//        }
//
        // punkte vergleich aller simulationen
//        int points = currentState.colorScores.get(ColorEnum.RED);
//
//        for (ColorEnum color : currentState.colorScores.keySet()) {
//            if (currentState.colorScores.get(color) < points) {
//                points = currentState.colorScores.get(color);
//            }
//        }
//
//        for (int i = 0; i < 1000; i++) {
//            // int currentPoi
//            // if playout(state);
//            // state von node aus fuer punktestand, get kleinste punktzahl
//            // spiele dann den zug mit den meisten Punkten
//        }
//        System.out.println(copy.nextState().size());
//        return null; // todo Problem, gibt aktuell die fertig gespielte Simulation zurueck (erster Move muss irgendwie gespeichert werden!!!!
    }

    public void backpropagation(Node node, int result) {
        while (node.parent != null) {
            node.state.numberOfVisits++;
            int lowestScore = Integer.MAX_VALUE;
            node.state.avgScore = (double) (result) / (double) (node.state.numberOfVisits);
            node = node.parent;
        }
    }
}
