package com.za.tutorial.ga.cs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

//import java.util.ArrayList;
import com.tutorail.ga.cs.domain.Cours;
import com.tutorail.ga.cs.domain.Department;
import com.tutorail.ga.cs.domain.Instructor;
import com.tutorail.ga.cs.domain.MeetingTime;
import com.tutorail.ga.cs.domain.Room;
public class Data {
    private ArrayList<Room> rooms;
    private ArrayList<Instructor> instructors;
    private ArrayList<MeetingTime> meetingTimes;
    private ArrayList<Cours> courses;
    private ArrayList<Department> depts;
    private int numberOfClasses;

    public Data() {
        rooms = new ArrayList<>();
        instructors = new ArrayList<>();
        meetingTimes = new ArrayList<>();
        courses = new ArrayList<>();
        depts = new ArrayList<>();
        numberOfClasses = 0;
    }

    public void initialize(JTextField roomsNumber, JTextField meetingTimesnumber, JTextField instructorsNumber, JTextField departmentNumber) {
        int numRooms = Integer.parseInt(roomsNumber.getText());
        int numMeetingTimes = Integer.parseInt(meetingTimesnumber.getText());
        int numInstructors = Integer.parseInt(instructorsNumber.getText());
        int numDepartments = Integer.parseInt(departmentNumber.getText());

        // Get the details for each room from the user interface
        for (int r = 1; r <= numRooms; r++) {
            String name = JOptionPane.showInputDialog("Enter the name of room " + r + ":");
            int seatingCapacity = Integer.parseInt(JOptionPane.showInputDialog("Enter the seating capacity of room " + r + ":"));

            Room room = new Room(name, seatingCapacity);
            rooms.add(room);
        }

        // Get the details for each meeting time from the user interface
        for (int m = 1; m <= numMeetingTimes; m++) {
            String id = JOptionPane.showInputDialog("Enter the ID of meeting time " + m + ":");
            String time = JOptionPane.showInputDialog("Enter the time for meeting time " + m + ":");

            MeetingTime meetingTime = new MeetingTime(id, time);
            meetingTimes.add(meetingTime);
        }

        // Get the details for each instructor from the user interface
        for (int i = 1; i <= numInstructors; i++) {
            String id = JOptionPane.showInputDialog("Enter the ID of instructor " + i + ":");
            String name = JOptionPane.showInputDialog("Enter the name of instructor " + i + ":");

            Instructor instructor = new Instructor(id, name);
            instructors.add(instructor);
        }

        // Get the details for each department from the user interface
        for (int d = 1; d <= numDepartments; d++) {
            String name = JOptionPane.showInputDialog("Enter the name of department " + d + ":");
            int numCourses = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of courses in department " + d + ":"));

            ArrayList<Cours> departmentCourses = new ArrayList<>();

            // Get the details for each course in the department
            for (int c = 1; c <= numCourses; c++) {
                String number = JOptionPane.showInputDialog("Enter the number of course " + c + " in department " + d + ":");
                String courseName = JOptionPane.showInputDialog("Enter the name of course " + c + " in department " + d + ":");
                int maxNumberOfStudents = Integer.parseInt(JOptionPane.showInputDialog("Enter the maximum number of students for course " + c + " in department " + d + ":"));

                ArrayList<Instructor> courseInstructors = new ArrayList<>();
                String instructorIDs = JOptionPane.showInputDialog("Enter the IDs of instructors for course " + c + " in department " + d + ":");
                String[] instructorIDArray = instructorIDs.split(",");

                // Find the instructors based on the entered IDs
                for (String instructorID : instructorIDArray) {
                    Instructor instructor = findInstructor(instructorID.trim());
                    if (instructor != null) {
                        courseInstructors.add(instructor);
                    } else {
                        JOptionPane.showMessageDialog(null, "Instructor with ID " + instructorID.trim() + " not found. Please try again.");
                        c--;
                        continue;
                    }
                }

                Cours course = new Cours(number, courseName, courseInstructors, maxNumberOfStudents);
                courses.add(course);
                departmentCourses.add(course);
            }

            Department department = new Department(name, departmentCourses);
            depts.add(department);
        }

        // Calculate the total number of classes
        depts.forEach(x -> numberOfClasses += x.getCourses().size());
    }

    private Instructor findInstructor(String instructorID) {
        for (Instructor instructor : instructors) {
            if (instructor.getId().equals(instructorID)) {
                return instructor;
            }
        }
        return null;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public ArrayList<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public ArrayList<Cours> getCourses() {
        return courses;
    }

    public ArrayList<Department> getDepts() {
        return depts;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public static void main(String[] args) {
        Data data = new Data();
        JTextField roomsNumber = new JTextField();
        JTextField meetingTimesnumber = new JTextField();
        JTextField instructorsNumber = new JTextField();
        JTextField departmentNumber = new JTextField();

        Object[] message = {
                "Number of rooms:", roomsNumber,
                "Number of meeting times:", meetingTimesnumber,
                "Number of instructors:", instructorsNumber,
                "Number of departments:", departmentNumber
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Objects number", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            data.initialize(roomsNumber, meetingTimesnumber, instructorsNumber, departmentNumber);
            JOptionPane.showMessageDialog(null, "Data initialization complete!");
        }
    }
}
