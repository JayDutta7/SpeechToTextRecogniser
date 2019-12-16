package com.jdhome.mvvmkotlin.database.localDatabase.dao.video

import androidx.room.*
import com.jdhome.mvvmkotlin.database.localDatabase.model.video.VideoTbl
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveVideo(appSettings: VideoTbl): Completable

    @Query("SELECT * FROM VideoTable WHERE type=:Type")
    fun getVideo(Type: String): Single<MutableList<VideoTbl>>

    @Update
    fun updateVideo(appSettings: VideoTbl): Completable
}