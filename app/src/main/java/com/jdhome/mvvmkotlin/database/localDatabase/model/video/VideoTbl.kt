package com.jdhome.mvvmkotlin.database.localDatabase.model.video

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "VideoTable")
data class VideoTbl(
    @PrimaryKey/*(autoGenerate = true) val id: Int = 0,*/
    @ColumnInfo(name = "video_id") val videoID: Int,
    @ColumnInfo(name = "videoUrl_small") val videoUrl_small: String?,
    @ColumnInfo(name = "videoUrl_medium") val videoUrl_medium: String?,
    @ColumnInfo(name = "videoUrl_large") val videoUrl_large: String?,
    @ColumnInfo(name = "type") val videoType: String?,
    @ColumnInfo(name = "duration") val videoDuration: Int?,
    @ColumnInfo(name = "views") val videoViews: Int?,
    @ColumnInfo(name = "downloads") val videoDownloads: Int?,
    @ColumnInfo(name = "favorites") val videoFavorites: Int?,
    @ColumnInfo(name = "likes") val videoLikes: Int?,
    @ColumnInfo(name = "video_owner") val videoOwner: String?,
    @ColumnInfo(name = "video_owner_img") val videoOwnerImg: String?
)