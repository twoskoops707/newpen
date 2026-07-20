package com.twoskoops707.newpen.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twoskoops707.newpen.data.models.Category
import com.twoskoops707.newpen.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val categories: List<Category>,
    private val onCategoryClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.tvTitle.text = category.title
            binding.tvCount.text = "${category.workflowCount} workflows"
            binding.tvEmoji.text = emojiFor(category.id)
            binding.root.setOnClickListener { onCategoryClick(category) }
        }

        private fun emojiFor(id: String) = when (id) {
            "setup"     -> "⚙️"
            "wifi"      -> "📡"
            "subghz"    -> "📻"
            "rfid"      -> "💳"
            "nfc"       -> "🔖"
            "badusb"    -> "⌨️"
            "bluetooth" -> "🔷"
            "infrared"  -> "🔴"
            "ibutton"   -> "🗝️"
            "gpio"      -> "🔌"
            "connect"   -> "📲"
            "tools"     -> "🛠️"
            else        -> "📁"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}
