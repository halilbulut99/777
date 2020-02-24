package fmi.informatics.extending;

import java.util.Calendar;

import java.util.concurrent.ThreadLocalRandom;

// Дефиниране на клас Student, който разширява абстрактния клас Person.
// Като наследник притежава всички характеристики и методи на класа родител
public class Student {

	private String firstName;
	private String secondName;
	private String lastName;
	private String speciality;
	private int facNumber;
	
	public Student(String firstName, String secondName, String lastName, String speciality, int facNumber) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
		this.speciality = speciality;
		this.facNumber = facNumber;
	}

	public Student(String firstName, String secondName, String lastName) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
	}

	public Student(String string, int parseInt, String string2, String string3, int i, int parseInt2, int parseInt3,
			int parseInt4) {
		// TODO Auto-generated constructor stub
	}

	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String fName) {
		this.firstName = firstName;
	}

	public String getsecondName() {
		return secondName;
	}

	public void setsecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getFacNumber() {
		return facNumber;
	}

	public void setFacNumber(int facNumber) {
		this.facNumber = facNumber;
	}
	
	
	// Пример за статичен вложен клас
	// Добавяме в класа Student помощен клас за произволно създаване на студенти - StudentGenerator
	public static class StudentGenerator {
		private static String[] firstNames = {"Иван", "Борис", "Гергана", "Ивана", "Михаил","Тома","Виктория"};
		private static String[] secondNames = {"Димитров", "Георгиев", "Петрова", "Иванова", "Борисов","Даниеле","Димитрова"};
		private static String[] lastNames = {"Иванов", "Илиев", "Пенева", "Иванова", "Василев","Касабе","Димитрова"};
		private static String[] specialities = {"Informatics", "STD", "BIT", "Mathematics"};
		
		public static Student make() {
			int arrayIndex = ThreadLocalRandom.current().nextInt(0, firstNames.length);
			String firstname = firstNames[arrayIndex];
			String secondName = secondNames[arrayIndex];
			String lastName = lastNames[arrayIndex];
			
			arrayIndex = ThreadLocalRandom.current().nextInt(0, specialities.length);
			String speciality = specialities[arrayIndex];
	
			int facNumber = ThreadLocalRandom.current().nextInt(111111, 999999);
				
			return new Student(firstname, secondName, lastName, speciality, facNumber);
		}
	}

	}
