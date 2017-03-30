package com.stepango.rxjava2tips;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

class StateExample implements Executable {

    interface Animal {
        void makeSound();
    }

    class Dog implements Animal {
        public void makeSound() { System.out.println("woof!"); }
    }

    interface AnimalState {
        boolean hasAnimal();
        Animal animal();
        String errorMsg();
    }

    class AnimalStateSuccess implements AnimalState {
        Animal animal;
        AnimalStateSuccess(Animal animal) { this.animal = animal; }
        @Override public boolean hasAnimal() { return true;}
        @Override public Animal animal() { return animal; }
        @Override public String errorMsg() { return null; }
    }

    enum AnimalStateError implements AnimalState {
        INSTANCE;
        @Override public boolean hasAnimal() { return false;}
        @Override public Animal animal() { return null; }
        @Override public String errorMsg() { return "We lost him"; }
    }

    public void execute() {
        List<AnimalState> animals = Arrays.asList(
                AnimalStateError.INSTANCE, new AnimalStateSuccess(new Dog())
        );
        Observable.fromIterable(animals)
                .filter(AnimalState::hasAnimal)
                .map(AnimalState::animal)
                .subscribe(Animal::makeSound);
    }

}
