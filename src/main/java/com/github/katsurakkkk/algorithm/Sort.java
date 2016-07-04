package com.github.katsurakkkk.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by katsurakkkk on 7/4/16.
 */
public class Sort {
    //================================================================
    // InsertSort
    //================================================================

    /**
     * 直接插入排序
     *
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void insertSort(T[] elements, Comparator<T> comparator) {
        if (isInputInvalid(elements, comparator)) {
            return;
        }

        int length = elements.length;
        for (int i = 1; i < length; i++) {
            T current = elements[i];
            int j;
            for (j = i; j > 0; j--) {
                if (comparator.compare(elements[j - 1], current) > 0) {
                    elements[j] = elements[j - 1];
                } else {
                    break;
                }
            }
            elements[j] = current;
        }
    }

    /**
     * 希尔排序
     *
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void shellSort(T[] elements, Comparator<T> comparator) {

    }

    //================================================================
    // SelectSort
    //================================================================

    /**
     * 选择排序
     *
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void selectSort(T[] elements, Comparator<T> comparator) {
        if (isInputInvalid(elements, comparator)) {
            return;
        }

        int length = elements.length;
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (comparator.compare(elements[min], elements[j]) > 0) {
                    min = j;
                }
            }
            if (min != i) {
                swap(elements, min, i);
            }
        }
    }

    /**
     * 堆排序
     *
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void heapSort(T[] elements, Comparator<T> comparator) {
        if (isInputInvalid(elements, comparator)) {
            return;
        }

        PriorityQueue<T> heap = new PriorityQueue(elements.length, comparator);
        for (T element : elements) {
            heap.add(element);
        }
        for (int i = 0; i < elements.length; i++) {
            elements[i] = heap.poll();
        }
    }

    //================================================================
    // SwapSort
    //================================================================

    /**
     * 冒泡排序
     *
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void bubbleSort(T[] elements, Comparator<T> comparator) {
        if (isInputInvalid(elements, comparator)) {
            return;
        }

        int length = elements.length;
        for (int i = 1; i < length; i++) {
            for (int j = length - 1; j >= i; j--) {
                if (comparator.compare(elements[j - 1], elements[j]) > 0) {
                    swap(elements, j - 1, j);
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void quickSort(T[] elements, Comparator<T> comparator) {
        if (isInputInvalid(elements, comparator)) {
            return;
        }
        doQuickSort(elements, 0, elements.length - 1, comparator);
    }

    private <T> void doQuickSort(T[] elements, int start, int end, Comparator<T> comparator) {
        if (start >= end) {
            return;
        }
        int pivot = partition(elements, start, end, comparator);
        doQuickSort(elements, start, pivot - 1, comparator);
        doQuickSort(elements, pivot + 1, end, comparator);
    }

    private <T> int partition(T[] elements, int start, int end, Comparator<T> comparator) {
        T pivot = elements[start];
        int pivotIndex = start, forward = start, back = end;
        while (forward < back) {
            for (; comparator.compare(pivot, elements[forward]) >= 0 && forward < end; forward++) {}
            for (; comparator.compare(pivot, elements[back]) <= 0 && back > start; back--) {}
            if (forward < back) {
                swap(elements, forward++, back--);
            }
        }
        swap(elements, back, pivotIndex);
        return back;
    }

    /**
     * 归并排序
     *
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void mergeSort(T[] elements, Comparator<T> comparator) {

    }


    public <T> void countingSort(T[] elements, Comparator<T> comparator) {

    }

    /**
     * 基数排序
     *
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void radixSort(T[] elements, Comparator<T> comparator) {

    }

    /**
     * 桶排序
     *
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void bucketSort(T[] elements, Comparator<T> comparator) {

    }

    private <T> boolean isInputInvalid(T[] elements, Comparator<T> comparator) {
        return elements == null || elements.length == 0 || comparator == null;
    }

    private <T> void swap(T[] elements, int i, int j) {
        T temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }


    public static void main(String[] args) {
        Integer[] elements = {3, 543, 54, 5, 6, 2, 67, 3, 65, 4};
//        Integer[] elements = {0,0,0,0,0,0,0,0,0,0,0};
        printArray(elements, "OriginalArray");

        Sort sort = new Sort();

        Integer[] dupArray = dupArray(elements);
        sort.bubbleSort(dupArray, (o1, o2) -> o1 - o2);
        printArray(dupArray, "BubbleSort");

        dupArray = dupArray(elements);
        sort.insertSort(dupArray, (o1, o2) -> o1 - o2);
        printArray(dupArray, "InsertSort");

        dupArray = dupArray(elements);
        sort.selectSort(dupArray, (o1, o2) -> o1 - o2);
        printArray(dupArray, "SelectSort");

        dupArray = dupArray(elements);
        sort.heapSort(dupArray, (o1, o2) -> o1 - o2);
        printArray(dupArray, "HeapSort");

        dupArray = dupArray(elements);
        sort.quickSort(dupArray, (o1, o2) -> o1 - o2);
        printArray(dupArray, "QuickSort");

        dupArray = dupArray(elements);
        sort.shellSort(dupArray, (o1, o2) -> o1 - o2);
        printArray(dupArray, "ShellSort");
    }

    private static <T> T[] dupArray(T[] array) {
        return Arrays.copyOf(array, array.length);
    }

    private static <T> void printArray(T[] array, String des) {
        System.out.println(arrayToString(array) + " :" + des);
    }

    public static <T> String arrayToString(T[] array) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("{");
        for (T item : array) {
            resultBuilder.append(item).append(",");
        }
        resultBuilder.deleteCharAt(resultBuilder.length() - 1);
        resultBuilder.append("}");
        return resultBuilder.toString();
    }
}
