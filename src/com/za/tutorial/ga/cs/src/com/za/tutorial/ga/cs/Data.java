package com.za.tutorial.ga.cs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import java.util.ArrayList;
import com.tutorail.ga.cs.domain.Cours;
import com.tutorail.ga.cs.domain.Department;
import com.tutorail.ga.cs.domain.Instructor;
import com.tutorail.ga.cs.domain.MeetingTime;
import com.tutorail.ga.cs.domain.Room;
public class Data {
 private ArrayList<Room> rooms = new ArrayList<>(); ;
 private ArrayList<Instructor> instructors = new ArrayList<>() ;
 private ArrayList<Instructor> courseInstructors = new ArrayList<>();
 private ArrayList<Cours> courses = new ArrayList<>();
 private ArrayList<Department> depts = new ArrayList<>() ;
 private ArrayList<MeetingTime>meetingTimes = new ArrayList<>();
 private int numberOfClasses = 0;
 public Data () {
	 initialize();
	 }
 // Initialize the data by taking user input
 public void initialize() {
   try (Scanner scanner = new Scanner(System.in)) {
	// Get the number of rooms
	   System.out.print("Enter the number of rooms: ");
	   int numRooms = scanner.nextInt();
	   scanner.nextLine();
	// Iterate over each room and get its details from the user
	   for (int r = 1; r <= numRooms; r++) {
	        System.out.println("Enter the name and seating capacity of room " + r + " (separated by comma):");
	        String input = scanner.nextLine();

	        String[] parts = input.split(",");
	        if (parts.length != 2) {
	            System.out.println("Invalid input. Please enter the name and capacity separated by comma.");
	            r--; // Decrement r to repeat the current iteration
	            continue;
	        }

	        String name = parts[0].trim();
	        int seatingCapacity = Integer.parseInt(parts[1].trim());

	        Room room = new Room(name, seatingCapacity);
	        rooms.add(room);
	    }
	// Get the number of meeting times
	   System.out.print("Enter the number of meeting times: ");
	   int numMeetingTimes = scanner.nextInt();
	   scanner.nextLine();
	// Iterate over each meeting time and get its details from the user
	   for (int m = 1; m <= numMeetingTimes; m++) {
		    System.out.println("Enter the id and time of meeting time " + m + " (separated by comma):");
		    String input = scanner.nextLine();

		    String[] parts = input.split(",");
		    if (parts.length != 2) {
		        System.out.println("Invalid input. Please enter the id and time separated by comma.");
		        m--; // Decrement m to repeat the current iteration
		        continue;
		    }

		    String id = parts[0].trim();
		    String time = parts[1].trim();

		    MeetingTime meetingTime = new MeetingTime(id, time);
		    meetingTimes.add(meetingTime);
		}
	// Get the number of instructors
	   System.out.print("Enter the number of instructors: ");
	   int nbrInstructors = scanner.nextInt();
	   scanner.nextLine();
	   // Iterate over each instructor and get their details from the user
	   for (int t = 1; t <= nbrInstructors; t++) {
		    System.out.println("Enter the id and name of instructor " + t + " (separated by comma):");
		    String input = scanner.nextLine();

		    String[] parts = input.split(",");
		    if (parts.length != 2) {
		        System.out.println("Invalid input. Please enter the id and name separated by comma.");
		        t--; // Decrement t to repeat the current iteration
		        continue;
		    }

		    String id = parts[0].trim();
		    String name = parts[1].trim();

		    Instructor instructor = new Instructor(id, name);
		    instructors.add(instructor);
		}
	// Get the number of departments
	   System.out.print("Enter the number of departments: ");
	   int numDepartments = scanner.nextInt();
	   scanner.nextLine();
	// Iterate over each department and get its details from the user
	   for(int d=1;d<=numDepartments;d++) {
		   System.out.println("Enter the name of the department"+ d +": ");
		   String name = scanner.nextLine();
		   System.out.print("Enter the number of courses in Department " + d + ": ");
		   int numCourses = scanner.nextInt();
		   scanner.nextLine();
		// Create a list to hold the instructors for the course
		   ArrayList<Cours> departmentCourses = new ArrayList<Cours>();
	   for(int c =1;c<=numCourses;c++) {
		   
		   System.out.print("Enter Course id"+ c +":");
		   String number = scanner.nextLine();
		   System.out.print("Enter course name"+ c +": ");
		   String coursename = scanner.nextLine();
		// Iterate over each instructor ID for the course and find the corresponding instructor object
		   System.out.print("Enter the number of instructors for course"+ c +": ");
		   int numInstructors = scanner.nextInt();
		   scanner.nextLine();
	       System.out.println("Enter the instructor IDs for course"+ c +": ");
	       for(int n = 1;n<= numInstructors;n++) {
	    	   System.out.print("Instructor" + n + "ID:");
	    	   String id = scanner.nextLine();
	    	   Instructor instructor = findInstructorById(instructors, id);
	    	   if (instructor !=null) {
	    		   courseInstructors.add(instructor);
	    	   }else {
	    		   System.out.println("Instructor with ID"+ id +"not found!");
	    		     }	   
	       }
	       System.out.print("Enter the maximum capacityt for course"+ c +": "); 
	       int maxNumbsOfStudents = scanner.nextInt();
	       scanner.nextLine();
	       Cours course = new Cours(number, coursename, courseInstructors, maxNumbsOfStudents);
	       courses.add(course);
	       departmentCourses.add(course);
	   }
	    Department dept = new Department(name, departmentCourses);
	    depts.add(dept);
	    }
}
// Calculate the total number of classes
   depts.forEach(x -> numberOfClasses += x.getCourses().size());
    }
//Helper method to find an instructor by ID in a list of instructors
 private static Instructor findInstructorById(List<Instructor> instructors, String id) {
	 for(Instructor instructor : instructors ) {
		 if(instructor.getId().equals(id)) {
			 return instructor;
		 }
	 }
	 return null;
 }
//Getter methods for accessing the dataI
 public ArrayList<Room> getRooms(){ return rooms; } 
 public ArrayList<Instructor> getInstructors(){ return instructors; }
 public ArrayList<Cours> getCourses(){ return courses; }
 public ArrayList<Department> getDepts(){ return depts; } 
 public ArrayList<MeetingTime> getMeetingTimes(){ return meetingTimes; } 
 public int getNumberOfClasses() {return numberOfClasses; }
}
