package service;

import model.Toy;

import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Service {
    private List<Toy> toys;
    private Queue<Toy> result;

    public Service() {
        toys = new ArrayList<>();
        result = new LinkedList<>();
    }

    public void get() {
        int choose;
        while (toys.size() > 0) {
            choose = random(getSize());
            result.add(toys.get(choose));
            toys.remove(choose);
        }
    }

    private void put() {
        toys.add(addConstructor());
        toys.add(addConstructor());
        toys.add(addRobot());
        toys.add(addRobot());
        toys.add(addDoll());
        toys.add(addDoll());
        toys.add(addDoll());
        toys.add(addDoll());
        toys.add(addDoll());
        toys.add(addDoll());
    }

    public void save(boolean append) {
        try (FileWriter fileWriter = new FileWriter("result.txt", append)) {
            fileWriter.write("-------------------------\n");
            fileWriter.write("Результат выборки:\n");
            fileWriter.write("-------------------------\n");
            while (result.size() > 0){
                fileWriter.write(result.remove().toString() +"\n");
            }
            fileWriter.write("-------------------------\n");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    private Toy addConstructor() {
        return new Toy(1L, "Конструктор", 0.2);
    }

    private Toy addRobot() {
        return new Toy(2L, "Робот", 0.2);
    }

    private Toy addDoll() {
        return new Toy(3L, "Кукла", 0.6);
    }

    private double getSize() {
        double size = 0;
        for (Toy toy: toys) {
            size = size + toy.getChance();
        }
        return size;
    }

    private int random(double size) {
        double variant = ThreadLocalRandom.current().nextDouble(0, size);
        double count = 0;
        int choose = -1;
        for (int i = 0; i < toys.size(); i++) {
            count = count + toys.get(i).getChance();
            if (count >= variant) {
                choose = i;
                break;
            }
        }
        return choose;
    }

    private void addCycle() {
        put();
        get();
    }

    public void getResult(boolean append) {
        addCycle();
        result.forEach(System.out::println);
        save(append);
    }

    public void getRepeatResult(int repeat, boolean append) {
        int count = repeat;
        while (count > 0) {
            getResult(append);
            count--;
        }
    }
}
