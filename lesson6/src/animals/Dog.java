package animals;

public class Dog extends Animal {
    public static int getCount() {
        return countDogs;
    }

    private static int countDogs;

    public Dog(String name) {
        super(name, 500, 10);
        countDogs++;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name=" + name +
                ", maxRunLength=" + maxRunLength +
                ", maxSwimLength=" + maxSwimLength +
                '}';
    }
}
