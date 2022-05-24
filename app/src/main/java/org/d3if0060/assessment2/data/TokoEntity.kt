package org.d3if0060.assessment2.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "forassessment_table")
data class TokoEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var namaToko: String,
    var alamatToko: String,
    var deskripsiToko: String
): Parcelable