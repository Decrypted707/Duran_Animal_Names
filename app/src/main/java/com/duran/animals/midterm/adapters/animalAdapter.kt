package com.duran.animals.midterm.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duran.animals.midterm.model.animals
import com.duran.animals.midterm.databinding.AnimalListBinding


class AnimalAdapter(
    private val animalList: List<animals>,
    private val onItemClick: (animals) -> Unit,
    private val onUnblockClick: (animals) -> Unit
) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {
    inner class AnimalViewHolder(private val binding: AnimalListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val animal = animalList[position]
                    onItemClick(animal)
                }
                binding.root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val animal = animalList[position]
                        onUnblockClick(animal)
                    }
                }
            }


        }


        fun bind(animal: animals) {
            binding.animals.text = animal.name
            if (animal.blockstatus) {
                binding.root.visibility = View.GONE
            } else {
                binding.root.visibility = View.VISIBLE
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val binding = AnimalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(animalList[position])
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun updateData(newData: List<animals>) {
//        animalList.clear()
//        animalList.addAll(newData)
//        notifyDataSetChanged()
//    }
}





