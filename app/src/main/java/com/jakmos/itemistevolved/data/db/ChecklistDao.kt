package com.jakmos.itemistevolved.data.db

import androidx.room.*
import com.jakmos.itemistevolved.data.entity.ChecklistEntity

@Dao
interface ChecklistDao {

    @Query("SELECT * FROM checklist ORDER BY updatedAt DESC")
    suspend fun getAll(): List<ChecklistEntity>

    @Query("SELECT * FROM checklist WHERE name LIKE :name")
    suspend fun findByName(name: String): List<ChecklistEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(checklist: ChecklistEntity): Long

    @Query("DELETE FROM checklist WHERE id= :id")
    suspend fun deleteById(id: Long): Int

    @Update
    suspend fun updateChecklist(checklists: ChecklistEntity): Int
}
