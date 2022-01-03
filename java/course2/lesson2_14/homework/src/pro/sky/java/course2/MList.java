package pro.sky.java.course2;

import pro.sky.java.course2.exceptions.MListIndexOutOfBoundsException;
import pro.sky.java.course2.exceptions.NullArgumentException;
import pro.sky.java.course2.exceptions.NoSuchElementException;

import java.util.Arrays;

public class MList implements StringList {
    private String[] array;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public MList() {
        this(INITIAL_CAPACITY);
    }

    public MList(int initialCapacity) {
        array = new String[initialCapacity];
    }

    private String[] grow(String[] arr) {
        int newLength = arr.length * 3 / 2;
        return Arrays.copyOf(array, newLength);
    }

    @Override
    public String add(String item) {
        checkInputString(item);
        checkAndCorrectLength();
        array[size++] = item;
        return item;
    }

    private void checkInputString(String item) {
        if (item == null) {
            throw new NullArgumentException();
        }
    }

    private void checkAndCorrectLength() {
        if (size == array.length) {
            array = grow(array);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new MListIndexOutOfBoundsException();
        }
    }

    @Override
    public String add(int index, String item) {
        checkInputString(item);
        checkIndex(index);
        checkAndCorrectLength();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkInputString(item);
        checkIndex(index);
        array[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checkInputString(item);
        int index = indexOf(item);
        if (index < 0) {
            throw new NoSuchElementException();
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String itemToRemove = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        array[--size] = null;
        return itemToRemove;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(String item) {
        checkInputString(item);
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkInputString(item);
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(array, size);
    }
}
