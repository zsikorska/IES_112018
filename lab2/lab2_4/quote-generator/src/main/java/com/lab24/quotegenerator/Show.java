package com.lab24.quotegenerator;

import java.util.ArrayList;

public class Show {
    private int id;
    private String name;


    public Show() {

    }

    public Show(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}