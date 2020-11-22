import java.util.Arrays;

public class HomeWork2 {
    public static void main(String[] args) {

//      1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
//        С помощью цикла и условия заменить 0 на 1, 1 на 0;
        int[] arr1 = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        replace0to1(arr1);
        System.out.println(Arrays.toString(arr1));

//      2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        int[] arr2 = new int[8];
        fillArrArithmeticProgress(arr2, 3);
        System.out.println(Arrays.toString(arr2));

//      3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        doubleItemsLess6(arr3);
        System.out.println(Arrays.toString(arr3));

//      4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью
//        цикла(-ов) заполнить его диагональные элементы единицами;
        int[][] arr4 = new int[5][5];
        fillArrayDiagonals1(arr4);
        for (int i = 0; i < arr4.length; i++) {
            System.out.println(Arrays.toString(arr4[i]));
        }

//      5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
        int[] arr5 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int min = findMinValueArray(arr5);
        int max = findMaxValueArray(arr5);
        System.out.printf("min=%d, max=%d\n", min, max);

//       6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть
//       true, если в массиве есть место, в котором сумма левой и правой части массива равны.
//       Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
//       граница показана символами ||, эти символы в массив не входят.
        int[] arr6 = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println(checkBalance(arr6));

//        7. Написать метод, которому на вход подается одномерный массив и число n (может быть положительным,
//        или отрицательным), при этом метод должен сместить все элементы массива на n позиций. Элементы смещаются
//        циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами. Примеры: [ 1, 2, 3 ] при n = 1
//        (на один вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].
//        При каком n в какую сторону сдвиг можете выбирать сами.
        int[] arr7 = {1, 5, 3, 8, 11, 4, 9};
        System.out.println(Arrays.toString(arr7));
        shiftArrayV2(arr7, -8);
        System.out.println(Arrays.toString(arr7));
    }

    public static void replace0to1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else if (array[i] == 1) {
                array[i] = 0;
            }
        }
    }

    public static void fillArrArithmeticProgress(int[] array, int multiplier) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i * multiplier;
        }
    }

    public static void doubleItemsLess6(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    public static void fillArrayDiagonals1(int[][] array) {
        int arrSize = array.length;
        // проверка массива на "квадратность" и выход если условие не выполняется
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != arrSize) return;
        }

        for (int i = 0; i < array.length; i++) {
            array[i][i] = 1;
            array[i][arrSize - i - 1] = 1;
        }
    }

    public static int findMinValueArray(int[] array) {
        int result = array[0];
        if (array.length > 1) {
            for (int i = 1; i < array.length; i++) {
                if (result > array[i]) {
                    result = array[i];
                }
            }
        }
        return result;
    }

    public static int findMaxValueArray(int[] array) {
        int result = array[0];
        if (array.length > 1) {
            for (int i = 1; i < array.length; i++) {
                if (result < array[i]) {
                    result = array[i];
                }
            }
        }
        return result;
    }

    public static boolean checkBalance(int[] array) {
        if (array.length > 1) {
            int sumL = 0;
            int sumR = array[array.length - 1];
            for (int i = 0; i < array.length - 1; i++) {
                sumL += array[i];
            }
            if (sumL == sumR) return true;
            for (int i = array.length - 2; i > 1; i--) {
                sumL -= array[i];
                sumR += array[i];
                if (sumL == sumR) return true;
            }
        }
        return false;
    }

    public static void shiftArray(int[] array, int shiftN) {
        int arrLeight = array.length;
        // если количество сдвигов больше длины массивов, то можно их уменьшить на количество полных сдвигов = длине массива
        shiftN = shiftN % arrLeight;
        // сдвиг влево на n равен сдвигу вправо на array.length - n
        if (shiftN < 0) {
            shiftN = arrLeight + shiftN;
        }
        if (shiftN != 0) {
            int prevI = 0;
            int nextI = shiftN;
            int prevItem = array[prevI];
            int nextItem;

            do {
                nextItem = array[nextI];
                array[nextI] = prevItem;
                prevItem = nextItem;
                prevI = nextI;
                nextI = (prevI + shiftN) % arrLeight;
            } while (nextI != shiftN);
        }
    }

    public static void shiftArrayV2(int[] array, int shiftN) {
        int arrLeight = array.length;
        // если количество сдвигов больше длины массивов, то можно их уменьшить на количество полных сдвигов = длине массива
        // для этого можно просто сделать количество сдвигов = остатку от деления кол-ва сдвигов на длину массива
        // сдвиг влево на -n равен сдвигу вправо на (-n) + array.length
        shiftN = (shiftN % arrLeight + arrLeight) % arrLeight;

        if (shiftN != 0) {
            int prevI = 0;
            int nextI = shiftN;
            int prevItem = array[prevI];
            int nextItem;

            for (int i = 0; i < array.length; i++) {
                nextItem = array[nextI];
                array[nextI] = prevItem;
                prevItem = nextItem;
                prevI = nextI;
                nextI = (prevI + shiftN) % arrLeight;
            }
        }
    }
}