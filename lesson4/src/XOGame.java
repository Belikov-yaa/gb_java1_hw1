import java.util.Random;
import java.util.Scanner;

public class XOGame {
    static final int SIZE = 3;
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
            if (checkWin(DOT_X)) {
                System.out.println("Вы выиграли!!!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
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
        if (SILLY_MODE) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(y, x));
        } else {
            int maxCount = 0;
            int[][] mapCellPrice = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {  // i == x
                for (int j = 0; j < SIZE; j++) {  // j == y
                    if (isCellValid(j, i)) {
                        int cellPrice = 0;
                        if (isValidCellContains(j, i - 1, DOT_O)) cellPrice++;
                        if (isValidCellContains(j, i + 1, DOT_O)) cellPrice++;
                        for (int k = 0; k < 3; k++) {
                            if (isValidCellContains(j - 1, i-1+k, DOT_O)) cellPrice++;
                            if (isValidCellContains(j + 1, i-1+k, DOT_O)) cellPrice++;
                        }
                        maxCount = maxCount < cellPrice ? cellPrice : maxCount;
                        mapCellPrice[j][i] = cellPrice;
                    }
                }
            }

            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!(isCellValid(y, x) && mapCellPrice[y][x]==maxCount));
        }
        map[y][x] = DOT_O;
    }

    static boolean isCellValid(int y, int x) {
        return isValidCellContains(y, x, DOT_EMPTY);
    }

    static boolean isValidCellContains(int y, int x, char dot) {
        return isValidCoord(y, x) && map[y][x] == dot;
    }

    static boolean isValidCoord(int y, int x) {
        if (y < 0 || x < 0 || y >= SIZE || x >= SIZE) {
            return false;
        }
        return true;
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

    static boolean checkWin(char c) {
        boolean isLineExist;

        // проверяем горизонтальные линии
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE - DOTS_TO_WIN + 1; x++) {
                isLineExist = true;
                for (int i = 0; i < DOTS_TO_WIN; i++) {
                    isLineExist = isLineExist && map[y][x + i] == c;
                    if (!isLineExist) break;
                }
                if (isLineExist) return true;
            }
        }

        // проверяем вертикальные линии
        for (int y = 0; y < SIZE - DOTS_TO_WIN + 1; y++) {
            for (int x = 0; x < SIZE; x++) {
                isLineExist = true;
                for (int i = 0; i < DOTS_TO_WIN; i++) {
                    isLineExist = isLineExist && map[y + i][x] == c;
                    if (!isLineExist) break;
                }
                if (isLineExist) return true;
            }
        }

        // проверяем диагонали вниз-вправо
        for (int y = 0; y < SIZE - DOTS_TO_WIN + 1; y++) {
            for (int x = 0; x < SIZE - DOTS_TO_WIN + 1; x++) {
                isLineExist = true;
                for (int i = 0; i < DOTS_TO_WIN; i++) {
                    isLineExist = isLineExist && map[y + i][x + i] == c;
                    if (!isLineExist) break;
                }
                if (isLineExist) return true;
            }
        }

        // проверяем диагонали вверх-вправо
        for (int y = DOTS_TO_WIN - 1; y < SIZE; y++) {
            for (int x = 0; x < SIZE - DOTS_TO_WIN + 1; x++) {
                isLineExist = true;
                for (int i = 0; i < DOTS_TO_WIN; i++) {
                    isLineExist = isLineExist && map[y - i][x + i] == c;
                    if (!isLineExist) break;
                }
                if (isLineExist) return true;
            }
        }

        return false;
    }

}
