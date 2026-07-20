package com.twoskoops707.newpen.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.twoskoops707.newpen.R
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
            val iconRes = iconFor(category.id)
            val tintColor = tintFor(category.id)
            binding.ivIcon.setImageResource(iconRes)
            binding.ivIcon.setColorFilter(
                ContextCompat.getColor(binding.root.context, tintColor)
            )
            binding.root.setOnClickListener { onCategoryClick(category) }
        }

        private fun iconFor(id: String) = when (id) {
            "setup"     -> R.drawable.ic_cat_setup
            "wifi"      -> R.drawable.ic_cat_wifi
            "subghz"    -> R.drawable.ic_cat_subghz
            "rfid"      -> R.drawable.ic_cat_rfid
            "nfc"       -> R.drawable.ic_cat_nfc
            "badusb"    -> R.drawable.ic_cat_badusb
            "bluetooth" -> R.drawable.ic_cat_bluetooth
            "infrared"  -> R.drawable.ic_cat_infrared
            "ibutton"   -> R.drawable.ic_cat_ibutton
            "gpio"      -> R.drawable.ic_cat_gpio
            "connect"   -> R.drawable.ic_cat_connect
            "tools"     -> R.drawable.ic_cat_tools
            else        -> R.drawable.ic_cat_tools
        }

        private fun tintFor(id: String) = when (id) {
            "setup"     -> R.color.colorPrimary
            "wifi"      -> R.color.colorCyan
            "subghz"    -> R.color.colorOrange
            "rfid"      -> R.color.colorPurple
            "nfc"       -> R.color.colorCyan
            "badusb"    -> R.color.colorRed
            "bluetooth" -> R.color.colorPurple
            "infrared"  -> R.color.colorOrange
            "ibutton"   -> R.color.colorYellowGreen
            "gpio"      -> R.color.colorCyan
            "connect"   -> R.color.colorPrimary
            "tools"     -> R.color.colorYellowGreen
            else        -> R.color.colorPrimary
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
