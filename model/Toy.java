package model;

public class Toy {
    private long id;
    private String name;
    private double chance;

    public Toy(long id, String name, double chance) {
        this.id = id;
        this.name = name;
        this.chance = chance;
    }

    public double getChance() {
        return chance;
    }

    @Override
    public String toString() {
        return "Игрушка: " + name + ", ID: " + id;
    }
}
