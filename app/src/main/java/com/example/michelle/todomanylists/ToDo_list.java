package com.example.michelle.todomanylists;

import java.util.ArrayList;

/**
 * Created by Michelle on 29-11-2016.
 * ToDo_list class
 */

import java.util.ArrayList;

public class ToDo_list {

    private String category;
    private ArrayList<ToDo_item> toDo_items = new ArrayList<>();

    // Constructor
    public ToDo_list(String category) {
        this.category = category;
    }

    // Constructor
    public ToDo_list(String category, ArrayList<ToDo_item> toDo_items) {
        this.category = category;
        this.toDo_items = toDo_items;
    }

    // Returns title
    public String toString() {
        return category;
    }

    // Returns ToDo_items
    public ArrayList<ToDo_item> getToDoItems() {
        return toDo_items;
    }

}