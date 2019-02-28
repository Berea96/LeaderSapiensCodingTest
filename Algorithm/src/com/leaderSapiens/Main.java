package com.leaderSapiens;

import java.text.SimpleDateFormat;
import java.util.*;

class ArrayAB {
    private Integer[] a;
    private Integer[] b;

    private ArrayList<Integer> relativeComplementA;
    private ArrayList<Integer> relativeComplementB;
    private ArrayList<Integer> intersection;
    private ArrayList<Integer> union;
    private ArrayList<Integer> symmetricDifference;

    private ArrayAB() {
    }

    public static ArrayAB getArrayAB() {
        return new ArrayAB();
    }

    public Integer[] getA() {
        return a;
    }
    public Integer[] getB() {
        return b;
    }
    public ArrayList<Integer> getRelativeComplementA() {
        return relativeComplementA;
    }
    public ArrayList<Integer> getRelativeComplementB() {
        return relativeComplementB;
    }
    public ArrayList<Integer> getIntersection() {
        return intersection;
    }
    public ArrayList<Integer> getUnion() {
        return union;
    }
    public ArrayList<Integer> getSymmetricDifference() {
        return symmetricDifference;
    }

    public void setA(Integer[] a) {
        this.a = a;
    }
    public void setB(Integer[] b) {
        this.b = b;
    }

    //배열에 값을 랜덤으로 지정하는 메소드
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

    //배열을 순서대로 출력하는 메소드
    //Stream을 사용해보았다
    public void printArray(String target, Integer[] array) {
        System.out.print("output " + target + " : ");
        Arrays.stream(array).forEach(n ->System.out.print(n + " "));
        System.out.println();
    }

    //배열간 차집합을 구하는 메소드
    public void setRelativeComplement(String who, Integer[] array1, Integer[] array2) {
        ArrayList<Integer> relativeComplement = new ArrayList<>();
        int check = 0;
        for(int a : array1) {
            for (int b : array2) {
                if (a != b) check = 0;
                else {
                    check = 1;
                    break;
                }
            }
            if (check == 0) {
                relativeComplement.add(a);
                check = 0;
            }
        }

        if(who.equals("A")) relativeComplementA = relativeComplement;
        else relativeComplementB = relativeComplement;
    }

    //배열간 교집합을 구하는 메소드
    public void setIntersection() {
        intersection = new ArrayList<>();
        int check = 0;
        for(int a : this.a) {
            for(int b : this.b) {
                if(a != b) check = 0;
                else {
                    check = 1;
                    break;
                }
            }
            if(check == 1) {
                intersection.add(a);
                check = 0;
            }
        }
    }

    //배열간 합집합을 구하는 메소드
    public void setUnion() {
        union = new ArrayList<>();
        int check = 1;
        for(int a : this.a)
            union.add(a);
        for(int b : this.b) {
            for(int i : this.a) {
                if(i != b) check = 1;
                else {
                    check = 0;
                    break;
                }
            }
            if(check != 0) {
                union.add(b);
                check = 0;
            }
        }

        Collections.sort(union);
    }

    //배열간 대칭차집합을 구하는 메소드
    public void setSymmetricDifference(ArrayList<Integer> union, ArrayList<Integer> intersection) {

        symmetricDifference = new ArrayList<>();

        int count = 0;
        for(int u : union) {
            for(int i : intersection) {
                if(i != u) count = 1;
                else {
                    count = 0;
                    break;
                }
            }
            if(count == 1) {
                symmetricDifference.add(u);
            }
        }
    }

    //집합들을 출력해주는 메소드이다.
    public void printSet(String target, ArrayList<Integer> set) {
        System.out.println(target);
        set.stream().forEach(data -> System.out.print(data + " "));
        System.out.println();
    }

    @Override
    public String toString() {
        return "arrayAB{" +
                "a=" + Arrays.toString(a) +
                ", b=" + Arrays.toString(b) +
                '}';
    }
}

//현재 시간을 가져오기위한 인터페이스
//람다전용이다.
@FunctionalInterface
interface CurrentTime {
    long currentTimePrint(String point);
}

//시작 시간과 끝난 시간의 차이를 구하기위한 인터페이스
//람다전용이다.
@FunctionalInterface
interface DiffTime {
    void diffTimePrint(Long start, Long end);
}

public class Main {

    //현재 시간을 가져오는 메소드이다.
    //현재 람다식 사용으로 주석처리하였다.
//    public static long currentTimePrint(String point) {
//        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss:SSS");
//
//        long current = System.currentTimeMillis();
//        String currentTime = format1.format(System.currentTimeMillis());
//        System.out.println(point + " " + currentTime);
//
//        return current;
//    }

    //시작 시간과 끝난 시간의 차이를 출력하는 메소드이다
    //현재 람다식 사용으로 주석처리하였다.
//    public static void diffTimePrint(Long start, Long end) {
//        System.out.println("실행 시간 " + (end - start) / 1000.0 + "초");
//    }

    public static void main(String[] args) {
        ArrayAB arrayAB = ArrayAB.getArrayAB();

        //현재 시간을 얻어오는 인터페이스이다. 람다식으로 표현하였다.
        CurrentTime currentTime = point -> {
            SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss:SSS");

            long current = System.currentTimeMillis();
            String now = format1.format(System.currentTimeMillis());
            System.out.println(point + " " + now);

            return current;
        };

        //현재 시간을 얻어오는 메소드
        //시작 시간을 가져옴
        //Long start = arrayAB.currentTimePrint("시작 시간");
        Long start = currentTime.currentTimePrint("시작 시간");

        //실행 시간을 구하기위한 반복문
        for (int i = 0; i < 1000; i++) {
            arrayAB.setA(arrayAB.setArray(new Integer[100]));
            arrayAB.setB(arrayAB.setArray(new Integer[100]));

            arrayAB.setRelativeComplement("A", arrayAB.getA(), arrayAB.getB());
            arrayAB.setRelativeComplement("B", arrayAB.getB(), arrayAB.getA());
            arrayAB.setIntersection();
            arrayAB.setUnion();
            arrayAB.setSymmetricDifference(arrayAB.getUnion(), arrayAB.getIntersection());
        }

        //현재 시간을 얻어오는 메소드
        //끝난 시간을 가져옴
        //Long end = currentTimePrint("끝난 시간");
        Long end = currentTime.currentTimePrint("끝난 시간");

        //시작 시간과 끝난 시간의 차이를 구하는 인터페이스를 람다로 표현
        DiffTime diffTime = (startTime, endTime) -> {
            System.out.println("실행 시간 " + (end - start) / 1000.0 + "초");
        };

        //시작 시간과 끝난 시간의 차이를 구하는 메소드
        //diffTimePrint(start, end);
        diffTime.diffTimePrint(start, end);

        //배열 출력 메소드
        arrayAB.printArray("A", arrayAB.getA());
        arrayAB.printArray("B", arrayAB.getB());
        arrayAB.printSet("A - B의 차집합", arrayAB.getRelativeComplementA());
        arrayAB.printSet("B - A의 차집합", arrayAB.getRelativeComplementB());
        arrayAB.printSet("A, B의 교집합", arrayAB.getIntersection());
        arrayAB.printSet("A, B의 합집합", arrayAB.getUnion());
        arrayAB.printSet("합집합 - 교집합", arrayAB.getSymmetricDifference());
    }
}