package com.stepango.rxjava2tips;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

class NullObjectExample implements Executable {

    interface Animal {
        void makeSound();
    }

    class Dog implements Animal {
        public void makeSound() {
            System.out.println("woof!");
        }
    }

    enum NullAnimal implements Animal {
        INSTANCE;
        public void makeSound() {
        }
    }

    @Override
    public void execute() {
        List<Animal> animals = Arrays.asList(NullAnimal.INSTANCE, new Dog());
        Observable.fromIterable(animals)
                .filter(animal -> animal != NullAnimal.INSTANCE)
                .subscribe(Animal::makeSound);
    }

}
