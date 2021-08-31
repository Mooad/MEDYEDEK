package test.main;

import java.util.Set;

public  class Main {
    public static void main(String[] args) {

        Set<String> X= new Set<String>();
        Set<String> Y= new Set<String>();

        System.out.println("Hello World!" + Main.numElementsInCommon());
    }
    static int numElementsInCommon(Set s1, Set s2) {
        int result = 0;
        for (Object o1 : s1)
            if (s2.contains(o1))
                result++;
        return result;
    }
    }