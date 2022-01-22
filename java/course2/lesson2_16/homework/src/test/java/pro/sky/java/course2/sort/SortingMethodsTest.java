package pro.sky.java.course2.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.MList;

import java.util.Arrays;
import java.util.Random;

import static pro.sky.java.course2.sort.SortingMethods.*;


class SortingMethodsTest {
    MList mList = new MList();
    Random random = new Random();
    private static final int BIG_SIZE = 10_000;
    private Integer[] expectedArray;


    @BeforeEach
    void init() {
        expectedArray = new Integer[BIG_SIZE];
        mList.clear();
        for (int i = 0; i < BIG_SIZE; i++) {
            int nextInt = random.nextInt(BIG_SIZE);
            mList.add(nextInt);
            expectedArray[i] = nextInt;
        }
    }

    @Test
    void selectionSortTest() {
        long start = System.currentTimeMillis();
        MList sortedMList = selectionSort(mList);

        System.out.println("selectionSort");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println();

        Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, sortedMList.toArray());
    }

    @Test
    void bubbleSortTest() {
        long start = System.currentTimeMillis();
        MList sortedMList = bubbleSort(mList);

        System.out.println("bubbleSort");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println();

        Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, sortedMList.toArray());
    }

    @Test
    void insertionSortTest() {
        long start = System.currentTimeMillis();
        MList sortedMList = insertionSort(mList);

        System.out.println("insertionSort");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println();

        Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, sortedMList.toArray());
    }

    @Test
    void quickSortTest() {
        long start = System.currentTimeMillis();
        MList sortedMList = quickSort(mList);

        System.out.println("quickSort");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println();

        Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, sortedMList.toArray());
    }

}