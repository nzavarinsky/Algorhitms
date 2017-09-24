package com.sorts;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SortingRealisations {
    int[] quickSortArray;
    int[] selectionSortArray;
    int quickSortCounter = 0;
    int selectionSortCounter = 0; // size of array from file
    private static int selectionSwaps = 0;
    private static int selectionComparisons = 0;
    private static int quickSortSwaps = 0;
    private static int quickSortComparisons = 0;

    public void readArrayForQuickSort() throws IOException {
        Scanner scan = new Scanner(new File("10_input.txt")); //provide file name from outside
        quickSortArray = new int[quickSortCounter];
        while (scan.hasNextInt()) {
            quickSortCounter++;
            scan.nextInt();

        }
        Scanner scan2 = new Scanner(new File("10_input.txt"));
        quickSortArray = new int[quickSortCounter];
        for (int i = 0; i < quickSortCounter; i++) {
            quickSortArray[i] = scan2.nextInt(); //fill the array with the integers
        }
    }

    public int partition(int p, int q) {
        int i = p;
        int j = q + 1;
        // Get the pivot element from the middle of the list
        int pivot = quickSortArray[p];
        // Divide into two lists
        do {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            do {
                quickSortComparisons++;
                i++;// As we not get we can increase i
            } while (quickSortArray[i] < pivot && i<q);
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            do {
                quickSortComparisons++;
                j--;// As we not get we can increase j
            } while (quickSortArray[j] > pivot);

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            if (i < j) {
                swap(i, j);
            }
        } while (i < j);
        // swap the pivot element and j th element
        swap(p, j);
        quickSortSwaps++;
        return j;

    }

    private void swap(int p, int j) {
        // exchange the elements
        int temp = quickSortArray[p];
        quickSortArray[p] = quickSortArray[j];
        quickSortArray[j] = temp;
    }

    public void quicksort() {
        // Recursion
        quicksort(0, quickSortCounter - 1);
    }

    public void quicksort(int p, int q) {
        int j;
        if (p < q) {
            // Divide into two lists
            j = partition(p, q);
            // Recursion
            quicksort(p, j - 1);
            quicksort(j + 1, q);
        }
    }

    public void print() {
        // print the elements of array
        for (int i = 0; i < quickSortCounter; i++) {
            System.out.print(quickSortArray[i] + " | ");

        }
        System.out.println("");
    }

    public void readArrayForSelecitonSort() throws IOException {
        Scanner scan = new Scanner(new File("10_input.txt")); //provide file name from outside
        while (scan.hasNextInt()) {
            selectionSortCounter++;
            scan.nextInt();
        }
        Scanner scan2 = new Scanner(new File("10_input.txt"));
        selectionSortArray = new int[selectionSortCounter];
        for (int i = 0; i < selectionSortCounter; i++) {
            selectionSortArray[i] = scan2.nextInt(); //fill the array with the integers
        }
    }

    public void selectionSort() throws IOException {

        for (int last = selectionSortArray.length - 1; last > 0; last--) {

            int largestIndex = last;    //Int which places the largest number at the end of the array

            // Find largest number
            for (int i = 0; i < last; i++) {

                //if i > the last number
                if (selectionSortArray[i] > selectionSortArray[largestIndex]) {
                    largestIndex = i;       //switch the last number and i
                } // end if

                //Comparison+1
                selectionComparisons++;

            } // end for
            // Swap last element with largest element
            int largest = selectionSortArray[last];
            selectionSortArray[last] = selectionSortArray[largestIndex];
            selectionSortArray[largestIndex] = largest;
            selectionSwaps++;

        }
    }

    public void swapsCounter() {
        System.out.println("Comparations : " + selectionComparisons + "\n"
                + "Swaps : " + selectionSwaps);
    }

    public void printSelectionSortArray() {
        for (int i = 0; i < selectionSortCounter; i++) {
            System.out.print(selectionSortArray[i] + " | ");
        }
        System.out.println("");
    }

    public static void main(String args[]) throws IOException {
        SortingRealisations quickSorter = new SortingRealisations();
        quickSorter.readArrayForQuickSort();
        System.out.println("----------------------\n" + "QuickSort\n" +
                "----------------------\n");
        System.out.println("Array before sort :");
        quickSorter.print();
        quickSorter.quicksort();
        System.out.println("\n" + "Array after sort :");
        quickSorter.print();
        System.out.println();
        System.out.println("Comparations : " + quickSortComparisons + "\n"
                + "Swaps : " + quickSortSwaps);
        SortingRealisations selectionSorter = new SortingRealisations();
        selectionSorter.readArrayForSelecitonSort();
        System.out.println("----------------------\n" + "SelectionSort\n" +
                "----------------------\n");
        System.out.println("Array before sort : ");
        selectionSorter.printSelectionSortArray();
        selectionSorter.selectionSort();
        System.out.println("\n" + "Array after sort :");
        selectionSorter.printSelectionSortArray();
        System.out.println();
        selectionSorter.swapsCounter();

    }
}