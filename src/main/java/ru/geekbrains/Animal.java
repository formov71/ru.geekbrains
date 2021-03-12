package ru.geekbrains;

import java.util.HashMap;

public class Animal {
    public String name;
    public String color;
    public int age;
    public String vid;
    public static HashMap<String, Integer> countVid = new HashMap<>();

    public Animal(String name, String color, int age, String vid) {
        this.name = name;
        this.color = color;
        this.age = age;
        this.vid = vid;
        countVid.put(vid, (countVid.get(vid) == null ? 1 : countVid.get(vid) + 1));
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }

    public String run(int distance) {
        return "пробежал " + distance + " м.";
    }

    public String swim(int distance) {
        return "проплыл " + distance + " м.";
    }

    public int getCountVid(String vid) {
        return countVid.get(vid);
    }
}

