package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class NoteDetailActivity extends AppCompatActivity {
    EditText titleET;
    EditText descET;
    Button saveButton, deleteButton;
    private Note selectedNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        getSupportActionBar().hide();
        titleET = findViewById(R.id.titleView);
        descET = findViewById(R.id.descriptionView);
        saveButton = findViewById(R.id.btnSave);
        deleteButton = findViewById(R.id.btnDelete);
        checkForEditNote();


        //savebutton listner
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(getApplicationContext());
                String title = titleET.getText().toString();
                String description = descET.getText().toString();


                if (selectedNote == null) {
                    int id = Note.noteArrayList.size();
                    Note newNote = new Note(id, title, description);
                    Note.noteArrayList.add(newNote);
                    sqLiteManager.addNoteToDatabase(newNote);
                } else {
                    selectedNote.setTitle(title);
                    selectedNote.setDescription(description);
                    sqLiteManager.updateNote(selectedNote);
                }

                finish();
            }
        });
        //delete button listner
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedNote.setDeleted(new Date());
                SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(getApplicationContext());
                sqLiteManager.updateNote(selectedNote);
                finish();
            }
        });

    }

    private void checkForEditNote() {
        Intent previousIntent = getIntent();

        int passedID = previousIntent.getIntExtra(Note.NOTE_EDIT_EXTRA, -1);
        selectedNote = Note.getNoteforID(passedID);
        if (selectedNote != null) {
            titleET.setText(selectedNote.getTitle());
            descET.setText(selectedNote.getDescription());
        } else {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }
}