package com.example.scheduler;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Employee {
    private LocalTime availability;
    private LocalTime preferences;
    private int priority;
    private String firstName;
    private String lastName;
    private int id;
    private LocalTime birthDate;
    private ArrayList<Role> roles;
    private int maximumDesiredHours;
    private int maximumHours;

    //Employee Constructor
    public Employee(final String firstName, final String lastName, final int ID, final LocalTime birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        capitalizeName();
        this.id = ID;
        this.birthDate = birthDate;
        System.out.println(String.format("Employee %s %s created", this.firstName, this.lastName));
    }

    //Employee Constructor with Roles
    public Employee(final String firstName, final String lastName, final int ID, final LocalTime birthDate,
                    ArrayList<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        capitalizeName();
        this.id = ID;
        this.birthDate = birthDate;
        this.roles = roles;
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
}
