package ru.geekbrains;

public class Dog extends Animal {
    public Dog(String name, String color, int age, String vid) {
        super(name, color, age, vid);
    }

    @Override
    public String run(int distance) {
        if (distance > 500) {
            return name + " дальше 500 метров не бегает";
        }
        return name + " пробежал " + distance + " м.";
    }

    @Override
    public String swim(int distance) {
        if (distance > 10) {
            return name + " дальше 10 метров не плавает";
        }
        return name + " проплыл " + distance + " м.";
    }
}
