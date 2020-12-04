package animals;

public class Cat extends Animal {

    private static int countCats;

    public static int getCount() {
        return countCats;
    }

    public Cat(String name) {
        super(name, 200, 0);
        countCats++;
    }

    @Override
    public void swim(int length) {
        System.out.println(this.name + " не умеет плавать");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name=" + name +
                ", maxRunLength=" + maxRunLength +
                ", maxSwimLength=" + maxSwimLength +
                '}';
    }
}
