package com.stepango.rxjava2tips;

public class Main {

    private static Executable[] examples = new Executable[]{
            new NullObjectExample(),
            new OptionalExample(),
            new StateExample()
    };

    public static void main(String[] args) {
        for (Executable example : examples) {
            example.execute();
        }
    }
}
