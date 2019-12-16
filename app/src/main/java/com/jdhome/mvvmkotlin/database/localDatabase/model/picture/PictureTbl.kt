package com.jdhome.mvvmkotlin.database.localDatabase.model.picture

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PictureTable")
data class PictureTbl(
    @PrimaryKey/*(autoGenerate = true) val id: Int = 0,*/
    @ColumnInfo(name = "pic_id") val picID: Int,
    @ColumnInfo(name = "image_url") val image_url: String?,
    @ColumnInfo(name = "type") val imageType: String?,
    @ColumnInfo(name = "image_size") val imageSize: Int?,
    @ColumnInfo(name = "views") val imageViews: Int?,
    @ColumnInfo(name = "downloads") val imageDownloads: Int?,
    @ColumnInfo(name = "favorites") val imageFavorites: Int?,
    @ColumnInfo(name = "likes") val imageLikes: Int?,
    @ColumnInfo(name = "image_owner") val imageOwner: String?,
    @ColumnInfo(name = "image_owner_img") val imageOwnerImg: String?
)