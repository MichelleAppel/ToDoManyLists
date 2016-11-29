package com.example.michelle.todomanylists;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToDoList_activity extends AppCompatActivity {
    private DBhelper dBhelper;
    private ListView listView;
    private ArrayAdapter<ToDo_item> adapter;
    private ArrayList<ToDo_item> todo_list;
    private EditText add_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        // Get database and fill in set ListView to it
        dBhelper = new DBhelper(this);

        // Checks if this is the first startup of the app and sets instructions if so
        SharedPreferences prefs = getSharedPreferences("first_start", MODE_PRIVATE);
        Boolean first_start = prefs.getBoolean("first_start", true);
        if (first_start) {
            dBhelper.create(new ToDo_item("This is your to-do list"));
            dBhelper.create(new ToDo_item("Add your entries below"));
            dBhelper.create(new ToDo_item("Long press an item to remove it"));
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("first_start", false);
            editor.apply();
        }

        // Fill the ListView with entries of the database
        set_listView();

        add_editText = (EditText)findViewById(R.id.editText);
        // Performs action when enter is pressed
        add_editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Get text from EditText
                    String todo_string = add_editText.getText().toString();
                    // If entry is not empty, add to to-do list
                    if (todo_string.length() > 0) {
                        ToDo_item item = new ToDo_item(todo_string);
                        dBhelper.create(item);

                        // Clear EditText
                        add_editText.setText("");

                        // Update ListView
                        set_listView();
                        return true;

                        // If entry is empty show toast
                    } else {
                        Toast.makeText(ToDoList_activity.this, "Don't you have anything to do?", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

    }

    // Fills in the ListView with entries from the database
    public void set_listView() {
        // Read database
        todo_list = dBhelper.read();

        // Set adapter
        adapter = new ToDo_adapter(this, todo_list);
        listView = (ListView)findViewById(R.id.Todo_listView);
        listView.setAdapter(adapter);

        // Performed when clicked: switch the check sign of an item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                ToDo_item item = adapter.getItem(pos);
                dBhelper.update(item.switchChecked());
                set_listView();
            }
        });

        // Performed when long clicked: delete an item
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                dBhelper.delete(adapter.getItem(pos));
                set_listView();
                return true;
            }
        });
    }
}
