package com.example.scheduler;

import java.util.ArrayList;

public class Employee {
    private String name;
    private Integer id;
    private ArrayList<Role> roles = new ArrayList<Role>();

    public Employee(String name, int id, Role role){
        this.name = name;
        this.id = id;
        this.roles.add(role);
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

    public ArrayList<Role> getRoles(){
        return this.roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
    }

    public void removeRole(int index){
        this.roles.remove(index);
    }

    public void removeRole(String name){
        for (Role role : this.roles){
            if (role.getName().equals(name)){
                this.roles.remove(role);
            }
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

}
