package com.tutorail.ga.cs.domain;

public class Class {
private int id;
private Department dept;
private Cours cours;
private Instructor instructor;
private MeetingTime meetingTime;
private Room room;
public Class(int id, Department dept, Cours cours) {
	this.id = id;
	this.dept = dept;
	this.cours = cours;
}
public void setInstructor(Instructor instructor) {this.instructor = instructor;}
public void setMeetingTime(MeetingTime meetingTime) {this.meetingTime = meetingTime;}
public void setRoom(Room room) {this.room = room;}
public int getId() {return id;}
public Department getDept() {return dept;}
public Cours getCours() {return cours;}
public Instructor getInstructor() {return instructor;}
public MeetingTime getMeetingTime() {return meetingTime;}
public Room getRoom() {return room;}
public String toString() {
	return "["+dept.getName()+","+cours.getNumber()+","+room.getNumber()+","+instructor.getId()+","+meetingTime.getId()+"]";
}

}





