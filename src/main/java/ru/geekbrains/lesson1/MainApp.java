package ru.geekbrains.lesson1;

public class MainApp {
    public static void main(String[] args) {
        byte byteVal = 127;
        short shortVal = 2311;
        int intVal = 5000;
        long longVal = 5000000L;
        float floatVal = 23.11f;
        double doubleVal = 258.11;
        char charVal = 'F';
        boolean booleanVal = true;

        System.out.println("1. Hello, World!");

        float calc = calculateArgument(3.14f, 5.6f, 1.3f, 2.3f);
        System.out.println("3. Метод, вычисляющий выражение a * (b + (c / d))=" + calc);

        boolean checkNumber = checkIntNumberFrom10to20(10, 11);
        System.out.println("4. Сумма лежит в пределах от 10 до 20 (включительно)?:" + checkNumber);

        String checkNegativeNum = checkPositivNegativeNumber(101,"5. Вы передали");
        System.out.println(checkNegativeNum);

        System.out.println("6. " + checkPositivNegativeNumber(-1));

        printHello("Сергей");

        int[] years = {1600, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400, 2500, 2600};
        for (int i = 0; i < years.length; i++){
            checkHighGradeYear(years[i], i+1);
        }
    }

    public static float calculateArgument(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    public static boolean checkIntNumberFrom10to20(int number1, int number2) {
        return (number1 + number2) >= 10 && (number1 + number2) <= 20;
    }

    public static String checkPositivNegativeNumber(int number, String text){
        if (number > 0 || number == 0){
            return text + " положительное число.";
        } else  {
            return text + " отрицательное число.";
        }
    }

    public static Boolean checkPositivNegativeNumber(int number){
        return (number < 0);
    }

    public static void printHello(String name){
        System.out.println("7. Привет, " + name + "!");
    }

    public static void checkHighGradeYear(int year, int row){
        System.out.print("8." + row + " ");
        if (year % 4 == 0){
            if (year % 100 == 0){
                if (year % 400 == 0){
                    System.out.println("Год " +year + " високосный (366 дней)");
                } else {
                    System.out.println("Год не високосный " +year + " год (365 дней)");
                }

            } else {
                System.out.println("Год " +year + " високосный (366 дней)");
            }
        } else {
            System.out.println("Год не високосный " +year + " год (365 дней)");
        }
    }
}
