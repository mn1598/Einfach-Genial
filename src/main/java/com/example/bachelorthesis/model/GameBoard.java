package com.example.bachelorthesis.model;

public class GameBoard {

    public ColorEnum[][] representation;

    public GameBoard() {
        initGameBoard();
    }

    public void initGameBoard() {
        representation = new ColorEnum[11][];
        representation[0] = new ColorEnum[6];
        representation[1] = new ColorEnum[7];
        representation[2] = new ColorEnum[8];
        representation[3] = new ColorEnum[9];
        representation[4] = new ColorEnum[10];
        representation[5] = new ColorEnum[11];
        representation[6] = new ColorEnum[10];
        representation[7] = new ColorEnum[9];
        representation[8] = new ColorEnum[8];
        representation[9] = new ColorEnum[7];
        representation[10] = new ColorEnum[6];


        for (int i = 0; i < representation.length; i++) {
            for (int j = 0; j < representation[i].length; j++) {
                representation[i][j] = ColorEnum.NONE;
            }
        }
        representation[0][0] = ColorEnum.RED;
        representation[0][5] = ColorEnum.GREEN;
        representation[5][0] = ColorEnum.PURPLE;
        representation[5][10] = ColorEnum.BLUE;
        representation[10][0] = ColorEnum.YELLOW;
        representation[10][5] = ColorEnum.ORANGE;
    }

    public boolean isFull() {
        ColorEnum none = ColorEnum.NONE;
        for (int i = 0; i < representation.length; i++) {
            for (int j = 0; j < representation[i].length; j++) {
                if (representation[i][j] == ColorEnum.NONE) {
                    // test this method
                    // first row
                    if (i == 0) {
                        if (j == 1) {
                            if (representation[i][j + 1] == none || representation[i + 1][j + 1] == none || representation[i + 1][j] == none) {
                                return false;
                            } else {
                                continue;
                            }
                        } else if (j == 4) {
                            if (representation[i + 1][j + 1] == none || representation[i + 1][j] == none || representation[i][j - 1] == none) {
                                return false;
                            } else {
                                continue;
                            }
                        } else {
                            if (representation[i][j + 1] == none || representation[i + 1][j + 1] == none || representation[i + 1][j] == none
                                    || representation[i][j - 1] == none) {
                                return false;
                            } else {
                                continue;
                            }
                        }
                    }
                    // second to fifth row
                    if (i > 0 && i < 5) {
                        if (j == 0) {
                            if (representation[i][j + 1] == none || representation[i + 1][j + 1] == none || representation[i + 1][j] == none
                                    || representation[i - 1][j] == none) {
                                return false;
                            } else {
                                continue;
                            }
                        }
                        if (j == representation[i].length - 1) {
                            if (representation[i + 1][j + 1] == none || representation[i + 1][j] == none || representation[i][j - 1] == none
                                    || representation[i - 1][j - 1] == none) {
                                return false;
                            } else {
                                continue;
                            }
                        }
                    }

                    // sixth row
                    // not necessary because only fields 'on edge' are precolored fields blue & purple!

                    // seventh to tenth row
                    if (i > 5 && i < 10) {
                        if (j == 0) {
                            if (representation[i][j + 1] == none || representation[i + 1][j] == none || representation[i - 1][j] == none
                                    || representation[i - 1][j + 1] == none) {
                                return false;
                            } else {
                                continue;
                            }
                        }
                        if (j == representation[i].length - 1) {
                            if (representation[i + 1][j - 1] == none || representation[i][j - 1] == none || representation[i - 1][j] == none
                                    || representation[i - 1][j + 1] == none) {
                                return false;
                            } else {
                                continue;
                            }
                        }
                    }

                    // eleventh row
                    if (i == 10) {
                        if (j == 1) {
                            if (representation[i][j + 1] == none || representation[i - 1][j] == none || representation[i - 1][j + 1] == none) {
                                return false;
                            } else {
                                continue;
                            }
                        } else if (j == 4) {
                            if (representation[i][j - 1] == none || representation[i - 1][j] == none || representation[i - 1][j + 1] == none) {
                                return false;
                            } else {
                                continue;
                            }
                        } else {
                            if (representation[i][j + 1] == none || representation[i][j - 1] == none || representation[i - 1][j] == none
                                    || representation[i - 1][j + 1] == none) {
                                return false;
                            } else {
                                continue;
                            }
                        }
                    }
                    // now check all field with six neighbouors at once!!!
                    if (i < 5) {
                        if (representation[i][j + 1] == none || representation[i + 1][j] == none || representation[i + 1][j + 1] == none
                                || representation[i][j - 1] == none || representation[i - 1][j] == none || representation[i - 1][j - 1] == none) {
                            return false;
                        }
                    }
                    if (i == 5) {
                        if (representation[i][j + 1] == none || representation[i + 1][j] == none || representation[i + 1][j - 1] == none
                                || representation[i][j - 1] == none || representation[i - 1][j - 1] == none || representation[i - 1][j] == none) {
                            return false;
                        }
                    }
                    if (i > 5) {
                        if (representation[i][j + 1] == none || representation[i + 1][j] == none || representation[i + 1][j - 1] == none
                                || representation[i][j - 1] == none || representation[i - 1][j] == none || representation[i - 1][j + 1] == none) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
