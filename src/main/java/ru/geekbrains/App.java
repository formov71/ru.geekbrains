package ru.geekbrains;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee[] employArray = new Employee[5];
        employArray[0] = new Employee("Petrov Kiril Ivanovich","Programmer", "progam@mail.ru", "8(459)456-45-56", 200000, 23);
        employArray[1] = new Employee("Smirnov Andrey Sergeevich","Engineer", "smirnov@mail.ru", "8(916)123-45-56", 20000, 43);
        employArray[2] = new Employee("Ivanov Kiril Semenovich","Pilot", "k.ivanov@mail.ru", "8(249)456-32-56", 50000, 23);
        employArray[3] = new Employee("Petrov Roman Sergeevich","Teacher", "r.petrov@mail.ru", "8(149)874-45-76", 30000, 50);
        employArray[4] = new Employee("Ignatov Sergey Wsevolodovich","Operator", "s.ignatov@mail.ru", "8(879)487-55-11", 130000, 23);

        for (int i = 0; i < employArray.length; i++) {
            if (employArray[i].getAge() > 40){
                System.out.println(employArray[i].toString());
            }
        }
    }
}
