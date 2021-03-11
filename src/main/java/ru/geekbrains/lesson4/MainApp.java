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
    private static int[] coordinatyLastStepGamer = new int[2];

    public static void main(String[] args) {
        runGameKrestNull();
    }

    public static void runGameKrestNull() {
        boolean notEndGame = true;
        System.out.println("Старт игры \"Крестики нолики\"");
        initGame();

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
        iscustIntelectCompa();
    }

    public static void iscustIntelectCompa() {
        if (coordinatyLastStepGamer.length == 0) randomCompRun();

        System.out.println(Arrays.toString(coordinatyLastStepGamer));
        int predX = -1;
        int predY = -1;

        int nextX = -1;
        int nextY = -1;
        int lastX = -1;
        int lastY = -1;
        if (coordinatyLastStepGamer[0] != 0) {
            nextX = coordinatyLastStepGamer[0] - 1;
        } else {
            nextX = coordinatyLastStepGamer[0];
        }
        if (coordinatyLastStepGamer[1] != 0) {
            nextY = coordinatyLastStepGamer[1] - 1;
        } else {
            nextY = coordinatyLastStepGamer[1];
        }

        if (coordinatyLastStepGamer[0] == SIZE_CUBE - 1) {
            lastX = coordinatyLastStepGamer[0];
        } else {
            lastX = coordinatyLastStepGamer[0] + 1;
        }
        if (coordinatyLastStepGamer[1] == SIZE_CUBE - 1) {
            lastY = coordinatyLastStepGamer[1];
        } else {
            lastY = coordinatyLastStepGamer[1] + 1;
        }

        found:
        for (int incX = nextX; incX <= lastX; incX++) {
            for (int incY = nextY; incY <= lastY; incY++) {
                if (incX == coordinatyLastStepGamer[0] && incY == coordinatyLastStepGamer[1]) continue;
                System.out.println("Value: " + WAR_MAP[incX][incY] + ": X:" + (incX + 1) + "; Y:" + (incY + 1));
                if (WAR_MAP[incX][incY] == GAMER_CELL) {
                    predX = incX;
                    predY = incY;
                    break found;
                }
            }
        }

//        System.out.println("Pred: " + WAR_MAP[predX][predY] + ": X:" + (predX+1) + "; Y:" + (predY+1) );
        int xSledStep = (coordinatyLastStepGamer[0] - predX) + coordinatyLastStepGamer[0];
        int ySledStep = coordinatyLastStepGamer[1] - predY + coordinatyLastStepGamer[1];

        if (xSledStep < 0 || ySledStep < 0 || xSledStep >= SIZE_CUBE || ySledStep >= SIZE_CUBE) {
            randomCompRun();
        } else {
            WAR_MAP[ySledStep][xSledStep] = COMP_CELL;
        }
    }

    public static void randomCompRun() {
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
                coordinatyLastStepGamer[0] = x - 1;
                coordinatyLastStepGamer[1] = y - 1;
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
                for (int y = SIZE_CUBE + firstDiag + step; y - step > 0; y--) {
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

