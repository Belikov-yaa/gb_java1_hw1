package lesson1;

public class HomeWork1 {
    public static void main(String[] args) {

        String message = "Hello Word!";

        byte monthNumber = 28;
        short year = 2020;
        int studentsCount = 31;
        long populationOfEarth = 7_775_947_227L;

        float pi = 3.14159f;
        double e = 2.718281828;

        char smile = '\u263b';
        boolean isRun = false;
    }

    public static float calculate(float a, float b, float c, float d) {
        return a * (b + c / d);
    }

    public static boolean isSumLess20(int a, int b) {
        int sum = a + b;
        return (sum >= 10 && sum <= 20);
    }

    public static void printNegativeOrPositive(int number) {
        if (number < 0) {
            System.out.println("Число отрицательное");
        } else {
            System.out.println("Число положительное");
        }
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }

    public static void printHelloName(String name) {
        System.out.printf("Привет, %s!\n", name);
    }

    public static void isIntercalaryYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.printf("%d год високосный\n", year);
        } else {
            System.out.printf("%d год не високосный\n", year);
        }
    }
}
