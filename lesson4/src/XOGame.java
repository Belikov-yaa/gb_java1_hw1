import java.util.Random;
import java.util.Scanner;

public class XOGame {
    static final int SIZE = 5;
    static final int DOTS_TO_WIN = 3;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static final boolean SILLY_MODE = false;

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();


    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X, DOTS_TO_WIN)) {
                System.out.println("Вы выиграли!!!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O, DOTS_TO_WIN)) {
                System.out.println("Комьютер победил");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
    }

    static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("input coord X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = DOT_X;
    }

    static void aiTurn() {
        int x;
        int y;
        if (SILLY_MODE) {   // если включен "глупый режим", то генерируем случайный ход
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(y, x));
            map[y][x] = DOT_O;
        } else {
            if (findWinCell(DOTS_TO_WIN)) { // усли есть выигрышный ход то ходим туда и выходим из метода
                return;
            }

            if (findLoseCell(DOTS_TO_WIN)) { // усли есть выигрышный ход для сопарника то ходим туда и выходим из метода
                return;
            }

            if (findWinCell(DOTS_TO_WIN - 1)) { // если выигрышных нет, то можно ли построить линию -1 от победной
                return;
            }

            findMaxPriceCell(DOT_O); // если ничего ранее не сработало, ищем ячейки рядом со своими ходами

        }

    }

    public static void findMaxPriceCell(char dotO) {
        int x;
        int y;
        int maxCount = 0;
        int[][] mapCellPrice = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {  // i == x
            for (int j = 0; j < SIZE; j++) {  // j == y
                if (isCellValid(j, i)) {
                    int cellPrice = 0;
                    if (isValidCellContains(j, i - 1, dotO)) cellPrice++;
                    if (isValidCellContains(j, i + 1, dotO)) cellPrice++;
                    for (int k = 0; k < 3; k++) {
                        if (isValidCellContains(j - 1, i - 1 + k, dotO)) cellPrice++;
                        if (isValidCellContains(j + 1, i - 1 + k, dotO)) cellPrice++;
                    }
                    maxCount = Math.max(maxCount, cellPrice);
                    mapCellPrice[j][i] = cellPrice;
                }
            }
        }

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!(isCellValid(y, x) && mapCellPrice[y][x] == maxCount));
        map[y][x] = DOT_O;
    }

    private static boolean findWinCell(int dotsToWin) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (isCellValid(y, x)) {
                    map[y][x] = DOT_O;
                    if (checkWin(DOT_O, dotsToWin)) {
                        return true;
                    } else {
                        map[y][x] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }

    private static boolean findLoseCell(int dotsToWin) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (isCellValid(y, x)) {
                    map[y][x] = DOT_X;
                    if (checkWin(DOT_X, dotsToWin)) {
                        map[y][x] = DOT_O;
                        return true;
                    } else {
                        map[y][x] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }

    static boolean isCellValid(int y, int x) {
        return isValidCellContains(y, x, DOT_EMPTY);
    }

    static boolean isValidCellContains(int y, int x, char dot) {
        return isValidCoord(y, x) && map[y][x] == dot;
    }

    static boolean isValidCoord(int y, int x) {
        return y >= 0 && x >= 0 && y < SIZE && x < SIZE;
    }

    static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkWin(char dot, int dotsToWin) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (checkLineWin(y, x, 0, 1, dotsToWin, dot)) return true; // проверяем горизонтальные линии
                if (checkLineWin(y, x, 1, 0, dotsToWin, dot)) return true; // проверяем вертикальные линии
                if (checkLineWin(y, x, 1, 1, dotsToWin, dot)) return true; // проверяем горизонтальные линии
                if (checkLineWin(y, x, -1, 1, dotsToWin, dot)) return true; // проверяем горизонтальные линии
            }
        }
        return false;
    }

    static boolean checkLineWin(int y, int x, int dy, int dx, int dotsToWin, char dot) {
        boolean isLineExist = false;
        if (x + (dotsToWin - 1) * dx < SIZE
                && y + (dotsToWin - 1) * dy < SIZE
                && x + (dotsToWin - 1) * dx >= 0
                && y + (dotsToWin - 1) * dy >= 0) {
            for (int i = 0; i < dotsToWin; i++) {
                isLineExist = map[y + dy * i][x + dx * i] == dot;
                if (!isLineExist) break;
            }
        }
        return isLineExist;
    }

}
