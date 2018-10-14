package com.wilson.models.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.wilson.models.MetOfficeData
import io.reactivex.Maybe




/**
 * Created by Wilson on 15-02-2018.
 */
@Dao interface MetOfficeDataDao
{
//    @Insert
//    fun insert(user: List<MetOfficeData>): Single<List<Long>>

    @Insert(onConflict = REPLACE)
    fun insert(task : MetOfficeData) : Long

    @Delete
    fun delete(task : MetOfficeData)

    @Delete
    fun delete(taskList: ArrayList<MetOfficeData>)

    @Query("Select * from tbl_matrix")
    fun getAllTask() : List<MetOfficeData>

    @Query("Select * from tbl_matrix")
    fun getMetOfficeData(): Maybe<List<MetOfficeData>>

    @Query("select * from tbl_matrix where id = :id")
    fun findTaskById(id: Int): MetOfficeData

    @Update(onConflict = REPLACE)
    fun update(task : MetOfficeData)

    @Update(onConflict = REPLACE)
    fun update(taskList : ArrayList<MetOfficeData>)


    @Insert
    fun insertAll(taskList : ArrayList<MetOfficeData>)

    @Query("DELETE FROM tbl_matrix")
    fun deleteAll()


}