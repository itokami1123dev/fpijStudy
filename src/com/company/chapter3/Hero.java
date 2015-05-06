package com.company.chapter3;

public class Hero {
    private final String name;
    private final int power;

    public Hero(final String name, final int power) {
        this.name = name;
        this.power = power;
    }

    public int powerDiff(final Hero other) {
        return power - other.power;
    }

    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("%s さんの戦闘力は %d です。", name, power);
    }
}