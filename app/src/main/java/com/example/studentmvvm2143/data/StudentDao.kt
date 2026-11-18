package com.example.studentmvvm2143.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.studentmvvm.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    // 1. Thao tác ghi: Bắt buộc dùng suspend fun để chạy trên background thread[cite: 5]
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    // 2. Thao tác đọc: Trả về Flow để tự động phát luồng dữ liệu khi bảng thay đổi[cite: 5]
    @Query("SELECT * FROM students_table ORDER BY id DESC")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM students_table WHERE student_code = :code LIMIT 1")
    suspend fun getStudentByCode(code: String): Student?
}