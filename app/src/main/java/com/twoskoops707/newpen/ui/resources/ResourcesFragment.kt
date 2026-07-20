package com.twoskoops707.newpen.ui.resources

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twoskoops707.newpen.databinding.FragmentResourcesBinding
import com.twoskoops707.newpen.databinding.ItemResourceBinding

class ResourcesFragment : Fragment() {

    private var _binding: FragmentResourcesBinding? = null
    private val binding get() = _binding!!

    data class Resource(
        val title: String,
        val url: String,
        val type: ResourceType
    )

    enum class ResourceType { YOUTUBE, GITHUB }

    private val resources = listOf(
        Resource(
            "THE Definitive Guide to ESP32 Marauder!!",
            "https://www.youtube.com/watch?v=YpVu-UF6k10",
            ResourceType.YOUTUBE
        ),
        Resource(
            "Flipper Zero: Complete Beginner to Advanced 2025",
            "https://www.youtube.com/playlist?list=PLnW9cfFSr4s79q5FHGN_yTizoPKKmgp4z",
            ResourceType.YOUTUBE
        ),
        Resource(
            "Flipper + Marauder: Best Wireless Hacking Device 2025?",
            "https://www.youtube.com/watch?v=prYhtvkxO24",
            ResourceType.YOUTUBE
        ),
        Resource(
            "Hacking WiFi Passwords with Flipper Zero + Hashcat",
            "https://www.youtube.com/watch?v=subLBPJ3IxU",
            ResourceType.YOUTUBE
        ),
        Resource(
            "Evil Portal on Flipper Zero Tutorial",
            "https://www.youtube.com/watch?v=WPT7qeySf6g",
            ResourceType.YOUTUBE
        ),
        Resource(
            "Flipper Zero AWOK RFID/NFC Complete Guide",
            "https://www.youtube.com/watch?v=DUJUKrSedJs",
            ResourceType.YOUTUBE
        ),
        Resource(
            "ESP32 Marauder Firmware",
            "https://github.com/justcallmekoko/ESP32Marauder",
            ResourceType.GITHUB
        ),
        Resource(
            "ESP32 Marauder Wiki (CLI Docs)",
            "https://github.com/justcallmekoko/ESP32Marauder/wiki/cli",
            ResourceType.GITHUB
        ),
        Resource(
            "WiFi Marauder Flipper App",
            "https://github.com/0xchocolate/flipperzero-wifi-marauder",
            ResourceType.GITHUB
        ),
        Resource(
            "Flipper Zero Protobuf (RPC)",
            "https://github.com/flipperdevices/flipperzero-protobuf",
            ResourceType.GITHUB
        ),
        Resource(
            "BadUSB Payloads Collection",
            "https://github.com/I-Am-Jakoby/Flipper-Zero-BadUSB",
            ResourceType.GITHUB
        ),
        Resource(
            "MacOS DuckyScripts (100+)",
            "https://github.com/narstybits/MacOS-DuckyScripts",
            ResourceType.GITHUB
        ),
        Resource(
            "Flipper Brute Force Sub-GHz",
            "https://github.com/tobiabocchi/flipperzero-bruteforce",
            ResourceType.GITHUB
        ),
        Resource(
            "Easy Marauder Flash Tool",
            "https://github.com/SkeletonMan03/FZEasyMarauderFlash",
            ResourceType.GITHUB
        ),
        Resource(
            "Flipper Zero Awesome List",
            "https://github.com/djsime1/awesome-flipperzero",
            ResourceType.GITHUB
        ),
        Resource(
            "Momentum Firmware",
            "https://github.com/Next-Flip/Momentum-Firmware",
            ResourceType.GITHUB
        ),
        Resource(
            "Flipper Zero Protobuf Python",
            "https://github.com/flipperdevices/flipperzero_protobuf_py",
            ResourceType.GITHUB
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ResourceAdapter(resources) { resource ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resource.url))
            startActivity(intent)
        }

        binding.rvResources.layoutManager = LinearLayoutManager(requireContext())
        binding.rvResources.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class ResourceAdapter(
        private val items: List<Resource>,
        private val onItemClick: (Resource) -> Unit
    ) : RecyclerView.Adapter<ResourceAdapter.ResourceViewHolder>() {

        inner class ResourceViewHolder(private val binding: ItemResourceBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(resource: Resource) {
                binding.tvResourceTitle.text = resource.title
                binding.tvResourceType.text = when (resource.type) {
                    ResourceType.YOUTUBE -> "YouTube"
                    ResourceType.GITHUB -> "GitHub"
                }
                binding.tvResourceUrl.text = resource.url
                binding.root.setOnClickListener { onItemClick(resource) }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
            val binding = ItemResourceBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return ResourceViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size
    }
}
