package com.tarpitout.aio.algorithm;

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
     * 不稳定
     * 时间复杂度:O(n^2)
     * 最差时间复杂度:O(n^2)
     * 空间复杂度:O(1)
     * 使用场景:大部分元素有序
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
     * 不稳定
     * 时间复杂度:O(nlogn)
     * 最差时间复杂度:O(n^s) 1<s<2
     * 空间复杂度:O(1)
     * 使用场景:元素小于5000
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void shellSort(T[] elements, Comparator<T> comparator) {
        if (isInputInvalid(elements, comparator)) {
            return;
        }
        int length = elements.length;
        for (int gap = length/2; gap >= 1; gap /= 2) {
            for (int i = gap; i < length; i++) {
                T current = elements[i];
                int j;
                for (j = i; j >= gap; j = j - gap) {
                    if (comparator.compare(elements[j - gap], current) > 0) {
                        elements[j] = elements[j - gap];
                    } else {
                        break;
                    }
                }
                elements[j] = current;
            }
//            printArray(elements, "gap:" + gap);
        }
    }

    //================================================================
    // SelectSort
    //================================================================

    /**
     * 选择排序
     * 稳定
     * 时间复杂度:O(n^2)
     * 最差时间复杂度:O(n^2)
     * 空间复杂度:O(1)
     * 使用场景:n较少时
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
     * 时间复杂度:O(nlogn)
     * 最差时间复杂度:O(nlogn)
     * 空间复杂度:O(n)
     * 使用场景:n较大时
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
     * 稳定
     * 时间复杂度:O(n^2)
     * 空间复杂度:O(1)
     * 使用场景:n较小时
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
     * 不稳定
     * 时间复杂度:O(nlogn)
     * 最差时间复杂度:O(n^2)
     * 空间复杂度:O(logn)
     * 使用场景:由于是递归,不适合内存有限制的情况, n较大时
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
     * 不稳定
     * 时间复杂度:O(nlogn)
     * 最差时间复杂度:O(nlogn)
     * 空间复杂度:O(n)
     * 使用场景:n较大时
     * @param elements
     * @param comparator
     * @param <T>
     */
    public <T> void mergeSort(T[] elements, Comparator<T> comparator) {
        if (isInputInvalid(elements, comparator)) {
            return;
        }

        Object[] aux = new Object[elements.length];
        int start = 0, end = elements.length - 1;
        doMergeSort(elements, start, end, comparator, aux);
    }

    private <T> void doMergeSort(T[] elements, int start, int end, Comparator<T> comparator, Object[] aux) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        doMergeSort(elements, start, mid, comparator, aux);
        doMergeSort(elements, mid + 1, end, comparator, aux);
        merge(elements, start, mid, end, comparator, aux);
    }

    private <T> void merge(T[] elements, int start, int mid, int end, Comparator<T> comparator, Object[] aux) {
        int lb = start, rb = mid + 1, auxIndex = start;
        while (lb <= mid && rb <= end) {
            if (comparator.compare(elements[lb], elements[rb]) <= 0) {
                aux[auxIndex++] = elements[lb++];
            } else {
                aux[auxIndex++] = elements[rb++];
            }
        }

        if (lb < mid + 1) {
            while(lb <= mid) {
                aux[auxIndex++] = elements[lb++];
            }
        } else {
            while(rb <= end) {
                aux[auxIndex++] = elements[rb++];
            }
        }

        for(int i = start; i <= end; i++) {
            elements[i] = (T) aux[i];
        }
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

        dupArray = dupArray(elements);
        sort.mergeSort(dupArray, (o1, o2) -> o1 - o2);
        printArray(dupArray, "MergeSort");
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
