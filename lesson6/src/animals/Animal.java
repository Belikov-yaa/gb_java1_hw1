package animals;

public abstract class Animal {
    private static int count;

    public static int getCount() {
        return count;
    }

    int maxRunLength;
    int maxSwimLength;

    String name;

    public Animal(String name, int maxRunLength, int maxSwimLength) {
        count++;
        this.name = name;
        this.maxRunLength = maxRunLength;
        this.maxSwimLength = maxSwimLength;
    }

    public void run(int length) {
        System.out.println(this.name + actionCheck(length, this.maxRunLength, "пробежал", "пробежать"));
    }

    public void swim(int length) {
        System.out.println(this.name + actionCheck(length, this.maxSwimLength, "проплыл", "проплыть"));
    }

    private String actionCheck(int length, int maxLength, String actionName, String actionName2) {
        if (length <= maxLength)
            return " " + actionName + " " + length + "м";
        else
            return " не смог " + actionName2 + " " + length + "м";
    }

}
