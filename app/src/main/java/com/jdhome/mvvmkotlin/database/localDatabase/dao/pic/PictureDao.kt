package com.jdhome.mvvmkotlin.database.localDatabase.dao.pic

import androidx.room.*
import com.jdhome.mvvmkotlin.database.localDatabase.model.picture.PictureTbl
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePic(appSettings: PictureTbl): Completable

    @Query("SELECT * FROM PictureTable WHERE type=:Type")
    fun getPic(Type:String): Single<MutableList<PictureTbl>>

    @Update
    fun update(appSettings: PictureTbl): Completable


}
