package com.example.studentmvvm2143.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentmvvm2143.model.Student

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        // @Volatile đảm bảo giá trị của INSTANCE luôn được cập nhật ngay lập tức giữa các luồng[cite: 6]
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ute_student_database.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}