package com.sindhuwidayat.mynoteapps

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface NoteDao {

    @Query("SELECT * from note")
    fun getAllNote(): List<NoteModel>

    @Insert(onConflict = REPLACE)
    fun insertNote( noteModel: NoteModel)

    @Delete
    fun deletNote( noteModel: NoteModel)

    @Query("UPDATE note SET title=:noteTitle, message =:noteMessage WHERE id =:noteId")
    fun updateNote(noteTitle:String, noteMessage:String, noteId:Long)
}