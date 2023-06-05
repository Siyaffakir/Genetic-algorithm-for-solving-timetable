package com.za.tutorial.ga.cs;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tutorail.ga.cs.domain.Class;

public class Driver {
    public static final int POPULATION_SIZE = 9;
    public static double MUTATION_RATE = 0.1;
    public static double CROSSOVER_RATE = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMB_OF_ELITE_SCHEDULES =1;
    private int scheduleNumb = 0;
    private int classNumb = 1;
    private Data data;
    private JLabel generationLabel;
    private DefaultTableModel tableModel;
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Driver driver = new Driver();
        driver.data = new Data();
        int generationNumber = 0;
        driver.printAvailableData();
        System.out.println("> Generation # "+generationNumber);
        System.out.print(" Schedule # |                                            ");
        System.out.print("Classes [dept,class,room,instructor,meeting-time]");
        System.out.println("                                   | Fitness | Conflicts");
        System.out.print("------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
        Population population = new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
        driver.setupTable();
        driver.updateTable(population.getSchedules().get(0));
        driver.updateGenerationLabel(generationNumber);
        driver.classNumb = 1;
        
        while (population.getSchedules().get(0).getFitness() !=1.0 && generationNumber<1000 ) {
            System.out.println("> Generation # "+ ++generationNumber);
            System.out.print(" Schedule # |                                            ");
            System.out.print("Classes [dept,class,room,instructor,meeting-time]");
            System.out.println("                                   | Fitness | Conflicts");
            System.out.print("------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------");
            population = geneticAlgorithm.evolve(population).sortByFitness();
            driver.scheduleNumb = 0;
            driver.updateTable(population.getSchedules().get(0));
            driver.updateGenerationLabel(generationNumber);
            driver.classNumb = 1 ;
        }
        
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        long executionTimeInSeconds = executionTime / 1000;
        System.out.println("Execution time: " + executionTimeInSeconds + " seconds");
    }
    
    private void setupTable() {
        JFrame frame = new JFrame("Class Schedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"Class #", "Department", "Course", "Room", "Instructor", "Meeting Time"};
        Object[][] data = new Object[0][6];

        tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        generationLabel = new JLabel();
        frame.add(generationLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    
    private void updateTable(Schedule schedule) {
        ArrayList<Class> classes = schedule.getClasses();
        tableModel.setRowCount(classes.size());
        
        for (int i = 0; i < classes.size(); i++) {
            Class cls = classes.get(i);
            String deptName = cls.getDept().getName();
            String courseName = cls.getCours().getName() + " (" + cls.getCours().getNumber() + ", " + cls.getCours().getMaxNumbOfStudents() + ")";
            String roomInfo = cls.getRoom().getNumber() + " (" + cls.getRoom().getSeatingCapacity() + ")";
            String instructorInfo = cls.getInstructor().getName() + " (" + cls.getInstructor().getId() + ")";
            String meetingTimeInfo = cls.getMeetingTime().getTime() + " (" + cls.getMeetingTime().getId() + ")";
            tableModel.setValueAt("Class #" + classNumb, i, 0);
            tableModel.setValueAt(deptName, i, 1);
            tableModel.setValueAt(courseName, i, 2);
            tableModel.setValueAt(roomInfo, i, 3);
            tableModel.setValueAt(instructorInfo, i, 4);
            tableModel.setValueAt(meetingTimeInfo, i, 5);
            classNumb++;
        }
    }
    
    private void updateGenerationLabel(int generationNumber) {
        generationLabel.setText("Generation: " + generationNumber);
    }

    private void printAvailableData() {
        System.out.println("Available Departments ==>");
        data.getDepts().forEach(x ->System.out.println("name: "+x.getName()+",courses: "+x.getCourses()));
        System.out.println("\nAvailable Courses ==>");
        data.getCourses().forEach(x -> System.out.println("course #: "+x.getNumber()+", name: "+x.getName()+", max # of students: "+x.getMaxNumbOfStudents()+", instructors: "+x.getInstructors()));
        System.out.println("\nAvailable Rooms ==>");
        data.getRooms().forEach(x ->System.out.println("id: "+x.getNumber()+", seatingCapacity:"+x.getSeatingCapacity()));
        System.out.println("\nAvailable Instructors ==>");
        data.getInstructors().forEach(x ->System.out.println("Id: "+x.getId()+", name: "+x.getName()));
        System.out.println("\nAvailable Meeting Times ==>");
        data.getMeetingTimes().forEach(x ->System.out.println("Id: "+x.getId()+", MeetingTime: "+x.getTime()));
        System.out.print("-----------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------");
    }
}
