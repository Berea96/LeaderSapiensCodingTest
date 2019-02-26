package com.leaderSapiens;

import java.util.Arrays;

class ArrayAB {
    private Integer[] a;
    private Integer[] b;

    public ArrayAB() {
    }

    public ArrayAB(Integer[] a, Integer[] b) {
        this.a = a;
        this.b = b;
    }

    public Integer[] getA() {
        return a;
    }

    public Integer[] getB() {
        return b;
    }

    public void setA(Integer[] a) {
        this.a = a;
    }

    public void setB(Integer[] b) {
        this.b = b;
    }

    public Integer[] setArray(int size, Integer[] array) {
        int count = 0;

        while(true) {
            int num = (int)((Math.random() * 300));

            if(count == size) break;
            else if(count != 0) {
                for (int i = 0; i <= count; i++) {
                    if (num == array[i]) break;
                    else {
                        array[count++] = num;
                        break;
                    }
                }
            }
            else array[count++] = num;
        }

        return array;
    }

    public void printArray(Integer[] array) {
        for(Integer obj : array) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public void sortArray(Integer[] array) {
        Arrays.sort(array);
    }

    @Override
    public String toString() {
        return "arrayAB{" +
                "a=" + Arrays.toString(a) +
                ", b=" + Arrays.toString(b) +
                '}';
    }
}

public class Main {

    public static void main(String[] args) {
        ArrayAB arrayAB = new ArrayAB();

        arrayAB.setA(new Integer[300]);
        arrayAB.setB(new Integer[100]);

        arrayAB.setA(arrayAB.setArray(300, arrayAB.getA()));
        arrayAB.setB(arrayAB.setArray(100, arrayAB.getB()));

        arrayAB.sortArray(arrayAB.getA());
        arrayAB.sortArray(arrayAB.getB());

        arrayAB.printArray(arrayAB.getA());
        arrayAB.printArray(arrayAB.getB());
    }
}
