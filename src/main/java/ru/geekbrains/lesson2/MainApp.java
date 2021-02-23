package ru.geekbrains.lesson2;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        int[] intArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("1.Array before:  " + Arrays.toString(intArray));
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (intArray[i] == 0 ? 1 : 0);
        }
        System.out.println("1.Array reverse: " + Arrays.toString(intArray));

        int[] intArrayEightLen = new int[8];
        for (int i = 0; i < intArrayEightLen.length; i++) {
            intArrayEightLen[i] = (i == 0 ? 0 : intArrayEightLen[i - 1] + 3);
        }
        System.out.println("2.Array eight length:" + Arrays.toString(intArrayEightLen));

        int[] intArrayRnd = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("3.Array before: " + Arrays.toString(intArrayRnd));
        for (int i = 0; i < intArrayRnd.length; i++) {
            intArrayRnd[i] = (intArrayRnd[i] < 6 ? intArrayRnd[i] * 2 : intArrayRnd[i]);
        }
        System.out.println("3.Array elm>6*2:" + Arrays.toString(intArrayRnd));

        int[][] squareArray = new int[10][10];
        for (int i = 0; i < squareArray.length; i++) {
            for (int j = 0; j < squareArray.length; j++) {
                squareArray[i][j] = (i == j ? 1 : (i == squareArray.length - 1 - j ? 1 : 0));
            }
            System.out.println("4.Array Square:" + Arrays.toString(squareArray[i]));
        }

        int[] oneDimArray = new int[100];
        int minNumber = 0;
        int maxNumber = 0;
        for (int i = 0; i < oneDimArray.length; i++) {
            oneDimArray[i] = (int) (Math.random() * 1000);
            if (oneDimArray[i] % 2 == 0) oneDimArray[i] *= -1;

            minNumber = (i == 0 ? minNumber = oneDimArray[i] : (oneDimArray[i] < minNumber ? minNumber = oneDimArray[i] : minNumber));
            maxNumber = (i == 0 ? maxNumber = oneDimArray[i] : (oneDimArray[i] > maxNumber ? maxNumber = oneDimArray[i] : maxNumber));
        }
        System.out.println("5. Print one dim array: " + Arrays.toString(oneDimArray));
        System.out.println("5. Min element: " + minNumber + " and Max element: " + maxNumber);

        int[] arrCheckBalance = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println("6. Return balance: " + checkBalanceArray(0, arrCheckBalance));

        int[] oneDimArrayBeforeMove = {1, 2, 3, 4, 5,  6, 7, 8, 9};
        int nStep = 4;
        System.out.println("7. Before move array:" + Arrays.toString(oneDimArrayBeforeMove));
        int[] returnMoveArray = moveArrayToNPosition(oneDimArrayBeforeMove, nStep);
        System.out.println("7. Moved array to n=" + nStep + ":" + Arrays.toString(returnMoveArray));
    }

    public static boolean checkBalanceArray(int idxCenter, int[] inArray) {
        if (idxCenter == inArray.length) return false;
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < inArray.length; i++) {
            if (idxCenter < i) rightSum += inArray[i];
            else leftSum += inArray[i];
        }
        System.out.println("6. ->Iteration:" + (idxCenter + 1) + " Number: " + inArray[idxCenter] + "; leftSum ->" + leftSum + (leftSum == rightSum ? " || " : " : ") + rightSum + "<- rightSum");
        if (leftSum == rightSum) return true;
        else return checkBalanceArray(++idxCenter, inArray);
    }

    public static int[] moveArrayToNPosition(int[] inArray, int n) {
        int firstVal = inArray[0];
        int lastVal = inArray[inArray.length - 1];
        for (int i = 0; i < (n < 0 ? n * -1 : n); i++) {
            if (n > 0) {
                for (int j = inArray.length - 2; j >= 0; j--) {
                    inArray[j + 1] = inArray[j];
                }
                inArray[0] = lastVal;
                lastVal = inArray[inArray.length - 1];
            } else {
                for (int j = 1; j <= inArray.length - 1; j++) {
                    inArray[j - 1] = inArray[j];
                }
                inArray[inArray.length - 1] = firstVal;
                firstVal = inArray[0];
            }
        }
        return inArray;
    }
}