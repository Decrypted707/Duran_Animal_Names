package com.duran.animals.midterm


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.duran.animals.midterm.databinding.ActivityAnimalDetailsBinding
import com.duran.animals.midterm.model.animals


class AnimalDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimalDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION") val animal = intent.getSerializableExtra("animal") as animals

        binding.name.text = animal.name
        binding.description.text = animal.description

        val animalName=binding.name.text

        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val blockedAnimalsSet = sharedPreferences.getStringSet("blockedAnimals", HashSet()) ?: HashSet()

        binding.blockButton.setOnClickListener {
            // Update the block status of the animal to true
//            animal.blockstatus = true

            // Save the updated block status in SharedPreferences
//            val editor = sharedPreferences.edit()
//                editor.putBoolean(animal.name.toString(), true)
//            editor.apply()

            // Show a toast message indicating the animal has been blocked
            Toast.makeText(this, "${animal.name} blocked", Toast.LENGTH_SHORT).show()

            // Pass the blocked animal's name to ManageBlockActivity
            val intent = Intent(this, ManageBlockActivity::class.java)
            intent.putExtra("blocked_animal_name", animal.name)
            startActivity(intent)

            finish()
        }

        binding.backButtonDetails.setOnClickListener {
            // Handle back button click event here
            finish() // Finish the current activity and navigate back to the previous activity (main page)
        }
    }


    private fun blockAnimal(animal: animals) {
        // Retrieve the set of blocked animals from SharedPreferences
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("BlockedAnimals", Context.MODE_PRIVATE)
        val blockedAnimals: MutableSet<String> =
            sharedPreferences.getStringSet("blocked_animals", mutableSetOf()) ?: mutableSetOf()

        // Add the animal name to the set of blocked animals
        blockedAnimals.add(animal.name.toString())

        // Save the updated set of blocked animals in SharedPreferences
        sharedPreferences.edit().putStringSet("blocked_animals", blockedAnimals).apply()
    }

}
