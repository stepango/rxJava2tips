package com.stepango.rxjava2tips;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import io.reactivex.Observable;

class OptionalExample implements Executable {

    interface Animal {
        void makeSound();
    }

    class Dog implements Animal {
        public void makeSound() {
            System.out.println("woof!");
        }
    }

    public void execute() {
        List<Optional<Animal>> animals = Arrays.asList(
                Optional.<Animal>empty(), Optional.of(new Dog())
        );
        Observable.fromIterable(animals)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .subscribe(Animal::makeSound);
    }

}
