package ru.geekbrains;

public class Cat extends Animal {
    public Cat(String name, String color, int age, String vid) {
        super(name, color, age, vid);
    }

    @Override
    public String run(int distance) {
        if (distance > 200) {
            return name + " дальше 200 метров не бегает";
        }
        return name + " пробежал " + distance + " м.";
    }

    @Override
    public String swim(int distance) {
        return name + " не умеет плавать";
    }
}
