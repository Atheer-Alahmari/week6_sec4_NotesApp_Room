package com.example.week6_sec4_notesapp_room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Massege")
data class Notes (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name="id")val id:Int=0,
    @ColumnInfo(name="Note")val note:String=""
)