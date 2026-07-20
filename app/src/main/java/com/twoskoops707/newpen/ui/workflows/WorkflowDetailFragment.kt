package com.twoskoops707.newpen.ui.workflows

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twoskoops707.newpen.data.WorkflowRepository
import com.twoskoops707.newpen.data.models.WorkflowStep
import com.twoskoops707.newpen.databinding.FragmentWorkflowDetailBinding
import com.twoskoops707.newpen.databinding.ItemStepCardBinding

class WorkflowDetailFragment : Fragment() {

    private var _binding: FragmentWorkflowDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkflowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val workflowId = arguments?.getString("workflowId") ?: return
        val workflow = WorkflowRepository.getWorkflowById(workflowId) ?: return

        binding.tvWorkflowTitle.text = workflow.title

        if (workflow.prerequisites.isNotEmpty()) {
            binding.tvPrerequisites.text = workflow.prerequisites.joinToString("\n") { "• $it" }
            binding.cardPrerequisites.isVisible = true
        } else {
            binding.cardPrerequisites.isVisible = false
        }

        binding.tvExpandPrereq.setOnClickListener {
            val isVisible = binding.tvPrerequisites.isVisible
            binding.tvPrerequisites.isVisible = !isVisible
            binding.tvExpandPrereq.text = if (isVisible) "Prerequisites ▼" else "Prerequisites ▲"
        }

        val stepAdapter = StepAdapter(workflow.steps) { command ->
            val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("command", command)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Copied: $command", Toast.LENGTH_SHORT).show()
        }

        binding.rvSteps.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSteps.adapter = stepAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class StepAdapter(
        private val steps: List<WorkflowStep>,
        private val onCommandCopy: (String) -> Unit
    ) : RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

        inner class StepViewHolder(private val binding: ItemStepCardBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(step: WorkflowStep) {
                binding.tvStepNumber.text = "Step ${step.stepNumber}"
                binding.tvStepTitle.text = step.title
                binding.tvStepDescription.text = step.description

                if (step.commands.isNotEmpty()) {
                    binding.tvCommands.isVisible = true
                    binding.tvCommandsLabel.isVisible = true
                    val sb = StringBuilder()
                    step.commands.forEach { cmd ->
                        sb.append("[${cmd.device.name}] ${cmd.label}: ${cmd.value}\n")
                    }
                    binding.tvCommands.text = sb.toString().trimEnd()
                    binding.tvCommands.setOnClickListener {
                        val allCmds = step.commands.joinToString("\n") { it.value }
                        onCommandCopy(allCmds)
                    }
                } else {
                    binding.tvCommands.isVisible = false
                    binding.tvCommandsLabel.isVisible = false
                }

                if (step.tips.isNotEmpty()) {
                    binding.tvTips.isVisible = true
                    binding.tvTipsLabel.isVisible = true
                    binding.tvTips.text = step.tips.joinToString("\n") { "• $it" }
                } else {
                    binding.tvTips.isVisible = false
                    binding.tvTipsLabel.isVisible = false
                }

                if (!step.warning.isNullOrBlank()) {
                    binding.tvWarning.isVisible = true
                    binding.tvWarningLabel.isVisible = true
                    binding.tvWarning.text = step.warning
                } else {
                    binding.tvWarning.isVisible = false
                    binding.tvWarningLabel.isVisible = false
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
            val binding = ItemStepCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return StepViewHolder(binding)
        }

        override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
            holder.bind(steps[position])
        }

        override fun getItemCount(): Int = steps.size
    }
}
