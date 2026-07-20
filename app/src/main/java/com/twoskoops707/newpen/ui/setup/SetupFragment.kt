package com.twoskoops707.newpen.ui.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.twoskoops707.newpen.databinding.FragmentSetupBinding

class SetupFragment : Fragment() {

    private var _binding: FragmentSetupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHardwareContent.text = """
• Flipper Zero (any version, v2.0+ for Momentum)
• AWOK Dual Mini v3 (ESP32 + Marauder firmware)
• USB-C cable (for Flipper USB)
• USB-C OTG adapter (for Android USB host)
• T5577 blank RFID cards (for RFID cloning)
• Magic Gen1a NFC cards (for NFC cloning)
• Blank iButton DS1990A (for iButton cloning)
        """.trimIndent()

        binding.tvFirmwareContent.text = """
┌─────────────┬──────────────────────────────┬──────────────────────────────┐
│ Firmware    │ Pros                         │ Cons                         │
├─────────────┼──────────────────────────────┼──────────────────────────────┤
│ Official    │ Stable, OTA updates          │ Region-locked, limited feats │
│ Unleashed   │ No region locks, extra apps  │ No desktop app support       │
│ Momentum    │ Best features, VID/PID spoof │ Manual install via qFlipper  │
└─────────────┴──────────────────────────────┴──────────────────────────────┘
        """.trimIndent()

        binding.tvChecklistContent.text = """
☐ Install Momentum firmware via qFlipper
☐ Flash Marauder on AWOK
☐ Install WiFi Marauder .fap on Flipper SD
☐ Enable Bluetooth on Flipper (Settings > Bluetooth)
☐ Pair Flipper to this phone via mobile app
☐ Allow-external-apps in Termux (if using Termux features)
☐ termux-setup-storage in Termux
☐ pkg install nmap python curl wget hashcat
        """.trimIndent()

        binding.tvResourcesContent.text = """
Momentum Firmware: github.com/Next-Flip/Momentum-Firmware
ESP32 Marauder: github.com/justcallmekoko/ESP32Marauder
WiFi Marauder FAP: github.com/0xchocolate/flipperzero-wifi-marauder
Awesome Flipper: github.com/djsime1/awesome-flipperzero
BadUSB Payloads: github.com/I-Am-Jakoby/Flipper-Zero-BadUSB
        """.trimIndent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
