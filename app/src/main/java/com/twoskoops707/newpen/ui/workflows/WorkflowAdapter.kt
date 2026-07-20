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
            binding.tvSubtitle.text = workflow.subtitle

            val hasFlipper = workflow.hardware.contains(Hardware.FLIPPER) || workflow.hardware.contains(Hardware.BOTH)
            val hasAWOK = workflow.hardware.contains(Hardware.AWOK) || workflow.hardware.contains(Hardware.BOTH)
            binding.tvBadgeFlipper.visibility = if (hasFlipper) android.view.View.VISIBLE else android.view.View.GONE
            binding.tvBadgeAWOK.visibility = if (hasAWOK) android.view.View.VISIBLE else android.view.View.GONE

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
