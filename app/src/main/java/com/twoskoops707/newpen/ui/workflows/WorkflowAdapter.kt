package com.twoskoops707.newpen.ui.workflows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twoskoops707.newpen.data.models.Hardware
import com.twoskoops707.newpen.data.models.Workflow
import com.twoskoops707.newpen.databinding.ItemWorkflowBinding

class WorkflowAdapter(
    private val workflows: List<Workflow>,
    private val onClick: (Workflow) -> Unit
) : RecyclerView.Adapter<WorkflowAdapter.WorkflowViewHolder>() {

    inner class WorkflowViewHolder(private val binding: ItemWorkflowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(workflow: Workflow) {
            binding.tvTitle.text = workflow.title
            binding.tvSubtitle.text = "${workflow.steps.size} steps"

            val hasFlipper = workflow.hardware.contains(Hardware.FLIPPER)
            val hasAWOK = workflow.hardware.contains(Hardware.AWOK)
            val hasBoth = workflow.hardware.contains(Hardware.BOTH) || (hasFlipper && hasAWOK)

            when {
                hasBoth -> {
                    binding.tvBadgeBoth.visibility = android.view.View.VISIBLE
                    binding.tvBadgeFlipper.visibility = android.view.View.GONE
                    binding.tvBadgeAWOK.visibility = android.view.View.GONE
                }
                hasFlipper -> {
                    binding.tvBadgeFlipper.visibility = android.view.View.VISIBLE
                    binding.tvBadgeAWOK.visibility = android.view.View.GONE
                    binding.tvBadgeBoth.visibility = android.view.View.GONE
                }
                hasAWOK -> {
                    binding.tvBadgeAWOK.visibility = android.view.View.VISIBLE
                    binding.tvBadgeFlipper.visibility = android.view.View.GONE
                    binding.tvBadgeBoth.visibility = android.view.View.GONE
                }
                else -> {
                    binding.tvBadgeFlipper.visibility = android.view.View.GONE
                    binding.tvBadgeAWOK.visibility = android.view.View.GONE
                    binding.tvBadgeBoth.visibility = android.view.View.GONE
                }
            }

            binding.root.setOnClickListener { onClick(workflow) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkflowViewHolder {
        val binding = ItemWorkflowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WorkflowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkflowViewHolder, position: Int) {
        holder.bind(workflows[position])
    }

    override fun getItemCount(): Int = workflows.size
}
