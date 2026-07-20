package com.twoskoops707.newpen.ui.workflows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twoskoops707.newpen.R
import com.twoskoops707.newpen.data.WorkflowRepository
import com.twoskoops707.newpen.databinding.FragmentWorkflowListBinding

class WorkflowListFragment : Fragment() {

    private var _binding: FragmentWorkflowListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkflowListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryId = arguments?.getString("categoryId") ?: return
        val workflows = WorkflowRepository.getWorkflowsByCategory(categoryId)

        val adapter = WorkflowAdapter(workflows) { workflow ->
            val bundle = bundleOf("workflowId" to workflow.id)
            findNavController().navigate(R.id.action_workflowListFragment_to_workflowDetailFragment, bundle)
        }

        binding.rvWorkflows.layoutManager = LinearLayoutManager(requireContext())
        binding.rvWorkflows.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
