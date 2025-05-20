package com.example.eduverify

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eduverify.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the existing profile data if available (optional)
        val currentName = intent.getStringExtra("name") ?: ""
        val currentAttendance = intent.getStringExtra("attendance") ?: ""
        val currentSubjects = intent.getStringExtra("subjects") ?: ""
        val currentSections = intent.getStringExtra("sections") ?: ""
        val currentGrades = intent.getStringExtra("grades") ?: ""
        val currentMessages = intent.getStringExtra("messages") ?: ""
        val currentEvents = intent.getStringExtra("events") ?: ""

        // Set the current values into the EditTexts
        binding.editName.setText(currentName)
        binding.editAttendance.setText(currentAttendance)
        binding.editSubjects.setText(currentSubjects)
        binding.editSections.setText(currentSections)
        binding.editGrades.setText(currentGrades)
        binding.editMessages.setText(currentMessages)
        binding.editEvents.setText(currentEvents)

        // Save button click listener to send the updated data to ProfileActivity
        binding.saveButton.setOnClickListener {
            // Get the input values from the EditTexts
            val name = binding.editName.text.toString()
            val attendance = binding.editAttendance.text.toString()
            val subjects = binding.editSubjects.text.toString()
            val sections = binding.editSections.text.toString()
            val grades = binding.editGrades.text.toString()
            val messages = binding.editMessages.text.toString()
            val events = binding.editEvents.text.toString()  // Get the Events input

            // Send the updated data to ProfileActivity
            val intent = Intent(this, ProfileActivity::class.java).apply {
                putExtra("name", name)
                putExtra("attendance", attendance)
                putExtra("subjects", subjects)
                putExtra("sections", sections)
                putExtra("grades", grades)
                putExtra("messages", messages)
                putExtra("events", events)  // Passing updated Events data
            }
            startActivity(intent)
        }

        // Reset button click listener to clear all fields
        binding.resetButton.setOnClickListener {
            binding.editName.text?.clear()
            binding.editAttendance.text?.clear()
            binding.editSubjects.text?.clear()
            binding.editSections.text?.clear()
            binding.editGrades.text?.clear()
            binding.editMessages.text?.clear()
            binding.editEvents.text?.clear()
        }
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
                    navigateTo(ProfileActivity::class.java)
                    true
                }
                R.id.nav_edit_profile -> {

                    Toast.makeText(this, "Already on EditProfile", Toast.LENGTH_SHORT).show()
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
