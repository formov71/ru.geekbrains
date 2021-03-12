package ru.geekbrains;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Animal dog = new Dog("Рексик","белый",2, "DOG");
        Animal dog1 = new Dog("Бобик","черно-белый",4, "DOG");
        Animal cat = new Cat("Вася","серый",7, "CAT");
        Animal cat1 = new Cat("Алиса","рыжий",1, "CAT");
        Animal cat2 = new Cat("Барсик","бело-рыжий",3, "CAT");
        Animal cat3 = new Cat("Муся","серый",4, "CAT");

        System.out.println("1. " + dog.run(250));
        System.out.println("2. " + dog.swim(15));
        System.out.println("3. " + dog.swim(5));

        System.out.println("4. " + cat.run(50));
        System.out.println("5. " + cat.run(250));
        System.out.println("6. " + cat.swim(5));

        System.out.println("Кол-во собак:" + dog.getCountVid(dog.vid));
        System.out.println("Кол-во кошек:" + dog.getCountVid(cat.vid));
    }
}
