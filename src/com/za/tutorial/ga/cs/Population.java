package com.za.tutorial.ga.cs;
import java.util.ArrayList;
import java.util.stream.IntStream;
public class Population {
    private ArrayList<Schedule> schedules;
    public Population (int size, Data data) {
    	 // Create a population of schedules with the specified size
	   schedules = new ArrayList<Schedule>(size);
	   IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(data).initialize()));
	
    }
 // Getter method to access the schedules in the population
    public ArrayList<Schedule> getSchedules(){return this.schedules;}
 // Sort the schedules in the population by fitness in descending order
    public Population sortByFitness() {
    	schedules.sort((schedule1,schedule2)->{
    		int returnValue = 0;
    		if(schedule1.getFitness() > schedule2.getFitness()) returnValue = -1;
    		else if (schedule1.getFitness() < schedule2.getFitness()) returnValue =1;
    		return returnValue;
    	});
    	return this;
    }
}
