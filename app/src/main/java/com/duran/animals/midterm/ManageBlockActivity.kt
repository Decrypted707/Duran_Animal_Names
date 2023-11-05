package com.duran.animals.midterm

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.duran.animals.midterm.adapters.AnimalAdapter
import com.duran.animals.midterm.databinding.ActivityManageBlockBinding
import com.duran.animals.midterm.model.animals

class ManageBlockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManageBlockBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageBlockBinding.inflate(layoutInflater) // Replace YourBindingClassName with the actual name of your generated binding class
        setContentView(binding.root)

        // Retrieve the set of blocked animals from SharedPreferences
        sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val blockedAnimals: MutableSet<String> = sharedPreferences.getStringSet("blocked_animals", mutableSetOf()) ?: mutableSetOf()

        // Create a list of animals objects from the set of blocked animals
        val animalsList = blockedAnimals.map { animals(it, it, true) }.filter { it.blockstatus }

        // Create an instance of the AnimalAdapter class with the list of animals objects
        val adapter = AnimalAdapter(animalsList, {}) {}

        // Set the adapter for the RecyclerView
        binding.blockedAnimalsRecyclerView.adapter = adapter

        binding.backButtonManage.setOnClickListener {
            // Handle back button click event here
            finish() // Finish the current activity and navigate back to the previous activity (main page)
        }
    }
}