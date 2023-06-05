package com.tutorail.ga.cs.domain;
import java.util.ArrayList;
public class Cours {
  private String number = null;
  private String coursename = null;
  private int maxNumbsOfStudents;
  private ArrayList<Instructor> instructors;
  public Cours(String number,String coursename,ArrayList<Instructor> instructors,int maxNumbOfStudents){
	 this.number = number;
	 this.coursename = coursename;
	 this.instructors = instructors;
	 this.maxNumbsOfStudents = maxNumbOfStudents;
  }
  public String getNumber() {return number;}
  public String getName() {return coursename;}
  public ArrayList<Instructor> getInstructors(){return instructors;}
  public int getMaxNumbOfStudents() {return maxNumbsOfStudents;}
  public String toString() {return coursename;}
  
   
}
