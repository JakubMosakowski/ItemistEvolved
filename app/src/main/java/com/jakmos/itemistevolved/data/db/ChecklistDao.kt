package com.jakmos.itemistevolved.data.db

import androidx.room.*
import com.jakmos.itemistevolved.domain.model.entity.ChecklistEntity

@Dao
interface ChecklistDao {

    @Query("SELECT * FROM checklist ORDER BY updatedAt DESC")
    suspend fun getAll(): List<ChecklistEntity>

    @Query("SELECT * FROM checklist WHERE name LIKE :name")
    suspend fun findByName(name: String): List<ChecklistEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg checklist: ChecklistEntity)

    @Delete
    suspend fun delete(checklist: ChecklistEntity)

    @Update
    suspend fun updateChecklist(vararg checklists: ChecklistEntity)
}