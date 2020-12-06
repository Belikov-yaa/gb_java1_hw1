
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Cat cat = new Cat("Murzik");
        Plate plate = new Plate(100);
        cat.eat(plate);
        plate.info();

        Dog dog = new Dog("Tuzik");
        dog.scare(cat);



    }
}
