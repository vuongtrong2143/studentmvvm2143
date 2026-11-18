package com.example.studentmvvm2143

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.studentmvvm2143.R
import com.example.studentmvvm2143.data.AppDatabase
import com.example.studentmvvm2143.repository.StudentRepository
import com.example.studentmvvm2143.ui.StudentListFragment
import com.example.studentmvvm2143.viewmodel.StudentViewModel
import com.example.studentmvvm2143.viewmodel.StudentViewModelFactory

class MainActivity : AppCompatActivity() {
    // Khởi tạo Database Singleton và Repository ở tầng Host[cite: 10]
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { StudentRepository(database.studentDao()) }
    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.toString()
        // MainActivity chỉ chứa FragmentContainerView nạp StudentListFragment[cite: 11]
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, StudentListFragment())
                .commit()
        }
    }
}