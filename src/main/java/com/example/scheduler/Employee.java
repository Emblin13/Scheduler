package com.example.scheduler;

public class Employee {
    private String name;
    private int id;
    private Role role;

    public Employee(String name, int id, Role role){
        this.name = name;
        this.id = id;
        this.role = role;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public Role getRole(){
        return this.role;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setRole(Role role){
        this.role = role;
    }


}
