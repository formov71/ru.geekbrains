package ru.geekbrains.lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MainApp {

    private static Random random = new Random();
    private static final int SIZE_CUBE = 3;
    private static final int COUNT_WIN_LINE = 3;
    private static final String EMPTY_CELL = "o";
    private static final String COMP_CELL = "\u001B[32m" + "O" + "\u001B[0m";
    private static final String GAMER_CELL = "\u001B[31m" + "X" + "\u001B[0m";
    private static String[][] WAR_MAP;
    private static enum varCheck { HORIZONT, VERTICAL, LEFT_DIAGONAL, RIGHT_DIAGONAL };
    private static enum whoRun { COMP, GAMER };
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
        //WAR_MAP[0][0] = COMP_CELL;
//        WAR_MAP[1][0] = COMP_CELL;
//        WAR_MAP[2][0] = COMP_CELL;
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

//        printMap();
//        checkVERTICAL(COMP_CELL);
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
        for (int x = 0; x < SIZE_CUBE; x++) header[x] = x+1;
        System.out.println("0:" + Arrays.toString(header));
        for (int x = 0; x < SIZE_CUBE; x++) {
            System.out.println((x + 1) + ":" + Arrays.toString(WAR_MAP[x]));
        }
    }

    public static void initGame(){
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
                    System.out.print("Не корректные координаты, куб размером: " + SIZE_CUBE +  " -> ");
                else {
                    if (WAR_MAP[y-1][x-1] != EMPTY_CELL)
                        System.out.print("Указанная ячейка не пустая! Введите новые координаты: -> ");
                    else {
                        WAR_MAP[y-1][x-1] = GAMER_CELL;
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
        if (hod==whoRun.COMP)
            checker = COMP_CELL;
        else
            checker = GAMER_CELL;

        switch (variant){
            case "HORIZONT":
                LineWin = checkHORIZONT(checker);
                break;
            case "VERTICAL":
                LineWin = checkVERTICAL(checker);
                break;

            case "LEFT_DIAGONAL":
                //System.out.println("LEFT_DIAGONAL");
                break;

            case "RIGHT_DIAGONAL":
                //System.out.println("RIGHT_DIAGONAL");
                break;
        }
        //System.out.println(coordinatyMaxLine.toString());
        return LineWin;
    }

    public static boolean checkVERTICAL(String checker){
        CoordinatyMaxLine coordinatyMaxLine = new CoordinatyMaxLine(checker, "VERTICAL");
        int[] pBegin = new int[]{-1,-1};
        int[] pEnd = new int[]{-1,-1};
        for (int x = 0; x < SIZE_CUBE; x++) {
            int countLine=0;
            for (int y = 0; y < SIZE_CUBE; y++) {
                if (WAR_MAP[y][x] == checker) {
                    if (pBegin[0] == -1) {
                        pBegin[0]= x+1;
                        pBegin[1] = y+1;
                        pEnd[0] = x+1;
                        pEnd[1] = y+1;
                    } else {
                        pEnd[0] = x+1;
                        pEnd[1] = y+1;
                    }
                    countLine++;
                    if (countLine == COUNT_WIN_LINE) break;
                } else {
                    pBegin = new int[]{-1,-1};
                    pEnd = new int[]{-1,-1};
                }
            }
            if (countLine > coordinatyMaxLine.counterMax || countLine == COUNT_WIN_LINE) {
                coordinatyMaxLine.counterMax = countLine;
                coordinatyMaxLine.beginPoint = pBegin;
                coordinatyMaxLine.endPoint = pEnd;
            }

            pBegin = new int[]{-1,-1};
            pEnd = new int[]{-1,-1};

            if (countLine == COUNT_WIN_LINE) break;
        }
        //System.out.println(coordinatyMaxLine.toString());
        return (coordinatyMaxLine.counterMax != COUNT_WIN_LINE);
    }

    public static boolean checkHORIZONT(String checker){
        CoordinatyMaxLine coordinatyMaxLine = new CoordinatyMaxLine(checker, "HORIZONT");
        int[] pBegin = new int[]{-1,-1};
        int[] pEnd = new int[]{-1,-1};
        for (int y = 0; y < SIZE_CUBE; y++) {
            int countLine=0;
            for (int x = 0; x < SIZE_CUBE; x++) {
                if (WAR_MAP[y][x] == checker) {
                    if (pBegin[0] == -1) {
                        pBegin[0]= x+1;
                        pBegin[1] = y+1;
                        pEnd[0] = x+1;
                        pEnd[1] = y+1;
                    } else {
                        pEnd[0] = x+1;
                        pEnd[1] = y+1;
                    }
                    countLine++;
                    if (countLine == COUNT_WIN_LINE) break;
                } else {
                    pBegin = new int[]{-1,-1};
                    pEnd = new int[]{-1,-1};
                }
            }
            if (countLine > coordinatyMaxLine.counterMax || countLine == COUNT_WIN_LINE) {
                coordinatyMaxLine.counterMax = countLine;
                coordinatyMaxLine.beginPoint = pBegin;
                coordinatyMaxLine.endPoint = pEnd;
            }

            pBegin = new int[]{-1,-1};
            pEnd = new int[]{-1,-1};

            if (countLine == COUNT_WIN_LINE) break;
        }
        //System.out.println(coordinatyMaxLine.toString());
        return (coordinatyMaxLine.counterMax != COUNT_WIN_LINE);
    }
}

