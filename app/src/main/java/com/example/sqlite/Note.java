package com.example.sqlite;

import java.util.ArrayList;
import java.util.Date;

public class Note {
    public static ArrayList<Note> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA="noteEdit";

    private int id;
    private String title;
    private String description;
    private Date deleted;

    public Note(int id, String title, String description, Date deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public Note(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deleted = null;
    }

    public static Note getNoteforID(int passedID) {
        for (Note n:noteArrayList){
            if (n.getId()==passedID){
                return n;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
    public static  ArrayList<Note> nonDeletedNoted(){
        ArrayList<Note> nonDeletedNotes = new ArrayList<>();
        for (Note n:noteArrayList){
            if (n.getDeleted() == null){
                nonDeletedNotes.add(n);
            }
        }
        return nonDeletedNotes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
