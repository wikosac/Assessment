package org.d3if4401.assessment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HewanDao {
    @Insert
    fun insert(hewan: HewanEntity)
    @Query("SELECT * FROM hewan ORDER BY id DESC LIMIT 1")
    fun getLastBmi(): LiveData<HewanEntity?>
}