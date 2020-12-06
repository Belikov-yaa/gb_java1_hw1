
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String[] catsName = {"Мурзик", "Барсик", "Васька", "Мурка", "Уголёк"};
        Cat[] cats = new Cat[catsName.length];
        Plate plate = new Plate(50);

        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat(catsName[i], new Random().nextInt(10) + 5);
            cats[i].eat(plate);
        }

        for (Cat cat : cats) {
            if (cat.isFullness())
                System.out.println(cat.getName() + " сытый");
            else
                System.out.println(cat.getName() + " голодный");
        }

        plate.info();

    }
}
