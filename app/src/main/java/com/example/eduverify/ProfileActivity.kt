package com.example.eduverify

import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eduverify.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data passed from the EditProfileActivity or Firebase
        val name = intent.getStringExtra("name") ?: "User"
        val attendance = intent.getStringExtra("attendance") ?: "N/A"
        val subjects = intent.getStringExtra("subjects") ?: "N/A"
        val sections = intent.getStringExtra("sections") ?: "N/A"
        val grades = intent.getStringExtra("grades") ?: "N/A"
        val messages = intent.getStringExtra("messages") ?: "N/A"
        val events = intent.getStringExtra("events") ?: "N/A"  // New data for events

        // Set the user's name in the profile view
        binding.profileName.text = name

        // Set the values to the corresponding cards
        binding.cardAttendance.findViewById<TextView>(R.id.attendanceText).text = "Attendance: $attendance"
        binding.cardSubjects.findViewById<TextView>(R.id.subjectsText).text = "Subjects: $subjects"
        binding.cardSections.findViewById<TextView>(R.id.sectionsText).text = "Sections: $sections"
        binding.cardGrades.findViewById<TextView>(R.id.gradesText).text = "Grades: $grades"
        binding.cardMessages.findViewById<TextView>(R.id.messagesText).text = "Messages: $messages"
        binding.cardEvents.findViewById<TextView>(R.id.eventsText).text = "Events: $events"  // Set events data

        setupListeners()
    }

    private fun setupListeners() {

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {

                    navigateTo(MainActivity::class.java)
                    true
                }
                R.id.nav_docs -> {
                    navigateTo(UploadActivity::class.java)
                    true
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Already on Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_edit_profile -> {
                    navigateTo(EditProfileActivity::class.java)
                    true
                }
                else -> false
            }
        }
    }
    private fun navigateTo(destination: Class<*>, finishCurrent: Boolean = false) {
        val intent = Intent(this, destination)
        startActivity(intent)
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        if (finishCurrent) finish()
    }
}
