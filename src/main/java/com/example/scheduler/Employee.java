package com.example.scheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Employee implements Comparable<Employee> {
    //private LocalTime availability; availibility needs to be a list, for each day of the week
    private List<Availability> availability;
    //private LocalTime preferences; no longer need preferences
    //private int priority; no longer need priority, based on hireDate
    private String firstName;
    private String lastName;
    private int id;
    //private LocalDate birthDate; birthdate no longer matters, just need hiredate
    private LocalDate hireDate;
    private ArrayList<Role> roles; //this represents position, need to edit
    //private int maximumDesiredHours; don't need this, just one maximum hours variable is fine
    private int maximumHours;
    private int remainingHours;

    //Employee Constructor
    public Employee(final String firstName, final String lastName, int id, final int maxHours, final LocalDate hireDate,
                    final List<Availability> availability) {
        this.firstName = firstName;
        this.lastName = lastName;
        capitalizeName();
        this.id = id;
        this.maximumHours = maxHours;
        this.hireDate = hireDate;
        this.availability = availability;
        remainingHours = maxHours;

        System.out.println(String.format("Employee %s %s created", this.firstName, this.lastName));
    }

    public int getRemainingHours() {
        return remainingHours;
    }

    public void setRemainingHours(int hours) {
        remainingHours = hours;
    }

    //Employee Constructor with Roles
    public Employee(final String firstName, final String lastName, final int maxHours, final LocalDate hireDate,
                    final List<Availability> availability, ArrayList<Role> roles, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        capitalizeName();
        this.maximumHours = maxHours;
        this.hireDate = hireDate;
        this.availability = availability;
        this.roles = roles;
        this.id = id;
        remainingHours = maxHours;
        System.out.println(String.format("Employee %s %s created", this.firstName, this.lastName));
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        capitalizeName();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        capitalizeName();
    }

    public String getLastName(){
        return this.lastName;
    }
    public void setId(String lastName) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
    public ArrayList<Role> getRoles(){ return this.roles; }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void setRoles(ArrayList<Role> roles){
        this.roles = roles;
    }

    public void removeRole(Role role){
        this.roles.remove(role);
    }

    public void removeRole(int index){
        this.roles.remove(index);
    }

    private void capitalizeName(){
        this.firstName = this.firstName.substring(0,1).toUpperCase() + this.firstName.substring(1).toLowerCase();
        this.lastName = this.lastName.substring(0,1).toUpperCase() + this.lastName.substring(1).toLowerCase();
    }

    public List<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Availability> availability) {
        this.availability = availability;
    }

    public int getMaximumHours() {
        return maximumHours;
    }

    public void setMaximumHours(int maximumHours) {
        //System.out.println(String.format("Maximum hours for %s %s set to %d", this.firstName, this.lastName, maximumHours));
        this.maximumHours = maximumHours;
    }
    public LocalDate getHireDate() {
        return hireDate;
    }
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public boolean isAvailable(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        // Check if employee is available on the given day and within the shift time range
        for (Availability a : availability) {
            if (a.getDay() == day && (a.getStartTime().isBefore(startTime) || a.getStartTime().equals(startTime)) && (a.getEndTime().isAfter(endTime) || a.getEndTime().equals(endTime))) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int compareTo(Employee other) {
        // Custom comparison based on hire date (seniority)
        return this.hireDate.compareTo(other.hireDate);
    }
}
