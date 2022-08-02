package com.networkedassets.api.controllers;

public class Scratch {

    public static void main(String... args) {
        Person person = new Person("Old");
        System.out.println(person.getName()); // 1
        update(person);
        System.out.println(person.getName()); // 4
    }

    private static void update(final Person person) {
        System.out.println(person.getName()); // 2
        person.setName("New");
        System.out.println(person.getName()); // 3
    }

    static class Person {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

        public Person(String name) {
            this.name = name;
        }

    }

}
