package com.leaderSapiens;

import java.util.ArrayList;
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

    public Integer[] setArray(Integer[] array) {
        int count = 0;

        while(true) {
            int num = (int)((Math.random() * 300));

            if(count == 0) array[count++] = num;
            else if(count == array.length) break;
            else {
                int checkNum = 0;
                for (int i = 0; i < count; i++) {
                    if (num == array[i]) {
                        checkNum = 0;
                        break;
                    }
                    else {
                        checkNum = 1;
                    }
                }
                if(checkNum == 1) array[count++] = num;
            }
        }
        return array;
    }

    public void printArray(String target, Integer[] array) {
        System.out.print("output " + target + " : ");
        for(Integer obj : array) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public void sortArray(Integer[] array) {
        Arrays.sort(array);
    }

    public void getAminusB() {
        ArrayList<Integer> aMinB = new ArrayList<>();
        int check = 0;
        for(int a : this.a) {
            for(int b : this.b) {
                if(a != b) check = 0;
                else {
                    check = 1;
                    break;
                }
            }
            if(check == 0) {
                aMinB.add(a);
                check = 0;
            }
        }

        System.out.println("A - B의 차집합");
        for(Integer i : aMinB) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void getBminusA() {
        ArrayList<Integer> bMinA = new ArrayList<>();
        int check = 0;
        for(int b : this.b) {
            for(int a : this.a) {
                if(b != a) check = 0;
                else {
                    check = 1;
                    break;
                }
            }
            if(check == 0) {
                bMinA.add(b);
                check = 0;
            }
        }

        System.out.println("B - A의 차집합");
        for(Integer i : bMinA) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public ArrayList<Integer> getAnB() {
        ArrayList<Integer> aNb = new ArrayList<>();
        int check = 0;
        for(int a : this.a) {
            for(int b : this.b) {
                if(a != b) check = 0;
                else {
                    check = 1;
                    break;
                }
            }
            if(check != 0) {
                aNb.add(a);
                check = 0;
            }
        }

        System.out.println("A, B의 교집합");
        for(Integer i : aNb) {
            System.out.print(i + " ");
        }
        System.out.println();

        return aNb;
    }

    public ArrayList<Integer> getAplusB() {
        ArrayList<Integer> aPlusB = new ArrayList<>();
        int check = 1;
        for(int a : this.a)
            aPlusB.add(a);
        for(int i : this.a) {
            for(int b : this.b) {
                if(i != b) check = 1;
                else {
                    check = 0;
                    break;
                }
            }
            if(check != 0) {
                aPlusB.add(i);
                check = 0;
            }
        }

        System.out.println("A, B의 합집합");
        for(Integer i : aPlusB) {
            System.out.print(i + " ");
        }
        System.out.println();

        return aPlusB;
    }

    public void getAnBminusAplusB() {
        ArrayList<Integer> aNB = getAnB();
        ArrayList<Integer> aPlusB = getAplusB();

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

        arrayAB.setA(new Integer[100]);
        arrayAB.setB(new Integer[100]);

        arrayAB.setA(arrayAB.setArray(arrayAB.getA()));
        arrayAB.setB(arrayAB.setArray(arrayAB.getB()));

        arrayAB.sortArray(arrayAB.getA());
        arrayAB.sortArray(arrayAB.getB());

        arrayAB.printArray("A", arrayAB.getA());
        arrayAB.printArray("B", arrayAB.getB());

        arrayAB.getAminusB();
        arrayAB.getBminusA();
        arrayAB.getAnB();
        arrayAB.getAplusB();
    }
}