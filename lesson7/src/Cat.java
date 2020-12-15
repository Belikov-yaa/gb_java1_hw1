public class Cat {
    private String name;
    private boolean fullness;
    private int doseSize;

    public int getDoseSize() {
        return doseSize;
    }

    public Cat(String name) {
        this.name = name;
        fullness = false;
    }

    public Cat(String name, int doseSize) {
        this(name);
        this.doseSize = doseSize;
    }

    public boolean isFullness() {
        return fullness;
    }

    public String getName() {
        return name;
    }

    public void eat(Plate plate) {
        if (plate.decreaseFood(doseSize)) {
            fullness = true;
            System.out.println("Кот " + name + " съел " + doseSize + " еды");
        } else {
            System.out.println("Кот " + name + " не смог съесть "+ doseSize + " еды");
        }
    }

    public void fear(Dog dog) {
        System.out.println("Кот " + name + " fear dog " + dog.getName());
    }

}
