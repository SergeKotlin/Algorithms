package HW1;

import java.util.Arrays;
import java.util.Random;

public class OnlyThreeTypes{

    private int sizeArray;
    private int maxRandomValue; // min = 0
    private int keyForSearching = 5; // min = 0
    private long startSpeedometer;

    public static void main(String[] args) {
        OnlyThreeTypes example = new OnlyThreeTypes();
        example.presentProgramPart1();
        example.presentProgramPart2();
    }

    private void presentProgramPart1() {
        sizeArray = 25;
        maxRandomValue = 9;
        int[] intArray = new int[sizeArray];
        Integer[] integerArray = new Integer[sizeArray]; // Ссылочные данные
        Integer[] integerCopiedArray;

        initIntegerArray(integerArray);

        startSpeedometer = System.nanoTime();
        System.out.println(Arrays.toString(integerArray));
        printTime("(Печать массива от Arrays)");
        startSpeedometer = System.nanoTime();
        integerCopiedArray = Arrays.copyOf(integerArray, sizeArray);
        printTime("(Время копирования массива через copyOf() от Arrays)");
        startSpeedometer = System.nanoTime();
        System.out.println(Arrays.toString(integerCopiedArray));
        printTime("(Печать второго массива от Arrays)");
        startSpeedometer = System.nanoTime();
        System.out.println(Arrays.compare(integerArray, integerCopiedArray));
        printTime("(Сравнение массивов через compare() от Arrays)");
        startSpeedometer = System.nanoTime();
        System.out.println(Arrays.equals(integerArray, new Integer[sizeArray-1]));
        printTime("(Сравнение массивов через equals() от Arrays)");
        sortArray(integerArray, "(Сортировка массива через sort() от Arrays)");
        System.out.println(Arrays.toString(integerArray));
        startSpeedometer = System.nanoTime();
        System.out.println(Arrays.binarySearch(integerCopiedArray, maxRandomValue));
        printTime("(Бинарный поиск в массиве через binarySearch() от Arrays)");
    }


    //TODO Наработки универсального метода
/*    integerArray = (Integer[]) inflateArray(10).toArray();
    private <fillers> List<fillers> inflateArray(int size) {
        List<fillers> newArray = new ArrayList<>(size);
        Random randomItem = new Random(100);
        for (fillers item : newArray) {
            item = (fillers) (Integer) randomItem.nextInt();
        }
        return newArray;
    }*/

//    private <Into> void comparison(Into item) {
//        this.startSpeedometer = System.nanoTime();
//        System.out.print("Ищем похожие переменные.. ");
//        if (item.equals(this.countInt) || item.equals(this.name)) {
//            System.out.print("всё в порядке");
//        } else {
//            System.out.print("переменные разные");
//        }
//        this.currentSpeed = System.nanoTime();
//        System.out.printf(" (Время выполнения %d нс)%n", printTime());
//    }

    private void presentProgramPart2() {
        sizeArray = 25;
        maxRandomValue = 9;
        Integer[] integerArray = new Integer[sizeArray]; // Ссылочные данные
        initIntegerArray(integerArray);

        System.out.println("\nЧасть вторая.");
        System.out.println(Arrays.toString(integerArray));

        startSpeedometer = System.nanoTime();
        System.out.println(linearSearch(integerArray) +
                " – получен индекс сопадающиего элемента в массиве ('-1' при отсутствии" +
                " совпадений)." + " Элемент сравнения " + keyForSearching);
        printTime("(Линейный поиск)");

        startSpeedometer = System.nanoTime();
        System.out.println(binarySearch(integerArray) +
                " – получен индекс сопадающиего элемента в массиве ('-1' при отсутствии" +
                " совпадений)." + " Элемент сравнения " + keyForSearching);
        printTime("(Бинарный поиск)");
    }

    private Integer linearSearch(Integer[] integerArray) {
        System.out.println("Линейный поиск");
        for (int i = 0; i < integerArray.length; i++) {
            if (integerArray[i] == keyForSearching)
                return i;
        }
        return -1;
    }

    private Integer binarySearch(Integer[] integerArray) {
        System.out.println("Бинарный или двоичный поиск");
        sortArray(integerArray, "(Массив отсортирован)");
        int startArr = 0;
        int endArr = integerArray.length-1;

        while (startArr <= endArr) {
            int middleOfArr = (startArr + endArr)/2;
            int midItem = integerArray[middleOfArr];

            if (midItem == keyForSearching) {
               return middleOfArr;
            } else if (midItem < keyForSearching) {
                startArr = middleOfArr + 1;
            } else if (midItem > keyForSearching) {
                endArr = middleOfArr - 1;
            }
        }
        return -1;
    }

    private void initIntegerArray(Integer[] integerArray) {
        Random random = new Random(sizeArray);
        for (int i = 0; i < sizeArray; i++) {
            integerArray[i] = random.nextInt(maxRandomValue+1);
        }
    }

    private void sortArray(Integer[] integerArray, String s) {
        startSpeedometer = System.nanoTime();
        Arrays.sort(integerArray);
        printTime(s);
        System.out.println(Arrays.toString(integerArray));
    }

    private void printTime(String moreInfo) {
        System.out.println(calcTime() + " нс " + moreInfo);
    }

    private long calcTime() {
        long currentSpeed = System.nanoTime();
        return (currentSpeed -startSpeedometer);
    }

    private void printTime() {
        printTime("");
    }

}

/* HW2, Algorithms.

Задание

✓ 2.1 На основе программного кода из домашнего задания №1 реализуйте массив на основе существующих примитивных ИЛИ ссылочных
 типов данных.
✓ Выполните обращение к массиву и базовые операции класса Arrays.
✓ Оценить выполненные методы с помощью базового класса System.nanoTime().
✓ 2.2 На основе написанного кода в задании 2.1 реализуйте линейный и двоичный поиск.
✓ Оценить алгоритмы линейного и двоичного поиска с помощью базового класса System.nanoTime(), при необходимости расширьте
 уже существующий массив данных.

- 2.3 Создайте массив размером 400 элементов.
- Выполните сортировку с помощью метода sort().
- Оцените сортировку с помощью базового класса System.nanoTime().
- 2.4 На основе существующего массива данных из задания 2.3 реализуйте алгоритм сортировки пузырьком.
- Оцените сортировку с помощью базового класса System.nanoTime().
- Сравните время выполнения алгоритмы сортировки методом sort() из задания 2.1 и сортировку пузырьком.
- 2.5 На основе массива данных из задания 2.3 реализуйте алгоритм сортировки методом выбора.
- Оцените сортировку с помощью базового класса System.nanoTime().
- Сравните со временем выполнения алгоритмов сортировки из прошлых заданий 2.3 и 2.4.
- На основе массива данных из задания 2.3 реализуйте алгоритм сортировки методом вставки.
- Оцените сортировку с помощью базового класса System.nanoTime().
- Сравните с временем выполнения алгоритмов сортировки из прошлых заданий 2.3, 2.4 и 2.5.

*/
// Serega, sure