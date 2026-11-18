package com.example.studentmvvm2143.repository

import com.example.studentmvvm2143.data.StudentDao
import com.example.studentmvvm2143.model.Student
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {
    // Nguồn chân lý duy nhất (Single Source of Truth)[cite: 6]
    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

    suspend fun insert(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun delete(student: Student) {
        studentDao.deleteStudent(student)
    }
}