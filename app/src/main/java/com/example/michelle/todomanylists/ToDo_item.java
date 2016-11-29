package com.example.michelle.todomanylists;

/**
 * Created by Michelle on 29-11-2016.
 */

class ToDo_item {
    public String todo_string;
    public int id;
    public boolean is_checked;

    // Constructor with text string
    public ToDo_item(String todo_string) {
        this.todo_string = todo_string;
    }

    // Constructor with id int, text string and checked boolean
    public ToDo_item(int id, String todo_string, boolean is_checked) {
        this.todo_string = todo_string;
        this.id = id;
        this.is_checked = is_checked;
    }

    // Switch the checked sign and return item
    public ToDo_item switchChecked() {
        is_checked = !is_checked;
        return this;
    }

    // To String
    public String toString() {
        return todo_string;
    }
}
