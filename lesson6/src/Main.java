import animals.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();

        animals.add(new Dog("Бобик"));
        animals.add(new Cat("Мурзик"));
        animals.add(new Cat("Барсик"));

        for (Animal pet : animals) {
            System.out.println(pet.toString());
        }

        System.out.println();
        System.out.println("Создано котов: " + Cat.getCount());
        System.out.println("Создано собак: " + Dog.getCount());
        System.out.println("Создано животных: " + Animal.getCount());

        System.out.println();
        animals.get(0).run(200);
        animals.get(0).swim(3);
        animals.get(1).run(100);
        animals.get(2).run(250);
        animals.get(2).swim(5);

    }
}
