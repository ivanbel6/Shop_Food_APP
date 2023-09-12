package com.example.myapp_2.Data.model.database.Room.SQLITE;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")//задаем название таблицы
public class Note {

    @PrimaryKey(autoGenerate = true)//автогенерация ключа
    private int id;

    private String title;

    private String description;

    private int priority;

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
