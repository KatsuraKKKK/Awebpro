package com.github.katsurakkkk.lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class LambdaTest {
	public static void main(String[] args) {
		LambdaTest LT = new LambdaTest();
//		LT.runnableTest();
//		LT.comparatorTest();
		LT.parrSteamTest();
	}
	
	public void parrSteamTest() {
		List<Person> pl = new ArrayList<Person>();
		for (int i = 0; i < 100; i++) {
			pl.add(new Person(i + ""));
		}
		
		pl.parallelStream().forEach((p) -> System.out.println(p.surName));
	}

	public void runnableTest() {
		System.out.println("=== RunnableTest ===");

		// Anonymous Runnable
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				System.out.println("Hello world one!");
			}
		};

		// Lambda Runnable
		Runnable r2 = () -> {
			System.out.println("Hello world two!");
			System.out.println("Hello world three!");
		};

		// Run em!
		r1.run();
		r2.run();
	}

	public void comparatorTest() {
		List<Person> personList = new ArrayList<Person>();
		personList.add(new Person("B"));
		personList.add(new Person("A"));
//		personList.stream().filter(p -> true).collect(Collectors.toList());
		
		phoneContacts(personList, (p) -> p.surName.equals("A"));
		
		// // Sort with Inner Class
		// Collections.sort(personList, new Comparator<Person>() {
		// public int compare(Person p1, Person p2) {
		// return p1.surName.compareTo(p2.surName);
		// }
		// });
		//
		// System.out.println("=== Sorted Asc SurName ===");
		// for (Person p : personList) {
		// p.printName();
		// }

		// Use Lambda instead
		// Print Asc
		System.out.println("=== Sorted Asc SurName ===");
		Collections.sort(personList, (p1, p2) -> p1.surName.compareTo(p2.surName));

		for (Person p : personList) {
			p.printName();
		}

		// Print Desc
		System.out.println("=== Sorted Desc SurName ===");
		Collections.sort(personList, (p1, p2) -> p2.surName.compareTo(p1.surName));

		for (Person p : personList) {
			p.printName();
		}

	}

	public void phoneContacts(List<Person> pl, MyTest<Person> aTest) {
		for (Person p : pl) {
			if (aTest.test(p)) {
				roboCall(p);
			}
		}
		
//		pl.parallelStream().forEach((p) -> p.surName.equals("A"));
	}

	public void roboCall(Person p) {
		System.out.println("Calling " + p.surName);
	}

	class Person {
		public String surName;

		public Person(String surName) {
			super();
			this.surName = surName;
		}

		public void printName() {
			System.out.println(this.surName);
		}
	}
}
