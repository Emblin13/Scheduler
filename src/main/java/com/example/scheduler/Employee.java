package com.example.scheduler;

public class Employee {
    private String name;
    private Integer id;
    private Role role;

    public Employee(String name, int id, Role role){
        this.name = name;
        this.id = id;
        this.role = role;
        System.out.println(String.format("Employee %s created", this.name));
    }

    public Employee(String name, Integer id){
        this.name = name;
        this.id = id;
        System.out.println(String.format("Employee %s created", this.name));
    }

    public String getName(){
        return this.name;
    }

    public String getFirstName(){
        return this.name.split(" ")[0];
    }

    public String getLastName(){
        return this.name.split(" ")[1];
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
