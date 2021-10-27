package com.example.week6_sec4_notesapp_room.models

import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM Massege /* ORDER BY Note DESC*/")
    fun getAllNotes():List<Notes>

    @Insert
    fun insertNote(note1:Notes)

    @Delete
    fun deleteNote(id: Notes)

    @Update
    fun updateNote(id: Notes)

}

