package com.wilson.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "tbl_matrix")
data class MetOfficeData(
        @ColumnInfo(name = "id")@PrimaryKey(autoGenerate = true)var id : Int=0,
        var value : Float = 0.0f,
        var year : Int=0,
        var month : Int=0
)

