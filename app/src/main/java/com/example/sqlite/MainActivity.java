package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView noteListView;
    Button newNotebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        noteListView = findViewById(R.id.noteListView);
        newNotebtn = findViewById(R.id.addNewNoteView);
        loadFromDBtoMemory();
//        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(),Note.noteArrayList);
//        noteListView.setAdapter(noteAdapter);
        setNoteAdapter();
        setOnClickListner();

        //new note onclickListner
        newNotebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NoteDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    // notelistview Click Listner -> edit
    private void setOnClickListner() {
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Note selectedNote = (Note) noteListView.getItemAtPosition(position);
                Intent editNote = new Intent(getApplicationContext(),NoteDetailActivity.class);
                editNote.putExtra(Note.NOTE_EDIT_EXTRA,selectedNote.getId());
                startActivity(editNote);

            }
        });
    }

    private void loadFromDBtoMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNoteAdapter();

    }
    private  void setNoteAdapter(){
        NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(),Note.nonDeletedNoted());
        noteListView.setAdapter(noteAdapter);
    }
}