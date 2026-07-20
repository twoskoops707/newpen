package com.twoskoops707.newpen.ui.setup

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

        val installAllCmd = "pkg update -y && pkg install -y nmap python python-pip curl wget tshark git dpkg && pip install requests scapy && wget https://github.com/brannondorsey/naive-hashcat/releases/download/data/rockyou.txt && echo ALL DONE"
        val installKaliCmd = "pkg update -y && pkg install -y wget curl && wget -O install-nethunter-termux https://offs.ec/2MceZWr && bash install-nethunter-termux"

        binding.btnInstallAll.setOnClickListener {
            copyAndOpenTermux(installAllCmd, "Install command copied! Paste in Termux (long-press → Paste)")
        }

        binding.btnInstallKali.setOnClickListener {
            copyAndOpenTermux(installKaliCmd, "Kali install command copied! Paste in Termux. This downloads ~8GB — use WiFi!")
        }
    }

    private fun copyAndOpenTermux(command: String, toastMsg: String) {
        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("termux_cmd", command))
        val intent = requireContext().packageManager.getLaunchIntentForPackage("com.termux")
        if (intent != null) {
            startActivity(intent)
        } else {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://f-droid.org/packages/com.termux/")))
        }
        Toast.makeText(requireContext(), toastMsg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
