package ru.geekbrains.lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MainApp {

    private static Random random = new Random();
    private static final int SIZE_CUBE = 5;
    private static final int COUNT_WIN_LINE = 4;
    private static final String EMPTY_CELL = "o";
    private static final String COMP_CELL = "\u001B[32m" + "0" + "\u001B[0m";
    private static final String GAMER_CELL = "\u001B[31m" + "X" + "\u001B[0m";
    private static String[][] WAR_MAP;

    private static enum varCheck {HORIZONT, VERTICAL, LEFT_DIAGONAL, RIGHT_DIAGONAL}

    ;

    private static enum whoRun {COMP, GAMER}

    ;
    private static Scanner scanner = new Scanner(System.in);

    public static class CoordinatyMaxLine {
        public String checker = "";
        public String verify = "";
        public int counterMax = 0;
        public int[] beginPoint = new int[2];
        public int[] endPoint = new int[2];

        public CoordinatyMaxLine(String checker, String verify) {
            this.checker = checker;
            this.verify = verify;
            this.counterMax = 0;
            this.beginPoint[0] = -1;
            this.beginPoint[1] = -1;
            this.endPoint[0] = -1;
            this.endPoint[1] = -1;
        }

        @Override
        public String toString() {
            return "CoordinatyMaxLine{" +
                    " verify=" + verify +
                    ", checker=" + checker +
                    ", counterMax=" + counterMax +
                    ", beginPoint=" + Arrays.toString(beginPoint) +
                    ", endPoint=" + Arrays.toString(endPoint) +
                    '}';
        }
    }


    public static void main(String[] args) {
        boolean notEndGame = true;
        System.out.println("Старт игры \"Крестики нолики\"");
        initGame();

//        WAR_MAP[2][3] = COMP_CELL;
//        WAR_MAP[3][2] = COMP_CELL;
//        WAR_MAP[4][1] = COMP_CELL;

//        WAR_MAP[2][5] = COMP_CELL;
//        //WAR_MAP[3][4] = COMP_CELL;
//        WAR_MAP[4][3] = COMP_CELL;
//
//        WAR_MAP[5][7] = COMP_CELL;
//
//        WAR_MAP[1][1] = COMP_CELL;
//        WAR_MAP[8][8] = COMP_CELL;
//        WAR_MAP[9][9] = COMP_CELL;

//        WAR_MAP[3][0] = COMP_CELL;
//        WAR_MAP[4][0] = COMP_CELL;
//
//        WAR_MAP[1][1] = COMP_CELL;
//        WAR_MAP[2][1] = COMP_CELL;
//
//        WAR_MAP[1][0] = COMP_CELL;
//        WAR_MAP[1][1] = COMP_CELL;
//        WAR_MAP[1][2] = COMP_CELL;
//
//        WAR_MAP[0][2] = COMP_CELL;
//        WAR_MAP[2][2] = COMP_CELL;
//
//        WAR_MAP[2][0] = COMP_CELL;
//        WAR_MAP[2][3] = COMP_CELL;
//        WAR_MAP[3][3] = COMP_CELL;
//        WAR_MAP[4][3] = COMP_CELL;

//        printMap("COMP");
//        checkRIGHT_DIAGONAL(COMP_CELL);
//        checkHORIZONT(COMP_CELL);
//        checkVERTICAL(COMP_CELL);
//        checkLEFT_DIAGONAL(COMP_CELL);

        gameOver:
        do {
            runComputer();
            for (varCheck variant : varCheck.values()) {
                notEndGame = chekGame(variant.toString(), whoRun.COMP);
                if (notEndGame == false) {
                    System.out.println("Компьютер вас надул!!!");
                    printMap("COMP");
                    break gameOver;
                }
            }
            printMap("COMP");
            runGamer();
            for (varCheck variant : varCheck.values()) {
                notEndGame = chekGame(variant.toString(), whoRun.GAMER);
                if (notEndGame == false) {
                    System.out.println("Вы выиграли!!!");
                    break;
                }
            }
            printMap("GAMER");
        } while (notEndGame);
        System.out.println("Конец!");
    }

    public static void printMap(String who) {
        System.out.println("WAR map Step:" + who);
        int[] header = new int[SIZE_CUBE];
        for (int x = 0; x < SIZE_CUBE; x++) header[x] = x + 1;
        System.out.println("0:" + Arrays.toString(header));
        for (int x = 0; x < SIZE_CUBE; x++) {
            System.out.println((x + 1) + ":" + Arrays.toString(WAR_MAP[x]));
        }
    }

    public static void initGame() {
        WAR_MAP = new String[SIZE_CUBE][SIZE_CUBE];
        for (int x = 0; x < SIZE_CUBE; x++) {
            for (int y = 0; y < SIZE_CUBE; y++) {
                WAR_MAP[x][y] = EMPTY_CELL;
            }
        }
    }

    public static void runComputer() {
        boolean CellEmpty = true;
        do {
            int x = random.nextInt(SIZE_CUBE);
            int y = random.nextInt(SIZE_CUBE);
            if (WAR_MAP[x][y] == EMPTY_CELL) {
                WAR_MAP[x][y] = COMP_CELL;
                CellEmpty = false;
            }
        } while (CellEmpty);
    }

    public static void runGamer() {
        System.out.print("Введите координаты вашего хода, в формате x:y -> ");
        boolean error = true;
        do {
            String coordinates = scanner.nextLine();
            if (coordinates.contains(":")) {
                String[] splitCoordinates = coordinates.split(":");
                int x = Integer.parseInt(splitCoordinates[0]);
                int y = Integer.parseInt(splitCoordinates[1]);
                if ((x <= 0 || x > SIZE_CUBE) || (y <= 0 || y > SIZE_CUBE))
                    System.out.print("Не корректные координаты, куб размером: " + SIZE_CUBE + " -> ");
                else {
                    if (WAR_MAP[y - 1][x - 1] != EMPTY_CELL)
                        System.out.print("Указанная ячейка не пустая! Введите новые координаты: -> ");
                    else {
                        WAR_MAP[y - 1][x - 1] = GAMER_CELL;
                        error = false;
                    }
                }
            } else {
                System.out.print("Введите координаты в формате x:y -> ");
            }
        } while (error);
    }

    public static boolean chekGame(String variant, whoRun hod) {
        boolean LineWin = true;
        String checker;
        if (hod == whoRun.COMP)
            checker = COMP_CELL;
        else
            checker = GAMER_CELL;

        switch (variant) {
            case "HORIZONT":
                LineWin = checkHORIZONT(checker);
                break;
            case "VERTICAL":
                LineWin = checkVERTICAL(checker);
                break;

            case "LEFT_DIAGONAL":
                LineWin = checkLEFT_DIAGONAL(checker);
                break;

            case "RIGHT_DIAGONAL":
                LineWin = checkRIGHT_DIAGONAL(checker);
                break;
        }
        return LineWin;
    }

    public static boolean checkRIGHT_DIAGONAL(String checker) {
        int firstDiag = SIZE_CUBE - COUNT_WIN_LINE;
        int countLine = 0;
        int step = 0;
        win:
        do {
            countLine = 0;
            if (firstDiag >= 0) {
                for (int x = firstDiag + COUNT_WIN_LINE - 1; x >= 0; x--) {
                    int y = firstDiag + COUNT_WIN_LINE - 1 - x;
                    if (WAR_MAP[y][x] == checker) {
                        countLine++;
                        //System.out.println("RIGHT_DIAGONAL >>> X:" + (x + 1) + "; Y:" + (y + 1) + "; VALUE:" + WAR_MAP[y][x] + "; COUNTER:" + countLine);
                        if (countLine == COUNT_WIN_LINE) break win;
                    } else {
                        countLine = 0;
                        //System.out.println("X:" + (x + 1) + "; Y:" + (y + 1) + "; VALUE:" + WAR_MAP[x][y] + "; COUNTER:" + countLine);
                    }
                }
            } else {
                for (int y = SIZE_CUBE + firstDiag + step; y-step > 0; y--) {
                    int x = SIZE_CUBE - y + step;
                    if (WAR_MAP[y][x] == checker) {
                        countLine++;
                        //System.out.println("RIGHT_DIAGONAL >>> X:" + (x + 1) + "; Y:" + (y + 1) + "; VALUE:" + WAR_MAP[y][x] + "; COUNTER:" + countLine);
                        if (countLine == COUNT_WIN_LINE) break win;
                    } else {
                        countLine = 0;
                        //System.out.println("X:" + (x + 1) + "; Y:" + (y + 1) + "; VALUE:" + WAR_MAP[y][x] + "; COUNTER:" + countLine);
                    }
                }
                step++;
            }
            //System.out.println("RETURN:" + (countLine != COUNT_WIN_LINE) + "; COUNTER:" + countLine);
            firstDiag--;
        } while (firstDiag >= ((SIZE_CUBE - COUNT_WIN_LINE) * -1));

        return (countLine != COUNT_WIN_LINE);
    }

    public static boolean checkLEFT_DIAGONAL(String checker) {
        int firstDiag = SIZE_CUBE - COUNT_WIN_LINE;
        int countLine = 0;
        win:
        do {
            countLine = 0;
            if (firstDiag >= 0) {
                for (int y = firstDiag; y < SIZE_CUBE; y++) {
                    int x = y - firstDiag;
                    if (WAR_MAP[y][x] == checker) {
                        countLine++;
                        //System.out.println("LEFT_DIAGONAL >>> X:" + (x + 1) + "; Y:" + (y + 1) + "; VALUE:" + WAR_MAP[y][x] + "; COUNTER:" + countLine);
                        if (countLine == COUNT_WIN_LINE) break win;
                    } else {
                        countLine = 0;
                        //System.out.println("X:" + (x + 1) + "; Y:" + (y + 1) + "; VALUE:" + WAR_MAP[y][x] + "; COUNTER:" + countLine);
                    }
                }
            } else {
                for (int x = (firstDiag * -1); x < SIZE_CUBE; x++) {
                    int y = x - (firstDiag * -1);
                    if (WAR_MAP[y][x] == checker) {
                        countLine++;
                        //System.out.println("LEFT_DIAGONAL >>> X:" + (x + 1) + "; Y:" + (y + 1) + "; VALUE:" + WAR_MAP[y][x] + "; COUNTER:" + countLine);
                        if (countLine == COUNT_WIN_LINE) break win;
                    } else {
                        countLine = 0;
                        //System.out.println("X:" + (x + 1) + "; Y:" + (y + 1) + "; VALUE:" + WAR_MAP[y][x] + "; COUNTER:" + countLine);
                    }
                }
            }
            //System.out.println("RETURN:" + (countLine != COUNT_WIN_LINE) + "; COUNTER:" + countLine);
            firstDiag--;
        } while (firstDiag >= ((SIZE_CUBE - COUNT_WIN_LINE) * -1));

        return (countLine != COUNT_WIN_LINE);
    }

    public static boolean checkVERTICAL(String checker) {
        int countLine = 0;
        for (int x = 0; x < SIZE_CUBE; x++) {
            countLine = 0;
            for (int y = 0; y < SIZE_CUBE; y++) {
                if (WAR_MAP[y][x] == checker) {
                    countLine++;
                    //System.out.println("VERTICAL >>> X:" + (x + 1) + "; Y:" + (y + 1) + "; VALUE:" + WAR_MAP[y][x] + "; COUNTER:" + countLine);
                    if (countLine == COUNT_WIN_LINE) break;
                } else countLine = 0;
            }
            if (countLine == COUNT_WIN_LINE) break;
        }
        return (countLine != COUNT_WIN_LINE);
    }

    public static boolean checkHORIZONT(String checker) {
        int countLine = 0;
        for (int y = 0; y < SIZE_CUBE; y++) {
            countLine = 0;
            for (int x = 0; x < SIZE_CUBE; x++) {
                if (WAR_MAP[y][x] == checker) {
                    countLine++;
                    //System.out.println("HORIZONT >>> X:" + (x + 1) + "; Y:" + (y + 1) + "; VALUE:" + WAR_MAP[y][x] + "; COUNTER:" + countLine);
                    if (countLine == COUNT_WIN_LINE) break;
                } else countLine = 0;
            }
            if (countLine == COUNT_WIN_LINE) break;
        }
        return (countLine != COUNT_WIN_LINE);
    }
}

