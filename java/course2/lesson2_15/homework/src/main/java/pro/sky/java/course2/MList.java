package pro.sky.java.course2;

import pro.sky.java.course2.exceptions.MListIndexOutOfBoundsException;
import pro.sky.java.course2.exceptions.NoSuchElementException;
import pro.sky.java.course2.exceptions.NullArgumentException;

import java.util.Arrays;

public class MList implements IntegerList {
    private Integer[] data;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public MList() {
        this(INITIAL_CAPACITY);
    }

    public MList(int initialCapacity) {
        data = new Integer[initialCapacity];
    }

    private Integer[] grow(Integer[] arr) {
        int newLength = arr.length * 3 / 2;
        return Arrays.copyOf(data, newLength);
    }

    private void sort(Integer[] array) {
        for (int i = 0; i < size(); i++) {
            int currentMinIndex = i;
            for (int j = i + 1; j < size(); j++) {
                if (array[currentMinIndex] > array[j]) {
                    currentMinIndex = j;
                }
            }
            if (currentMinIndex != i) {
                Integer buf = array[i];
                array[i] = array[currentMinIndex];
                array[currentMinIndex] = buf;
            }
        }
    }

    @Override
    public Integer add(Integer item) {
        checkInputString(item);
        checkAndCorrectLength();
        data[size++] = item;
        return item;
    }

    private void checkInputString(Integer item) {
        if (item == null) {
            throw new NullArgumentException();
        }
    }

    private void checkAndCorrectLength() {
        if (size == data.length) {
            data = grow(data);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new MListIndexOutOfBoundsException();
        }
    }

    @Override
    public Integer add(int index, Integer item) {
        checkInputString(item);
        checkIndex(index);
        checkAndCorrectLength();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkInputString(item);
        checkIndex(index);
        data[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkInputString(item);
        int index = indexOf(item);
        if (index < 0) {
            throw new NoSuchElementException();
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer itemToRemove = data[index];
        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        data[--size] = null;
        return itemToRemove;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] copyArray = toArray();
        sort(copyArray);
        return binarySearch(copyArray, item) >= 0;
    }

    private int binarySearch(Integer[] sortedArray, Integer item) {
        int low = 0;
        int high = size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Integer midVal = sortedArray[mid];
            if (item.equals(midVal)) {
                return mid;
            }

            if (item > midVal) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    @Override
    public int indexOf(Integer item) {
        checkInputString(item);
        for (int i = 0; i < size; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkInputString(item);
        for (int i = size - 1; i >= 0; i--) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullArgumentException();
        }
        if (size != otherList.size()) {
            return false;
        }

        return Arrays.equals(otherList.toArray(), toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(data, null);
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(get(i)).append(", ");
        }
        return stringBuilder.append(get(size - 1)).toString();
    }
}
