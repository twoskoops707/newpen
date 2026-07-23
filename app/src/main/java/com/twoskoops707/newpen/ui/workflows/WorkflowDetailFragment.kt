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
import com.twoskoops707.newpen.DeviceProfileManager
import com.twoskoops707.newpen.R
import com.twoskoops707.newpen.data.WorkflowRepository
import com.twoskoops707.newpen.data.models.Device
import com.twoskoops707.newpen.data.models.Hardware
import com.twoskoops707.newpen.data.models.WifiBoard
import com.twoskoops707.newpen.data.models.WorkflowStep
import com.twoskoops707.newpen.databinding.FragmentWorkflowDetailBinding
import com.twoskoops707.newpen.databinding.ItemCommandBinding
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
        val wifiBoard = DeviceProfileManager.getBoard(requireContext())

        binding.tvWorkflowTitle.text = workflow.title
        binding.tvStepCounter.text = "${workflow.steps.size} STEPS"

        val needsWifiBoard = workflow.hardware.any { it == Hardware.AWOK || it == Hardware.BOTH }
        if (needsWifiBoard) {
            binding.tvGearBanner.visibility = View.VISIBLE
            binding.tvGearBanner.text = when (wifiBoard) {
                WifiBoard.AWOK_DUAL_MINI_V3 ->
                    "// GEAR: AWOK DUAL MINI V3  —  navigate menus on AWOK screen"
                WifiBoard.FLIPPER_WIFI_DEV_BOARD ->
                    "// GEAR: FLIPPER WIFI DEV BOARD  —  Apps → GPIO → [ESP32] WiFi Marauder → type command"
                WifiBoard.NONE ->
                    "// GEAR: NO WIFI MODULE  —  attach an AWOK or Flipper Dev Board to run these attacks"
            }
        } else {
            binding.tvGearBanner.visibility = View.GONE
        }

        if (workflow.prerequisites.isNotEmpty()) {
            binding.tvPrerequisites.text = workflow.prerequisites.joinToString("\n") { "· $it" }
            binding.cardPrerequisites.isVisible = true
        } else {
            binding.cardPrerequisites.isVisible = false
        }

        binding.tvExpandPrereq.setOnClickListener {
            val expanded = binding.tvPrerequisites.isVisible
            binding.tvPrerequisites.isVisible = !expanded
            binding.tvExpandPrereq.text = if (expanded) "// prerequisites  +" else "// prerequisites  −"
        }

        val totalSteps = workflow.steps.size
        val stepAdapter = StepAdapter(
            steps = workflow.steps,
            wifiBoard = wifiBoard,
            onCommandCopy = { command ->
                copyToClipboard(command)
                Toast.makeText(requireContext(), "Copied!", Toast.LENGTH_SHORT).show()
            },
            onCommandRun = { command ->
                executeInTermux(command)
            },
            onRunTermux = { commands ->
                executeInTermux(commands)
            },
            onStepVisible = { stepIndex ->
                val progress = ((stepIndex + 1) * 100) / totalSteps
                binding.progressBar.progress = progress
                binding.tvStepCounter.text = "STEP ${stepIndex + 1} OF $totalSteps"
            }
        )

        binding.rvSteps.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSteps.adapter = stepAdapter

        binding.rvSteps.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lm = recyclerView.layoutManager as LinearLayoutManager
                val firstVisible = lm.findFirstVisibleItemPosition()
                if (firstVisible >= 0) {
                    val progress = ((firstVisible + 1) * 100) / totalSteps
                    binding.progressBar.progress = progress
                    binding.tvStepCounter.text = "STEP ${firstVisible + 1} OF $totalSteps"
                }
            }
        })
    }

    private fun copyToClipboard(text: String) {
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("command", text))
    }

    private fun executeInTermux(command: String) {
        try {
            val intent = Intent()
            intent.setClassName("com.termux", "com.termux.app.run_command.RunCommandService")
            intent.action = "com.termux.RUN_COMMAND"
            intent.putExtra("com.termux.RUN_COMMAND_PATH", "/data/data/com.termux/files/usr/bin/bash")
            intent.putExtra("com.termux.RUN_COMMAND_ARGUMENTS", arrayOf("-c", command))
            intent.putExtra("com.termux.RUN_COMMAND_WORKDIR", "/data/data/com.termux/files/home")
            intent.putExtra("com.termux.RUN_COMMAND_TERMINAL", true)
            requireContext().startService(intent)
            val launch = requireContext().packageManager.getLaunchIntentForPackage("com.termux")
            if (launch != null) {
                launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                requireContext().startActivity(launch)
            }
        } catch (e: Exception) {
            copyToClipboard(command)
            val launch = requireContext().packageManager.getLaunchIntentForPackage("com.termux")
            if (launch != null) startActivity(launch)
            else startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://f-droid.org/packages/com.termux/")))
            Toast.makeText(requireContext(), getString(R.string.toast_run_termux), Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class StepAdapter(
        private val steps: List<WorkflowStep>,
        private val wifiBoard: WifiBoard,
        private val onCommandCopy: (String) -> Unit,
        private val onCommandRun: (String) -> Unit,
        private val onRunTermux: (String) -> Unit,
        private val onStepVisible: (Int) -> Unit
    ) : RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

        inner class StepViewHolder(private val binding: ItemStepCardBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(step: WorkflowStep, position: Int) {
                binding.tvStepNumber.text = "// STEP ${step.stepNumber}"
                binding.tvStepTitle.text = step.title
                binding.tvStepDescription.text = step.description

                binding.llCommands.removeAllViews()
                if (step.commands.isNotEmpty()) {
                    binding.tvCommandsLabel.isVisible = true
                    binding.llCommands.isVisible = true
                    step.commands.forEach { cmd ->
                        val cmdBinding = ItemCommandBinding.inflate(
                            LayoutInflater.from(binding.root.context),
                            binding.llCommands,
                            false
                        )
                        val prefix = when (cmd.device) {
                            Device.TERMUX -> "TERMUX"
                            Device.MARAUDER -> when (wifiBoard) {
                                WifiBoard.AWOK_DUAL_MINI_V3 -> "AWOK SCREEN"
                                WifiBoard.FLIPPER_WIFI_DEV_BOARD -> "FLIPPER APP"
                                WifiBoard.NONE -> "MARAUDER"
                            }
                            Device.FLIPPER_CLI -> "FLIPPER"
                            Device.ANDROID -> "PHONE"
                            Device.PC -> "PC"
                        }
                        cmdBinding.tvCommandLabel.text = "via $prefix · ${cmd.label}"
                        cmdBinding.tvCommandValue.text = cmd.value
                        cmdBinding.tvCommandDevice.text = cmd.device.name.lowercase().replace('_', ' ')
                        cmdBinding.btnCopy.setOnClickListener { onCommandCopy(cmd.value) }
                        if (cmd.device == Device.TERMUX) {
                            cmdBinding.btnRun.isVisible = true
                            cmdBinding.btnRun.setOnClickListener { onCommandRun(cmd.value) }
                        } else {
                            cmdBinding.btnRun.isVisible = false
                        }
                        binding.llCommands.addView(cmdBinding.root)
                    }

                    val termuxCmds = step.commands.filter { it.device == Device.TERMUX }
                    if (termuxCmds.isNotEmpty()) {
                        binding.btnRunTermux.isVisible = true
                        binding.btnRunTermux.setOnClickListener {
                            val cmdText = termuxCmds.joinToString(" && ") { it.value }
                            onRunTermux(cmdText)
                        }
                    } else {
                        binding.btnRunTermux.isVisible = false
                    }
                } else {
                    binding.tvCommandsLabel.isVisible = false
                    binding.llCommands.isVisible = false
                    binding.btnRunTermux.isVisible = false
                }

                if (step.tips.isNotEmpty()) {
                    binding.tvTips.text = step.tips.joinToString("\n\n") { "· $it" }
                    binding.btnExpandTips.isVisible = true
                    binding.btnExpandExtra.isVisible = true
                } else {
                    binding.btnExpandTips.isVisible = false
                    binding.btnExpandExtra.isVisible = false
                }

                binding.llTips.isVisible = false

                binding.btnExpandTips.setOnClickListener {
                    binding.llTips.isVisible = !binding.llTips.isVisible
                }
                binding.btnExpandExtra.setOnClickListener {
                    binding.llTips.isVisible = !binding.llTips.isVisible
                }
                binding.btnExpandWhy.setOnClickListener {
                    binding.llTips.isVisible = !binding.llTips.isVisible
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
            holder.bind(steps[position], position)
        }

        override fun getItemCount(): Int = steps.size
    }
}
