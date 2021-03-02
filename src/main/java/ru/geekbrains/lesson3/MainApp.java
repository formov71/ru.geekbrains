package ru.geekbrains.lesson3;

import java.util.Random;
import java.util.Scanner;

public class MainApp {

    private static int anyNumberComp;
    private static String anyWordComp;
    private static final int MAX_NUMBER = 9;
    private static final int ALL_STEPS = 3;
    private static final String[] WORDS = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
            "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper",
            "pineapple", "pumpkin", "potato"};

    public static void main(String[] args) {

        startGameWhatsTheNumber();
        startGuessTheNumber();
    }

    public static void startGameWhatsTheNumber() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("У вас " + ALL_STEPS + " попытки, чтобы угадать число.");
        do {
            anyNumberComp = rand.nextInt(MAX_NUMBER + 1);
            for (int i = 1; i <= ALL_STEPS; i++) {
                System.out.print("Попытка № " + i + ". Введите  предполагаемое число:");
                int myNumber = sc.nextInt();
                System.out.println();
                if (checkAnyNumber(myNumber)) {
                    System.out.println("Вы победили!");
                    break;
                } else {
                    if (myNumber < anyNumberComp && i != ALL_STEPS)
                        System.out.println("Загаданное число больше.");
                    else if (myNumber > anyNumberComp && i != ALL_STEPS)
                        System.out.println("Загаданное число меньше.");
                    else
                        System.out.println("Вы проиграли, указанное число: " + anyNumberComp);
                }
            }
            System.out.print("Повторить игру еще раз? 1-да/0-нет");
        } while (sc.nextInt() == 1);
    }

    public static Boolean checkAnyNumber(int chkNumber) {
        if (chkNumber < 0 || chkNumber > MAX_NUMBER) return false;
        return chkNumber == anyNumberComp;
    }

    public static void startGuessTheNumber() {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        Boolean endGame = false;

        anyWordComp = WORDS[rand.nextInt(WORDS.length)];

        System.out.println("Угадай слово.");
        do {
            System.out.print("Введите предполагаемое слово:");
            String myWord = sc.nextLine();
            endGame = checkAnyWord(myWord);

        } while (!endGame);
        System.out.println("Ура вы отгодали слово! " + anyWordComp);
    }

    public static Boolean checkAnyWord(String chkWord) {
        if (anyWordComp.equals(chkWord)) return true;

        char[] anyWordCompArr = anyWordComp.toCharArray();
        char[] chkWordArr = chkWord.toCharArray();

        int minLengthWord = 0;
        if (anyWordCompArr.length < chkWordArr.length)
            minLengthWord = anyWordCompArr.length;
        else
            minLengthWord = chkWordArr.length;

        String constructorWord = "";
        for (int i = 0; i < minLengthWord; i++) {
            if (anyWordCompArr[i] == chkWordArr[i])
                constructorWord += anyWordCompArr[i];
            else
                break;
        }
        System.out.println(constructorWord + "###############");

        return false;
    }
}
