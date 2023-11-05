package com.duran.animals.midterm


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.duran.animals.midterm.databinding.ActivityAnimalNamesBinding
import com.duran.animals.midterm.model.animals
import com.duran.animals.midterm.adapters.AnimalAdapter

class AnimalNamesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimalNamesBinding
    private lateinit var animalsList: List<animals>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalNamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE)
        val blockedAnimalIds = sharedPreferences.getStringSet("blocked_animals", emptySet()) ?: emptySet()



        animalsList = listOf(
            animals( "Aardvark", "A nocturnal mammal that feeds on ants and termites.",false),
            animals( "Bear", "A large mammal found in various habitats.",false),
            animals( "Cheetah", "The fastest land animal known for its speed and agility.",false),
            animals( "Dolphin", "A highly intelligent marine mammal.",false),
            animals( "Elephant", "The largest land animal known for its trunk and tusks.",false),
            animals( "Fox", "A small to medium-sized mammal with a bushy tail.", false),
            animals( "Giraffe", "A tall, long-necked mammal native to Africa.", false),
            animals( "Hippopotamus", "A large herbivorous mammal found near rivers and lakes in Africa.", false),
            animals( "Iguana", "A lizard native to Central and South America.", false),
            animals( "Jaguar", "A large feline species native to the Americas.", false),
            animals( "Kangaroo", "A marsupial native to Australia known for its powerful hind legs.", false),
            animals( "Lion", "A large, carnivorous mammal known as the 'King of the Jungle'.", false),
            animals( "Monkey", "A primate known for its playful and agile nature.", false),
            animals( "Nightingale", "A small, migratory songbird known for its melodious songs.", false),
            animals( "Ostrich", "A large, flightless bird native to Africa.", false),
            animals( "Penguin", "A flightless bird species found in the Southern Hemisphere.", false),
            animals( "Quokka", "A small marsupial native to Australia known for its friendly appearance.", false),
            animals( "Raccoon", "A medium-sized mammal native to North America known for its distinctive facial mask.", false),
            animals( "Sloth", "A slow-moving mammal native to Central and South America.", false),
            animals( "Tiger", "A large, striped feline species native to Asia.", false),
            animals( "Uakari", "A type of monkey found in the Amazon rainforest.", false),
            animals( "Vulture", "A scavenging bird known for feeding on carrion.", false),
            animals( "Wallaby", "A small or medium-sized kangaroo native to Australia and nearby islands.", false),
            animals( "X-ray Tetra", "A small, transparent fish native to South America.", false),
            animals( "Yak", "A long-haired mammal native to the Himalayas.", false),
            animals( "Zebra", "A large, striped mammal native to Africa.", false)
        )

        val filteredAnimalsList = animalsList.filter { animal ->
            !animal.blockstatus && animal.name !in blockedAnimalIds
        }

        val adapter = AnimalAdapter(filteredAnimalsList, { animal ->
            // Handle item click
            val intent = Intent(this, AnimalDetailsActivity::class.java)
            intent.putExtra("animal", animal)
            startActivity(intent)
        }) {}

        binding.animalsRecyclerView.adapter = adapter
        binding.animalsRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.manageblockButton.setOnClickListener {
            val blockedAnimalsList = animalsList.filter { animal ->
                animal.blockstatus
            }
            val intent = Intent(this, ManageBlockActivity::class.java)
            intent.putExtra("blocked_animals", ArrayList(blockedAnimalsList))
            startActivity(intent)
        }


    }


    private fun onItemClick(animal: animals) {
        // Handle item click
        val intent = Intent(this, AnimalDetailsActivity::class.java)
        intent.putExtra("animals", animal)
        startActivity(intent)
    }

}

