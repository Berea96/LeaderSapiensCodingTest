package com.leaderSapiens;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

        Arrays.sort(array);

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

//        System.out.println("A - B의 차집합");
//        for(Integer i : aMinB) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
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

//        System.out.println("B - A의 차집합");
//        for(Integer i : bMinA) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
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

//        System.out.println("A, B의 교집합");
//        for(Integer i : aNb) {
//            System.out.print(i + " ");
//        }
//        System.out.println();

        return aNb;
    }

    public ArrayList<Integer> getAplusB() {
        ArrayList<Integer> aPlusB = new ArrayList<>();
        int check = 1;
        for(int a : this.a)
            aPlusB.add(a);
        for(int b : this.b) {
            for(int i : this.a) {
                if(i != b) check = 1;
                else {
                    check = 0;
                    break;
                }
            }
            if(check != 0) {
                aPlusB.add(b);
                check = 0;
            }
        }
        Collections.sort(aPlusB);

//        System.out.println("A, B의 합집합");
//        for(Integer i : aPlusB) {
//            System.out.print(i + " ");
//        }
//        System.out.println();

        return aPlusB;
    }

    public void getAnBminusAplusB() {
        ArrayList<Integer> aNb = getAnB();
        ArrayList<Integer> aPlusB = getAplusB();

        ArrayList<Integer> result = new ArrayList<>();

        int count = 0;
        for(int apb : aPlusB) {
            for(int anb : aNb) {
                if(anb != apb) count = 1;
                else {
                    count = 0;
                    break;
                }
            }
            if(count == 1) {
                result.add(apb);
            }
        }

//        System.out.println("합집합 - 교집합");
//        for(int r : result) {
//            System.out.print(r + " ");
//        }
//        System.out.println();
    }

    @Override
    public String toString() {
        return "arrayAB{" +
                "a=" + Arrays.toString(a) +
                ", b=" + Arrays.toString(b) +
                '}';
    }
}

interface CurrentTime {
    long currentTimePrint(String point);
}

interface DiffTime {
    void diffTimePrint(Long start, Long end);
}

public class Main {

//    public static long currentTimePrint(String point) {
//        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss:SSS");
//
//        long current = System.currentTimeMillis();
//        String currentTime = format1.format(System.currentTimeMillis());
//        System.out.println(point + " " + currentTime);
//
//        return current;
//    }

//    public static void diffTimePrint(Long start, Long end) {
//        System.out.println("실행 시간 " + (end - start) / 1000.0 + "초");
//    }

    public static void main(String[] args) {
        ArrayAB arrayAB = new ArrayAB();

        //현재 시간을 얻어오는 메소드
        //시작 시간을 가져옴
        //Long start = arrayAB.currentTimePrint("시작 시간");
        CurrentTime currentTime = point -> {
            SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss:SSS");

            long current = System.currentTimeMillis();
            String now = format1.format(System.currentTimeMillis());
            System.out.println(point + " " + now);

            return current;
        };

        Long start = currentTime.currentTimePrint("시작 시간");

        for(int i = 0; i < 1000; i++) {
            arrayAB.setA(arrayAB.setArray(new Integer[100]));
            arrayAB.setB(arrayAB.setArray(new Integer[100]));

            arrayAB.getAminusB();
            arrayAB.getBminusA();
            arrayAB.getAnB();
            arrayAB.getAplusB();
            arrayAB.getAnBminusAplusB();
        }

        //배열 출력 메소드
        //arrayAB.printArray("A", arrayAB.getA());
        //arrayAB.printArray("B", arrayAB.getB());

        //현재 시간을 얻어오는 메소드
        //끝난 시간을 가져옴
        //Long end = currentTimePrint("끝난 시간");
        Long end = currentTime.currentTimePrint("끝난 시간");

        //시작 시간과 끝난 시간의 차이를 구하는 메소드
        //diffTimePrint(start, end);
        DiffTime diffTime = (startTime, endTime) -> {
            System.out.println("실행 시간 " + (end - start) / 1000.0 + "초");
        };

        diffTime.diffTimePrint(start, end);
    }
}