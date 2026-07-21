package com.example.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.database.dao.ActiveIdDao
import com.example.data.database.dao.ControlGRVDao
import com.example.data.database.dao.UserDao
import com.example.data.database.entities.ActiveIdLocal
import com.example.data.database.entities.ControlGRVLocal
import com.example.data.database.entities.UserLocal
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.jvm.java

@Database(
    entities = [
        UserLocal::class,
        ControlGRVLocal::class,
        ActiveIdLocal::class],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun activeIdDao(): ActiveIdDao
    abstract fun controlGRVDao(): ControlGRVDao

    companion object{
        @Volatile
        private var INSTANCE: MyDatabase? = null

        private const val DB_NAME = "generalData.db"

        @OptIn(DelicateCoroutinesApi::class)
        fun getDatabase(context: Context): MyDatabase {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, DB_NAME)
                            .allowMainThreadQueries()
                            .addCallback(object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    Log.d("MoviesDatabase", "populating with data...")
                                    GlobalScope.launch(Dispatchers.IO) { rePopulateDb(INSTANCE) }
                                }
                            }).build()
                    }
                }
            }

            return INSTANCE!!
        }
    }
}
