import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessWord {
    static final String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
            "pear", "pepper", "pineapple", "pumpkin", "potato"};

    public static void main(String[] args) {
        char[] mask = new char[15];
        Arrays.fill(mask, '#');
        Scanner sc = new Scanner(System.in);
        boolean isWordGuessed = false;

        String hiddenWord = words[new Random().nextInt(words.length)];
        System.out.println("Попробуйте отгадать слово (короче 15 букв). Если сдаетесь введите \"exit\"");

        while (!isWordGuessed) {
            String userAnswer = sc.nextLine().toLowerCase();
            if (userAnswer.equalsIgnoreCase("exit")) break;
            if (!userAnswer.equalsIgnoreCase(hiddenWord)) {
                for (int i = 0; i < Math.min(userAnswer.length(), hiddenWord.length()); i++) {
                    if (hiddenWord.charAt(i) == userAnswer.charAt(i))
                        mask[i] = hiddenWord.charAt(i);
                }
                printMaskArr(mask);
                Arrays.fill(mask, '#');
            } else {
                isWordGuessed = true;
            }
        }

        if (isWordGuessed)
            System.out.printf("Поздравляю Вы угадали слово - %s\n", hiddenWord);
        else
            System.out.printf("К сожалению, Вы не смогли угадать слово- %s\n", hiddenWord);
    }

    static void printMaskArr(char[] maskArray) {
        for (char c : maskArray) {
            System.out.print(c);
        }
        System.out.println();
    }
}
