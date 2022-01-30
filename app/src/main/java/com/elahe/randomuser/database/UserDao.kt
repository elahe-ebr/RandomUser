package com.elahe.randomuser.database

import androidx.room.*
import com.elahe.randomuser.data.Result

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Result)

    @Query("SELECT * FROM tbl_user")
    fun get(): MutableList<Result>

    @Delete
    fun delete(users: MutableList<Result>)
}