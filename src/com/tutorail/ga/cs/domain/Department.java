package com.tutorail.ga.cs.domain;
import java.util.ArrayList;
public class Department {
private String name;
private ArrayList<Cours> courses;
public Department(String name,ArrayList<Cours> courses) {
	this.name = name;
	this.courses = courses;
}
public String getName() {return name;}
public ArrayList<Cours> getCourses(){return courses;}


}
