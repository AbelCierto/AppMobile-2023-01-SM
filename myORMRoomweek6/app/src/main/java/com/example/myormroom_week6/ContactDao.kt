package com.example.myormroom_week6

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact")
    fun getAll(): List<Contact>
    @Insert
    fun insertContact(vararg contact: Contact)
    @Delete
    fun deleteContact(vararg contact: Contact)
    @Update
    fun updateContact(vararg contact: Contact)
}