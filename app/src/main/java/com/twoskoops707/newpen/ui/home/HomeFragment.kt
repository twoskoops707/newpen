package com.twoskoops707.newpen.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.twoskoops707.newpen.R
import com.twoskoops707.newpen.data.WorkflowRepository
import com.twoskoops707.newpen.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = WorkflowRepository.getCategories()

        val adapter = CategoryAdapter(categories) { category ->
            val bundle = bundleOf("categoryId" to category.id)
            findNavController().navigate(R.id.action_homeFragment_to_workflowListFragment, bundle)
        }

        binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCategories.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
