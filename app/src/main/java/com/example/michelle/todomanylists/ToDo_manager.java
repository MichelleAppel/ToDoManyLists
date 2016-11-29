package com.example.michelle.todomanylists;

/**
 * Created by Michelle on 29-11-2016.
 * The manager singleton class
 */

public class ToDo_manager {

    private static ToDo_manager instance = new ToDo_manager();
    private static String string;

    public static ToDo_manager getInstance() {
        return instance;
    }

    // Constructor
    private ToDo_manager() {
        string = "initial string";
    }

    // Setter
    public void setToDo_manager(String newString) {
        string = newString;
    }

    // Getter
    public String getToDo_manager() {
        return string;
    }
}
