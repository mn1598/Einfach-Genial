package com.example.bachelorthesis.model;

import java.util.*;

public class State {
    public GameBoard gameBoard;
    public HashMap<ColorEnum, Integer> colorScores;
    public Stone currentStone; // stone has been drawed
    public boolean firstMove;
    public int numberOfVisits;
    public double avgScore; // avg Points per round (for the lowest color)
    public boolean terminal;
    public Game game;
    public int numberOfNext;

    // "Kopierkonstruktor" fuer Folgezustaende
    public State(State state) {
        firstMove = false;
        numberOfNext = state.numberOfNext;
        game = new Game(state.game);
        gameBoard = new GameBoard();
        for (int i = 0; i < state.gameBoard.representation.length; i++) {
            for (int j = 0; j < state.gameBoard.representation[i].length; j++) {
                gameBoard.representation[i][j] = state.gameBoard.representation[i][j];
            }
        }

        colorScores = new HashMap<>();
        state.colorScores.forEach((x, y) -> {
            this.colorScores.put(x, y);
        });
    }

    // Konstruktor zur erstmaligen initialisierung
    public State() {
        // initial state
        colorScores = new HashMap<ColorEnum, Integer>();
        colorScores.put(ColorEnum.RED, 0);
        colorScores.put(ColorEnum.BLUE, 0);
        colorScores.put(ColorEnum.GREEN, 0);
        colorScores.put(ColorEnum.YELLOW, 0);
        colorScores.put(ColorEnum.ORANGE, 0);
        colorScores.put(ColorEnum.PURPLE, 0);
        gameBoard = new GameBoard();
        numberOfVisits = 0;
        firstMove = true;
        game = new Game();
        //currentStone = game.drawStone();
        terminal = false;
    }


    public void printBoard() {
        for (int i = 0; i < gameBoard.representation.length; i++) {
            for (int k = 5; k > i; k--) {
                System.out.print("  ");
            }

            for (int k = 0; k < i - 5 && i > 5; k++) {
                System.out.print("  ");
            }

            for (int j = 0; j < gameBoard.representation[i].length; j++) {
                System.out.print(gameBoard.representation[i][j].toString().charAt(0) + "   ");
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }

    public List<State> nextState() {
        currentStone = game.drawStone();
        List<State> nextStates = new ArrayList<>();
        if (firstMove) {
            nextStates = checkFirstMove(currentStone);
            firstMove = false;
        } else {
            if (currentStone != null) {
                for (int i = 0; i < gameBoard.representation.length; i++) {
                    int len = gameBoard.representation[i].length;
                    for (int j = 0; j < len; j++) {
                        if (gameBoard.representation[i][j] == ColorEnum.NONE) {
                            /*
                             * Rotation NONE
                             */
                            if (j < len - 1) {
                                if (gameBoard.representation[i][j + 1] == ColorEnum.NONE) {
                                    // rota.NONE hinzufuegen
                                    State stateNew = new State(this);
                                    stateNew.putStoneOnField(currentStone, Rotation.NONE, i, j);
                                    stateNew.calculatePoints(currentStone, Rotation.NONE, i, j);
                                    nextStates.add(stateNew);
                                }
                            }

                            /*
                             * Roatation One
                             */
                            //Rotation.ONE upper half
                            if (i < 5) {
                                if (gameBoard.representation[i + 1][j + 1] == ColorEnum.NONE) {
                                    // rota.ONE hinzufuegen
                                    State stateNew = new State(this);
                                    stateNew.putStoneOnField(currentStone, Rotation.ONE_CLOCKWISE, i, j);
                                    stateNew.calculatePoints(currentStone, Rotation.ONE_CLOCKWISE, i, j);
                                    nextStates.add(stateNew);
                                }
                            }

                            //Rotation.ONE lower half
                            if (i >= 5 && i < 10 && j < len - 1) {
                                if (gameBoard.representation[i + 1][j] == ColorEnum.NONE) {
                                    // rota.ONE hinzufuegen
                                    State stateNew = new State(this);
                                    stateNew.putStoneOnField(currentStone, Rotation.ONE_CLOCKWISE, i, j);
                                    stateNew.calculatePoints(currentStone, Rotation.ONE_CLOCKWISE, i, j);
                                    nextStates.add(stateNew);
                                }
                            }

                            /*
                             * Rotation TWO
                             */
                            //Rotation.TWO upper half
                            if (i < 5) {
                                if (gameBoard.representation[i + 1][j] == ColorEnum.NONE) {
                                    // rota.TWO hinzufuegen
                                    State stateNew = new State(this);
                                    stateNew.putStoneOnField(currentStone, Rotation.TWO_CLOCKWISE, i, j);
                                    stateNew.calculatePoints(currentStone, Rotation.TWO_CLOCKWISE, i, j);
                                    nextStates.add(stateNew);
                                }
                            }

                            //Rotation.TWO lower half
                            if (i >= 5 && i < 10 && j > 0) {
                                if (gameBoard.representation[i + 1][j - 1] == ColorEnum.NONE) {
                                    // rota.TWO hinzufuegen
                                    State stateNew = new State(this);
                                    stateNew.putStoneOnField(currentStone, Rotation.TWO_CLOCKWISE, i, j);
                                    stateNew.calculatePoints(currentStone, Rotation.TWO_CLOCKWISE, i, j);
                                    nextStates.add(stateNew);
                                }
                            }

                            /*
                             * Rotation THREE
                             */
                            if (j > 0) {
                                if (gameBoard.representation[i][j - 1] == ColorEnum.NONE) {
                                    // rota.THREE hinzufuegen
                                    State stateNew = new State(this);
                                    stateNew.putStoneOnField(currentStone, Rotation.THREE_CLOCKWISE, i, j);
                                    stateNew.calculatePoints(currentStone, Rotation.THREE_CLOCKWISE, i, j);
                                    nextStates.add(stateNew);
                                }
                            }

                            /*
                             * Rotation FOUR
                             */
                            //Rotation.FOUR upper half
                            if (i <= 5 && i > 0 && j > 0) {
                                if (gameBoard.representation[i - 1][j - 1] == ColorEnum.NONE) {
                                    // rota.FOUR hinzufuegen
                                    State stateNew = new State(this);
                                    stateNew.putStoneOnField(currentStone, Rotation.FOUR_CLOCKWISE, i, j);
                                    stateNew.calculatePoints(currentStone, Rotation.FOUR_CLOCKWISE, i, j);
                                    nextStates.add(stateNew);
                                }
                            }

                            //Rotation.FOUR lower half
                            if (i > 5) {
                                if (gameBoard.representation[i - 1][j] == ColorEnum.NONE) {
                                    // rota.FOUR hinzufuegen
                                    State stateNew = new State(this);
                                    stateNew.putStoneOnField(currentStone, Rotation.FOUR_CLOCKWISE, i, j);
                                    stateNew.calculatePoints(currentStone, Rotation.FOUR_CLOCKWISE, i, j);
                                    nextStates.add(stateNew);
                                }
                            }

                            /*
                             * Rotation FIVE
                             */
                            //Rotation.FIVE upper half
                            if (i <= 5 && i > 0 && j < len - 1) {
                                if (gameBoard.representation[i - 1][j] == ColorEnum.NONE) {
                                    // rota.FIVE hinzufuegen
                                    State stateNew = new State(this);
                                    stateNew.putStoneOnField(currentStone, Rotation.FIVE_CLOCKWISE, i, j);
                                    stateNew.calculatePoints(currentStone, Rotation.FIVE_CLOCKWISE, i, j);
                                    nextStates.add(stateNew);
                                }
                            }

                            //Rotation.FIVE lower half
                            if (i > 5) {
                                if (gameBoard.representation[i - 1][j + 1] == ColorEnum.NONE) {
                                    // rota.FIVE hinzufuegen
                                    State stateNew = new State(this);
                                    stateNew.putStoneOnField(currentStone, Rotation.FIVE_CLOCKWISE, i, j);
                                    stateNew.calculatePoints(currentStone, Rotation.FIVE_CLOCKWISE, i, j);
                                    nextStates.add(stateNew);
                                }
                            }
                        }
                    }
                }
            }
        }

        firstMove = false;
        numberOfNext = nextStates.size();
        if (nextStates.isEmpty()) {
            terminal = true;
        }
        return nextStates;
    }

    private List<State> checkFirstMove(Stone currentStone) {
        List<State> nextStates = new ArrayList<>();
        // startpositionen überprüfen
        for (int i = 0; i < gameBoard.representation.length; i++) {
            int len = gameBoard.representation[i].length;
            for (int j = 0; j < len; j++) {

                // none
                if (((i == 0 || i == 10) && (j == 1 || j == 3)) || ((i == 1 || i == 9) && (j != 2 && j != 3 && j != 6))
                        || ((i == 4 || i == 6) && (j == 0 || j == 8)) || (i == 5 && (j == 1 || j == 8))) {

                    // rota.NONE hinzufuegen
                    State stateNew = new State(this);
                    stateNew.putStoneOnField(currentStone, Rotation.NONE, i, j);
                    stateNew.calculatePoints(currentStone, Rotation.NONE, i, j);
                    nextStates.add(stateNew);
                }

                // one
                if ((i == 0 && (j == 1 || j == 4)) || (i == 1 && (j != 2 && j != 3 && j != 4)) ||(i == 3 && j == 8)
                || (i == 4 && (j == 0 || j == 8)) || (i == 5 && (j == 1 || j== 9)) || (i == 6 && j == 0) || (i == 8 && (j == 0 ||
                        j == 1 || j == 5 || j == 6)) || (i == 9 && (j == 0 || j == 1 || j == 4))){

                    // rota.ONE hinzufuegen
                    State stateNew = new State(this);
                    stateNew.putStoneOnField(currentStone, Rotation.ONE_CLOCKWISE, i, j);
                    stateNew.calculatePoints(currentStone, Rotation.ONE_CLOCKWISE, i, j);
                    nextStates.add(stateNew);
                }

                // two
                if((i == 0 && (j == 1 || j == 4)) || (i == 1 && (j == 0 || j == 1 || j == 5 || j == 6)) || (i== 4 && (j == 0 || j == 1))
                || (i == 5 && (j == 1 || j == 9)) || (i == 6 && j == 9) || (i == 8 && (j == 1 || j == 2 || j == 6 || j == 7))
                || (i == 9 && (j == 2 || j == 5))){

                    // rota.TWO hinzufuegen
                    State stateNew = new State(this);
                    stateNew.putStoneOnField(currentStone, Rotation.TWO_CLOCKWISE, i, j);
                    stateNew.calculatePoints(currentStone, Rotation.TWO_CLOCKWISE, i, j);
                    nextStates.add(stateNew);
                }

                // three
                if(((i == 0 || i == 10) && (j == 2 || j == 4)) || ((i == 1 || i == 9) && (j == 1 || j == 2 || j == 5 || j == 6))
                || ((i == 4 || i == 6) && (j == 1 || j == 9)) || (i == 5 && (j == 2 || j == 9))){

                    // rota.THREE hinzufuegen
                    State stateNew = new State(this);
                    stateNew.putStoneOnField(currentStone, Rotation.THREE_CLOCKWISE, i, j);
                    stateNew.calculatePoints(currentStone, Rotation.THREE_CLOCKWISE, i, j);
                    nextStates.add(stateNew);
                }

                // four
                if((i == 1 && (j == 2 || j == 5)) || (i == 2 && (j == 1 || j == 2 || j == 6 || j == 7)) || (i == 4 && j == 9)
                    || (i == 5 && (j == 1 || j == 9)) || (i == 6 && (j == 1 || j == 9)) || (i == 7 && j == 0)
                        || (i == 9 && (j == 0 || j == 1 || j == 5 || j == 6)) || (i == 10 && (j == 1 || j == 4))){

                    // rota.FOUR hinzufuegen
                    State stateNew = new State(this);
                    stateNew.putStoneOnField(currentStone, Rotation.FOUR_CLOCKWISE, i, j);
                    stateNew.calculatePoints(currentStone, Rotation.FOUR_CLOCKWISE, i, j);
                    nextStates.add(stateNew);
                }

                // five
                if((i == 1 && (j == 1 || j == 4)) || (i == 2 && (j == 0 || j == 1 || j == 5 || j == 6)) || (i == 4 && j == 0)
                || (i == 5 && (j == 1 || j == 9)) || (i == 6 && (j == 0 || j == 8)) || (i == 7 && j == 8) || (i == 9 &&
                        (j == 0 || j== 1 || j == 5 || j == 6)) || (i == 10 && (j == 1 || j == 4))){

                    // rota.FIVE hinzufuegen
                    State stateNew = new State(this);
                    stateNew.putStoneOnField(currentStone, Rotation.FIVE_CLOCKWISE, i, j);
                    stateNew.calculatePoints(currentStone, Rotation.FIVE_CLOCKWISE, i, j);
                    nextStates.add(stateNew);
                }
            }
        }
        return nextStates;
    }

    public void putStoneOnField(Stone stone, Rotation rotation, int i, int j) {
        gameBoard.representation[i][j] = stone.stoneColor[0];
        switch (rotation) {
            case NONE:
                gameBoard.representation[i][j + 1] = stone.stoneColor[1];
                break;
            case ONE_CLOCKWISE:
                if (i < 5) {
                    gameBoard.representation[i + 1][j + 1] = stone.stoneColor[1];
                } else {
                    gameBoard.representation[i + 1][j] = stone.stoneColor[1];
                }
                break;
            case TWO_CLOCKWISE:
                if (i < 5) {
                    gameBoard.representation[i + 1][j] = stone.stoneColor[1];
                } else {
                    gameBoard.representation[i + 1][j - 1] = stone.stoneColor[1];
                }
                break;
            case THREE_CLOCKWISE:
                gameBoard.representation[i][j - 1] = stone.stoneColor[1];
                break;
            case FOUR_CLOCKWISE:
                if (i <= 5) {
                    gameBoard.representation[i - 1][j - 1] = stone.stoneColor[1];
                } else {
                    gameBoard.representation[i - 1][j] = stone.stoneColor[1];
                }
                break;
            case FIVE_CLOCKWISE:
                if (i <= 5) {
                    gameBoard.representation[i - 1][j] = stone.stoneColor[1];
                } else {
                    gameBoard.representation[i - 1][j + 1] = stone.stoneColor[1];
                }
                break;
            default:
        }
    }

    public void calculatePoints(Stone stone, Rotation rotation, int i, int j) {
        //System.out.println("start calculating points");
        int counter = 0;
        for (int colorCounter = 0; colorCounter < 2; colorCounter++) { // sorgt dafuer, dass beide farben vom gelegten Stein geprüft werden
            A:
            for (counter = 0; counter < 6; counter++) { // sorgt dafuer, dass jede richtung gecheckt wird
                int offset = 1;
                boolean inBounds = true;
                boolean sameColor = true;
                int times = 0;
                while (inBounds) {
                    try {
                        while (sameColor) {
                            if (counter == 0 && ((colorCounter == 0 && rotation.ordinal() != counter) || (colorCounter == 1 && rotation.ordinal() != 3))) {
                                if (gameBoard.representation[i][j + offset] == stone.stoneColor[colorCounter]) {
                                    colorScores.put(stone.stoneColor[colorCounter], colorScores.get(stone.stoneColor[colorCounter]) + 1);
                                    offset++;
                                } else {
                                    sameColor = false;
                                }
                            } else if (counter == 1 && ((colorCounter == 0 && rotation.ordinal() != counter) || (colorCounter == 1 && rotation.ordinal() != 4))) {
                                if (gameBoard.representation[i + offset][j + offset] == stone.stoneColor[colorCounter] && i < 5 ||
                                        (gameBoard.representation[i + offset][j] == stone.stoneColor[colorCounter] && i >= 5)) {
                                    colorScores.put(stone.stoneColor[colorCounter], colorScores.get(stone.stoneColor[colorCounter]) + 1);
                                    offset++;
                                } else {
                                    sameColor = false;
                                }
                            } else if (counter == 2 && ((colorCounter == 0 && rotation.ordinal() != counter) || (colorCounter == 1 && rotation.ordinal() != 5))) {
                                if ((gameBoard.representation[i + offset][j] == stone.stoneColor[colorCounter] && i < 5) ||
                                        (j > 0 && (gameBoard.representation[i + offset][j - offset] == stone.stoneColor[colorCounter]) && i >= 5)) {
                                    colorScores.put(stone.stoneColor[colorCounter], colorScores.get(stone.stoneColor[colorCounter]) + 1);
                                    offset++;
                                } else {
                                    sameColor = false;
                                }
                            } else if (counter == 3 && ((colorCounter == 0 && rotation.ordinal() != counter) || (colorCounter == 1 && rotation.ordinal() != 0))) {
                                if (j > 0 && gameBoard.representation[i][j - offset] == stone.stoneColor[colorCounter]) {
                                    colorScores.put(stone.stoneColor[colorCounter], colorScores.get(stone.stoneColor[colorCounter]) + 1);
                                    offset++;
                                } else {
                                    sameColor = false;
                                }
                            } else if (counter == 4 && ((colorCounter == 0 && rotation.ordinal() != counter) || (colorCounter == 1 && rotation.ordinal() != 1))) {
                                if ((j > 0 && gameBoard.representation[i - offset][j - offset] == stone.stoneColor[colorCounter] && i <= 5) ||
                                        (gameBoard.representation[i - offset][j] == stone.stoneColor[colorCounter] && i > 5)) {
                                    colorScores.put(stone.stoneColor[colorCounter], colorScores.get(stone.stoneColor[colorCounter]) + 1);
                                    offset++;
                                    //System.out.println(colorCounter + ", " + i + ", " + j);
                                } else {
                                    sameColor = false;
                                }
                            } else if (counter == 5 && ((colorCounter == 0 && rotation.ordinal() != counter) || (colorCounter == 1 && rotation.ordinal() != 2))) {
                                if ((gameBoard.representation[i - offset][j] == stone.stoneColor[colorCounter] && i <= 5) ||
                                        (gameBoard.representation[i - offset][j + offset] == stone.stoneColor[colorCounter] && i > 5)) {
                                    colorScores.put(stone.stoneColor[colorCounter], colorScores.get(stone.stoneColor[colorCounter]) + 1);
                                    offset++;
                                } else {
                                    sameColor = false;
                                }
                            } else {
                                break;
                            }
                        }
                        break;

                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                        //System.out.println("Ueberlauf!!" + e);

                        continue A;
                    }
                }
            }
            //bereite i, j ccordinates fuer zweite farbe vom stein vor
            // Fallunterscheidung, haengt von ab, ob oberhalb oder unterhalb der mitte
            switch (rotation) {
                case NONE:
                    j++;
                    break;
                case ONE_CLOCKWISE:
                    if (i < 5) {
                        j++;
                    }
                    i++;
                    break;
                case TWO_CLOCKWISE:
                    if (i >= 5) {
                        j--;
                    }
                    i++;
                    break;
                case THREE_CLOCKWISE:
                    j--;
                    break;
                case FOUR_CLOCKWISE:
                    if (i < 5) {
                        j--;
                    }
                    i--;
                    break;
                case FIVE_CLOCKWISE:
                    if (i >= 5) {
                        j++;
                    }
                    i--;
                    break;
                default:
                    break;
            }
        }
    }

    public void playout() {
        System.out.println("Start random playout for simulation!");

        // copy instance of game for simulation from this state
        Game game = new Game(this.game);
        this.game = game;
        Random random = new Random();
        List<State> nextStates = this.nextState();
        this.printBoard();
        while (!nextStates.isEmpty()) {
           //System.out.println(nextStates.size());
            State next = nextStates.get(random.nextInt(nextStates.size()));
            next.printBoard();
            this.colorScores = next.colorScores;
            this.currentStone = next.currentStone;
            this.gameBoard = next.gameBoard;
            nextStates = next.nextState();
        }
//        State next = nextStates.get(random.nextInt(nextStates.size()));
//        next.printBoard();
//        this.colorScores = next.colorScores;
//        this.currentStone = next.currentStone;
//        this.gameBoard = next.gameBoard;
    }
}
