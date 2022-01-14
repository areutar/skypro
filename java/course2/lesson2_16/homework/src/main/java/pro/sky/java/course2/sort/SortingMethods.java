package pro.sky.java.course2.sort;

import pro.sky.java.course2.MList;

import java.util.ArrayDeque;
import java.util.Deque;

public class SortingMethods {
    private void swap(MList mList, int i, int j) {
        Integer item = mList.get(i);
        mList.set(i, mList.get(j));
        mList.set(j, item);
    }

    public MList selectionSort(MList mList) {
        for (int i = 0; i < mList.size(); i++) {
            int currentMinIndex = i;
            for (int j = i + 1; j < mList.size(); j++) {
                if (mList.get(currentMinIndex) > mList.get(j)) {
                    currentMinIndex = j;
                }
            }
            if (currentMinIndex != i) {
                swap(mList, i, currentMinIndex);
            }
        }
        return mList;
    }

    public MList bubbleSort(MList mList) {
        for (int i = 0; i < mList.size() - 1; i++) {
            for (int j = 0; j < mList.size() - 1 - i; j++) {
                if (mList.get(j) > mList.get(j + 1)) {
                    swap(mList, j, j + 1);
                }
            }
        }
        return mList;
    }

    public MList insertionSort(MList mList) {
        for (int i = 1; i < mList.size(); i++) {
            for (int j = i; j > 0 && mList.get(j - 1) > mList.get(j); j--) {
                swap(mList, j - 1, j);
            }
        }
        return mList;
    }

    public MList quickSort(MList mList) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        stack.push(mList.size());

        while (!stack.isEmpty()) {
            int end = stack.pop();
            int start = stack.pop();
            if (end - start < 2) {
                continue;
            }
            int p = start + ((end - start) / 2);
            p = partition(mList, p, start, end);

            stack.push(p + 1);
            stack.push(end);

            stack.push(start);
            stack.push(p);

        }
        return mList;
    }

    private int partition(MList mList, int position, int start, int end) {
        int l = start;
        int h = end - 2;
        int pivot = mList.get(position);
        swap(mList, position, end - 1);

        while (l < h) {
            if (mList.get(l) < pivot) {
                l++;
            } else if (mList.get(h) >= pivot) {
                h--;
            } else {
                swap(mList, l, h);
            }
        }
        int idx = h;
        if (mList.get(h) < pivot) {
            idx++;
        }
        swap(mList, end - 1, idx);
        return idx;
    }

}
