package com.prasad.demo;

public class Student {

	private String name;
	private String className;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	
	public Student() {
		
	}
	public Student(String name, String className) {
		
		this.name = name;
		this.className = className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
