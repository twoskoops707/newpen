package com.twoskoops707.newpen.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.twoskoops707.newpen.AppTheme
import com.twoskoops707.newpen.ThemeManager
import com.twoskoops707.newpen.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val current = ThemeManager.getCurrent(requireContext())
        updateIndicators(current)

        val rows = mapOf(
            binding.rowTerminal to AppTheme.TERMINAL,
            binding.rowPhosphor to AppTheme.PHOSPHOR,
            binding.rowArctic to AppTheme.ARCTIC,
            binding.rowMidnight to AppTheme.MIDNIGHT,
            binding.rowSolar to AppTheme.SOLAR,
            binding.rowGhost to AppTheme.GHOST
        )

        rows.forEach { (row, theme) ->
            row.setOnClickListener {
                ThemeManager.save(requireContext(), theme)
                requireActivity().recreate()
            }
        }
    }

    private fun updateIndicators(current: AppTheme) {
        val indicators = mapOf(
            AppTheme.TERMINAL to binding.indTerminal,
            AppTheme.PHOSPHOR to binding.indPhosphor,
            AppTheme.ARCTIC to binding.indArctic,
            AppTheme.MIDNIGHT to binding.indMidnight,
            AppTheme.SOLAR to binding.indSolar,
            AppTheme.GHOST to binding.indGhost
        )
        indicators.forEach { (theme, ind) ->
            ind.text = if (theme == current) "[ ON ]" else "›"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
