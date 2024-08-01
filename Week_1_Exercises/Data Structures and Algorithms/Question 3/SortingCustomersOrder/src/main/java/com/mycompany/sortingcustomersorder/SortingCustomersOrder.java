/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sortingcustomersorder;

import java.util.Arrays;

/**
 *
 * @author shari
 */
public class SortingCustomersOrder {

    private static void swap(Order[] orderArray, int index1, int index2) {
        Order temp = orderArray[index1];
        orderArray[index1] = orderArray[index2];
        orderArray[index2] = temp;
    }

    private static int partition(Order[] orderArray, int start, int end) {
        Order pivot = orderArray[end];
        int index1 = start - 1;

        for (int index2 = start; index2 < end; index2++) {
            if (orderArray[index2].getOrderID() <= pivot.getOrderID()) {
                index1++;
                swap(orderArray, index1, index2);
            }
        }
        swap(orderArray, index1 + 1, end);
        return index1 + 1;
    }

    private static void quickSort(Order[] orderArray, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(orderArray, start, end);
            quickSort(orderArray, start, pivotIndex - 1);
            quickSort(orderArray, pivotIndex + 1, end);
        }
    }

    public static void main(String[] args) {
        Order[] orderArray = new Order[5];
        int[] orderID = {7654, 1234, 10987, 4567, 3452};
        String[] orderName = {"Grocery", "Electricals", "Electronics", "Dress", "Mobile Accessories"};
        double[] totalPrice = {230.23, 140.23, 567, 987.98, 456.78};

        for (int index = 0; index < 5; index++) {
            Order order = new Order();
            order.setOrderID(orderID[index]);
            order.setOrderName(orderName[index]);
            order.setTotalPrice(totalPrice[index]);
            orderArray[index] = order;
        }
        Order[] orderArrayCopy = orderArray;
        System.out.println("Sorting by Bubble Sort");
        for (int ptr1 = 0; ptr1 < 5; ptr1++) {
            for (int ptr2 = ptr1 + 1; ptr2 < 5; ptr2++) {
                if (orderArray[ptr1].getOrderID() > orderArray[ptr2].getOrderID()) {
                    Order tempOrder = orderArray[ptr1];
                    orderArray[ptr1] = orderArray[ptr2];
                    orderArray[ptr2] = tempOrder;
                }
            }
        }
        System.out.println("Sorted Array: ");
        for (int index = 0; index < 5; index++) {
            System.out.print(orderArray[index].getOrderID() + " -- ");
        }
        System.out.println();

        System.out.println("Sorting by QuickSort");
        quickSort(orderArrayCopy,0,4);
        System.out.println("Sorted Array: ");
        for (int index = 0; index < 5; index++) {
            System.out.print(orderArrayCopy[index].getOrderID() + " -- ");
        }
    }
}
