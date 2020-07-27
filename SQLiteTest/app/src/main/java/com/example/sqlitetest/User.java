package com.example.sqlitetest;

public class User {

    private String id;
    private String name;
    private String age;
    private String height;
    private String weight;

    public User(String id, String name, String age, String height, String weight){
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString(){
        return id + " " + name + " " + age + " " + height + " " + weight;
    }
}
