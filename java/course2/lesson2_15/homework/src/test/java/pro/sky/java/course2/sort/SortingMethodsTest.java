package pro.sky.java.course2.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.MList;

import java.util.Arrays;
import java.util.Random;


class SortingMethodsTest {
    SortingMethods sortingMethods = new SortingMethods();
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
    void selectionSort() {
        long start = System.currentTimeMillis();
        MList sortedMList = sortingMethods.selectionSort(mList);

        System.out.println("selectionSort");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println();

        Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, sortedMList.toArray());
    }

    @Test
    void bubbleSort() {
        long start = System.currentTimeMillis();
        MList sortedMList = sortingMethods.bubbleSort(mList);

        System.out.println("bubbleSort");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println();

        Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, sortedMList.toArray());
    }

    @Test
    void insertionSort() {
        long start = System.currentTimeMillis();
        MList sortedMList = sortingMethods.insertionSort(mList);

        System.out.println("insertionSort");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println();

        Arrays.sort(expectedArray);
        Assertions.assertArrayEquals(expectedArray, sortedMList.toArray());
    }

}