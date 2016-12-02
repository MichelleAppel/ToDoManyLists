package com.example.michelle.todomanylists;

import java.util.ArrayList;

/**
 * Created by Michelle on 29-11-2016.
 * The manager singleton class
 */

public class ToDo_manager {

    private static ToDo_manager instance = new ToDo_manager();

    private ArrayList<ToDo_list> todo_lists = new ArrayList<>();

    public static ToDo_manager getInstance() {
        return instance;
    }

    // Constructor
    private ToDo_manager() {
    }

    // Setter
    public void setToDo_manager(String newString) {

    }

    // Getter
    // Returns ToDoList ArrayList
    public ArrayList<ToDo_list> getToDoLists() {
        return todo_lists;
    }

    // Returns all ToDoList titles
    public ArrayList<String> getTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < todo_lists.size(); i++) {
            titles.add(todo_lists.get(i).toString());
        }
        return titles;
    }
}
