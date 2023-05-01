package com.example.scheduler;

import java.util.ArrayList;

public class Role {
    private String name;
    private int id;
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    public Role(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public ArrayList<Employee> getEmployees(){
        return this.employees;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee){
        this.employees.remove(employee);
    }

    public void removeEmployee(int index){
        this.employees.remove(index);
    }


}
