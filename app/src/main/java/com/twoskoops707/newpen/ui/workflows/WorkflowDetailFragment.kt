package com.twoskoops707.newpen.ui.workflows

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.twoskoops707.newpen.data.models.Device
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

        val stepAdapter = StepAdapter(
            steps = workflow.steps,
            onCommandCopy = { command ->
                copyToClipboard(command)
                Toast.makeText(requireContext(), "Copied!", Toast.LENGTH_SHORT).show()
            },
            onRunTermux = { command ->
                copyToClipboard(command)
                openTermux()
                Toast.makeText(requireContext(), getString(com.twoskoops707.newpen.R.string.toast_run_termux), Toast.LENGTH_LONG).show()
            }
        )

        binding.rvSteps.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSteps.adapter = stepAdapter
    }

    private fun copyToClipboard(text: String) {
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("command", text))
    }

    private fun openTermux() {
        val intent = requireContext().packageManager.getLaunchIntentForPackage("com.termux")
        if (intent != null) {
            startActivity(intent)
        } else {
            val storeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://f-droid.org/packages/com.termux/"))
            startActivity(storeIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class StepAdapter(
        private val steps: List<WorkflowStep>,
        private val onCommandCopy: (String) -> Unit,
        private val onRunTermux: (String) -> Unit
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
                        val prefix = when (cmd.device) {
                            Device.TERMUX -> "[TERMUX]"
                            Device.MARAUDER -> "[AWOK]"
                            Device.FLIPPER_CLI -> "[FLIPPER]"
                            Device.ANDROID -> "[PHONE]"
                            Device.PC -> "[PC]"
                        }
                        sb.append("$prefix ${cmd.label}:\n${cmd.value}\n\n")
                    }
                    binding.tvCommands.text = sb.toString().trimEnd()
                    binding.tvCommands.setOnClickListener {
                        val allCmds = step.commands.joinToString("\n") { it.value }
                        onCommandCopy(allCmds)
                    }

                    val termuxCmds = step.commands.filter { it.device == Device.TERMUX }
                    if (termuxCmds.isNotEmpty()) {
                        binding.btnRunTermux.isVisible = true
                        binding.btnRunTermux.setOnClickListener {
                            val cmdText = termuxCmds.joinToString("\n") { it.value }
                            onRunTermux(cmdText)
                        }
                    } else {
                        binding.btnRunTermux.isVisible = false
                    }
                } else {
                    binding.tvCommands.isVisible = false
                    binding.tvCommandsLabel.isVisible = false
                    binding.btnRunTermux.isVisible = false
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
                    binding.llWarning.isVisible = true
                    binding.tvWarning.text = step.warning
                } else {
                    binding.llWarning.isVisible = false
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
