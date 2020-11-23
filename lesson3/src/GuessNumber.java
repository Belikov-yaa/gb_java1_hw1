import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            newGame();
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            if (sc.nextInt() != 1) break;
        }
    }

    static void newGame() {
        Random random = new Random();
        int number = random.nextInt(10);
        System.out.println("Попробуйте угадать число от 0 до 9");
        boolean isWin = false;
        for (int i = 0; i < 3; i++) {
            int userAnswer = sc.nextInt();
            if (userAnswer > number) {
                System.out.println("Загаданное число меньше");
                System.out.println("Загаданное число больше");
            } else {
                isWin = true;
                break;
            }

        }
        System.out.println("Игра закончена.");
        if (isWin)
            System.out.println("Поздравляю! Вы угадали число.");
        else
            System.out.println("Попытки закончились. Вы не смогли угадать число");
    }
}
