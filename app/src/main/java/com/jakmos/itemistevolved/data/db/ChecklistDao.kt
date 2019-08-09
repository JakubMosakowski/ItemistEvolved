package com.jakmos.itemistevolved.data.db

import androidx.room.*
import com.jakmos.itemistevolved.domain.model.Checklist

@Dao
interface ChecklistDao {
    @Query("SELECT * FROM Checklist")
    suspend fun getAll(): List<Checklist>

    @Query("SELECT * FROM Checklist WHERE name LIKE :name")
    suspend fun findByName(name: String): List<Checklist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg checklist: Checklist)

    @Delete
    suspend fun delete(checklist: Checklist)

    @Update
    suspend fun updateChecklist(vararg checklists: Checklist)
}